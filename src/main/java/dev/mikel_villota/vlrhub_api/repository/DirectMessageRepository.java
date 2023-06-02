package dev.mikel_villota.vlrhub_api.repository;

import dev.mikel_villota.vlrhub_api.entity.DirectMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "direct_messages", collectionResourceRel = "direct_messages")
public interface DirectMessageRepository extends PagingAndSortingRepository<DirectMessageEntity, Long>, JpaRepository<DirectMessageEntity, Long> {

}
