package dev.mikel_v.vhub_api.repository;

import dev.mikel_v.vhub_api.entity.ImpressionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "impressions", collectionResourceRel = "impressions")
public interface ImpressionRepository extends PagingAndSortingRepository<ImpressionEntity, Long>, JpaRepository<ImpressionEntity, Long> {

}