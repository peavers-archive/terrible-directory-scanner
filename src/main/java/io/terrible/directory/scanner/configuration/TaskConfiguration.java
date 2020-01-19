/* Licensed under Apache-2.0 */
package io.terrible.directory.scanner.configuration;

import com.beust.jcommander.JCommander;
import io.terrible.directory.scanner.Application;
import io.terrible.directory.scanner.service.ScanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author Chris Turner (chris@forloop.space) */
@Slf4j
@EnableTask
@Configuration
@RequiredArgsConstructor
public class TaskConfiguration {

  private final ScanService scanService;

  private final Application.Args args = new Application.Args();

  @Bean
  public CommandLineRunner commandLineRunner() {
    return args -> {
      JCommander.newBuilder().addObject(this.args).build().parse(args);

      scanService.scanForVideos(this.args.getDirectory());
    };
  }
}
