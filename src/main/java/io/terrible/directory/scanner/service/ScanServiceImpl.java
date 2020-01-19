/* Licensed under Apache-2.0 */
package io.terrible.directory.scanner.service;

import com.google.common.net.MediaType;
import io.terrible.directory.scanner.domain.VideoFile;
import io.terrible.directory.scanner.repository.VideoFileRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/** @author Chris Turner (chris@forloop.space) */
@Slf4j
@Service
@SuppressWarnings("ALL")
@RequiredArgsConstructor
public class ScanServiceImpl implements ScanService {

  private final VideoFileRepository videoFileRepository;

  @Override
  public void scanForVideos(final String input) throws IOException {

    final Collection<File> files =
        FileUtils.listFiles(
            new File(input), new RegexFileFilter("^(.*?)"), DirectoryFileFilter.DIRECTORY);

    final ArrayList<VideoFile> videos = new ArrayList<>(files.size());

    for (final File file : files) {
      final String mimeType = Files.probeContentType(file.toPath());

      if (StringUtils.isAnyEmpty(mimeType)) {
        continue;
      }

      if (MediaType.parse(mimeType).is(MediaType.ANY_VIDEO_TYPE)) {
        videos.add(
            VideoFile.builder().absolutePath(file.getAbsolutePath()).mimeType(mimeType).build());
      }
    }

    // This is just a temp storage so nuke the old stuff
    videoFileRepository.deleteAll();

    videoFileRepository.saveAll(videos);

    log.info("processed {} records", videos.size());
  }
}
