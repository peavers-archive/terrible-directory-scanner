/* Licensed under Apache-2.0 */
package io.terrible.directory.scanner.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaFile {

  private String name;

  private String absolutePath;

  private String mimeType;

  private long size;

  private Date lastAccessTime;

  private Date lastModifiedTime;

  private Date importedTime;
}
