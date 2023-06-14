package dev.mikel_v.vhub_api.repository;

import dev.mikel_v.vhub_api.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "likes", collectionResourceRel = "likes")
public interface LikeRepository extends PagingAndSortingRepository<LikeEntity, Long>, JpaRepository<LikeEntity, Long> {

}
