/* Licensed under Apache-2.0 */
package io.terrible.directory.scanner.service;

import io.terrible.directory.scanner.binding.MessageBinding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
@EnableBinding(MessageBinding.class)
public class MessageServiceImpl implements MessageService {

  private final MessageChannel channel;

  public MessageServiceImpl(final MessageBinding binding) {
    this.channel = binding.subscription();
  }

  @Override
  public boolean send(final GenericMessage<File> message) {
    return this.channel.send(message);
  }
}
