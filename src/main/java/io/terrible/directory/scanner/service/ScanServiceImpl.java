/* Licensed under Apache-2.0 */
package io.terrible.directory.scanner.service;

import com.google.common.net.MediaType;
import io.terrible.directory.scanner.domain.MediaFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

/** @author Chris Turner (chris@forloop.space) */
@Slf4j
@Service
@RequiredArgsConstructor
public class ScanServiceImpl implements ScanService {

  private final MessageService messageService;

  @Override
  public void scanMedia(final String input) throws IOException {
    final Collection<File> files =
        FileUtils.listFiles(new File(input), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);

    for (final File file : files) {
      final String mimeType = Files.probeContentType(file.toPath());

      //noinspection UnstableApiUsage
      if (StringUtils.isNoneEmpty(mimeType)
          && MediaType.parse(mimeType).is(MediaType.ANY_VIDEO_TYPE)) {
        messageService.send(new GenericMessage<>(buildMediaFile(file, mimeType)));
      }
    }
  }

  private MediaFile buildMediaFile(final File file, final String mimeType) throws IOException {
    final BasicFileAttributes attributes =
        Files.readAttributes(file.toPath(), BasicFileAttributes.class);

    return MediaFile.builder()
        .name(file.getName())
        .absolutePath(file.getAbsolutePath())
        .importedTime(Instant.now().toEpochMilli())
        .lastAccessTime(attributes.lastAccessTime().toInstant().toEpochMilli())
        .lastModifiedTime(attributes.lastModifiedTime().toInstant().toEpochMilli())
        .size(attributes.size())
        .mimeType(mimeType)
        .build();
  }
}
