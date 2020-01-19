/* Licensed under Apache-2.0 */
package io.terrible.directory.scanner.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/** @author Chris Turner (chris@forloop.space) */
@Data
@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
public class VideoFile {

  @Id private String id;

  private String absolutePath;

  private String mimeType;
}
