/* Licensed under Apache-2.0 */
package io.terrible.directory.scanner.configuration;

import javax.sql.DataSource;
import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;

/**
 * This can be pretty much ignored, is just here to wire up Postgres to record the status of the
 * task.
 *
 * @author Chris Turner (chris@forloop.space)
 */
public class DataSourceConfig extends DefaultTaskConfigurer {

  public DataSourceConfig(DataSource dataSource) {
    super(dataSource);
  }
}
