/* Licensed under Apache-2.0 */
package io.terrible.directory.scanner.binding;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MessageBinding {

  String DIRECTORY_CHANNEL = "directoryChannel";

  @Output(DIRECTORY_CHANNEL)
  MessageChannel subscription();
}
