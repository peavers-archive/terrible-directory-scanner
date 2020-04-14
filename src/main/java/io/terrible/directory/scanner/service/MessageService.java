/* Licensed under Apache-2.0 */
package io.terrible.directory.scanner.service;

import java.io.File;
import org.springframework.messaging.support.GenericMessage;

public interface MessageService {

  boolean send(GenericMessage<File> message);
}
