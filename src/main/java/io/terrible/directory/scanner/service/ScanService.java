/* Licensed under Apache-2.0 */
package io.terrible.directory.scanner.service;

import java.io.IOException;

/** @author Chris Turner (chris@forloop.space) */
public interface ScanService {

  void scanMedia(String directory) throws IOException;
}
