/* Licensed under Apache-2.0 */
package io.terrible.directory.scanner.service;

import io.terrible.directory.scanner.domain.MediaFile;
import org.springframework.messaging.support.GenericMessage;

/** @author Chris Turner (chris@forloop.space) */
public interface MessageService {

  boolean send(GenericMessage<MediaFile> message);
}
