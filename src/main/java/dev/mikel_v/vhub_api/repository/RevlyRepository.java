package dev.mikel_v.vhub_api.repository;

import dev.mikel_v.vhub_api.entity.RevlyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "revlyes", collectionResourceRel = "revlyes")
public interface RevlyRepository extends PagingAndSortingRepository<RevlyEntity, Long>, JpaRepository<RevlyEntity, Long> {

}
