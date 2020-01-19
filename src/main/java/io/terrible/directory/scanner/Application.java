/* Licensed under Apache-2.0 */
package io.terrible.directory.scanner;

import com.beust.jcommander.Parameter;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public static void main(final String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Data
  public static class Args {

    @Parameter(
        names = {"--directory", "-d"},
        arity = 1,
        description = "Directory to recursively scan",
        required = true)
    private String directory;
  }
}
