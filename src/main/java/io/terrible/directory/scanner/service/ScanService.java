/* Licensed under Apache-2.0 */
package io.terrible.directory.scanner.service;

import java.io.IOException;

public interface ScanService {

  void scanMedia(String directory) throws IOException;
}
