/* Licensed under Apache-2.0 */
package io.terrible.directory.scanner.domain;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

/** @author Chris Turner (chris@forloop.space) */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaFile {

  @Id private String id;

  private String absolutePath;

  private String mimeType;

  @Version private Long version;

  @CreatedDate private Instant created;

  @LastModifiedDate private Instant lastModified;
}
