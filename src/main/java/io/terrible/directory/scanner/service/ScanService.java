/* Licensed under Apache-2.0 */
package io.terrible.directory.scanner.service;

import io.terrible.directory.scanner.domain.MediaFile;
import java.io.IOException;
import java.util.ArrayDeque;

/** @author Chris Turner (chris@forloop.space) */
public interface ScanService {

  ArrayDeque<MediaFile> scanMedia(String directory) throws IOException;
}
