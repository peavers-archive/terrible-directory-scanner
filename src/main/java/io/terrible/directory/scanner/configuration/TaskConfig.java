/* Licensed under Apache-2.0 */
package io.terrible.directory.scanner.configuration;

import com.beust.jcommander.JCommander;
import io.terrible.directory.scanner.Application;
import io.terrible.directory.scanner.service.ScanService;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TaskConfig {

  private final ScanService scanService;

  private final Application.Args args = new Application.Args();

  private final DataSource dataSource;

  @Bean
  public DataSourceConfig getTaskConfigurer() {
    return new DataSourceConfig(dataSource);
  }

  @Bean
  public CommandLineRunner commandLineRunner() {
    return args -> {
      JCommander.newBuilder().addObject(this.args).build().parse(args);

      scanService.scanMedia(this.args.getDirectory());
    };
  }
}
