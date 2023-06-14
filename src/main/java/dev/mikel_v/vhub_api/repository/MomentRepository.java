package dev.mikel_v.vhub_api.repository;

import dev.mikel_v.vhub_api.entity.MomentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "moments", collectionResourceRel = "moments")
public interface MomentRepository extends PagingAndSortingRepository<MomentEntity, Long>, JpaRepository<MomentEntity, Long> {

}