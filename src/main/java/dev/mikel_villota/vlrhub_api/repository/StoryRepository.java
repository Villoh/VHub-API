package dev.mikel_villota.vlrhub_api.repository;

import dev.mikel_villota.vlrhub_api.entity.StoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "stories", collectionResourceRel = "stories")
public interface StoryRepository extends PagingAndSortingRepository<StoryEntity, Long>, JpaRepository<StoryEntity, Long> {

}