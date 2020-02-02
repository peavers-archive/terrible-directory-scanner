/* Licensed under Apache-2.0 */
package io.terrible.directory.scanner.service;

import com.google.common.net.MediaType;
import io.terrible.directory.scanner.domain.MediaFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayDeque;
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
@RequiredArgsConstructor
public class ScanServiceImpl implements ScanService {

  @Override
  public ArrayDeque<MediaFile> scanMedia(final String input) throws IOException {

    final Collection<File> files =
        FileUtils.listFiles(
            new File(input), new RegexFileFilter("^(.*?)"), DirectoryFileFilter.DIRECTORY);

    final ArrayDeque<MediaFile> videos = new ArrayDeque<>(files.size());

    for (final File file : files) {
      final String mimeType = Files.probeContentType(file.toPath());

      if (StringUtils.isAnyEmpty(mimeType)) {
        continue;
      }

      //noinspection UnstableApiUsage
      if (MediaType.parse(mimeType).is(MediaType.ANY_VIDEO_TYPE)) {
        log.info("found media file {}", file.getAbsolutePath());

        videos.add(
            MediaFile.builder().absolutePath(file.getAbsolutePath()).mimeType(mimeType).build());
      }
    }

    log.info("processed {} records", videos.size());

    return videos;
  }
}
