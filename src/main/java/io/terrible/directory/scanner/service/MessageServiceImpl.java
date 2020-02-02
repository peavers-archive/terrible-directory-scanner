/* Licensed under Apache-2.0 */
package io.terrible.directory.scanner.service;

import io.terrible.directory.scanner.binding.MessageBinding;
import io.terrible.directory.scanner.domain.MediaFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

/** @author Chris Turner (chris@forloop.space) */
@Slf4j
@Service
@EnableBinding(MessageBinding.class)
public class MessageServiceImpl implements MessageService {

  private final MessageChannel channel;

  public MessageServiceImpl(MessageBinding binding) {
    this.channel = binding.subscription();
  }

  @Override
  public boolean send(final GenericMessage<MediaFile> message) {
    return this.channel.send(message);
  }
}
