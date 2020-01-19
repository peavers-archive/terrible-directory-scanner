/* Licensed under Apache-2.0 */
package io.terrible.directory.scanner.repository;

import io.terrible.directory.scanner.domain.VideoFile;
import org.springframework.data.mongodb.repository.MongoRepository;

/** @author Chris Turner (chris@forloop.space) */
public interface VideoFileRepository extends MongoRepository<VideoFile, String> {}
