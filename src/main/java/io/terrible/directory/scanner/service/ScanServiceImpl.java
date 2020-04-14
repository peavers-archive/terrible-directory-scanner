/* Licensed under Apache-2.0 */
package io.terrible.directory.scanner.service;

import com.google.common.net.MediaType;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

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
        messageService.send(new GenericMessage<File>(file));
      }
    }
  }
}
