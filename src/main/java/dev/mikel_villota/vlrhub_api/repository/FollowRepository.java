package dev.mikel_villota.vlrhub_api.repository;

import dev.mikel_villota.vlrhub_api.entity.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "follows", collectionResourceRel = "follows")
public interface FollowRepository extends PagingAndSortingRepository<FollowEntity, Long>, JpaRepository<FollowEntity, Long> {

}