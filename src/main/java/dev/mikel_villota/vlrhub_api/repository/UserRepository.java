package dev.mikel_villota.vlrhub_api.repository;

import dev.mikel_villota.vlrhub_api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "users", collectionResourceRel = "users")
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>, JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserNameIgnoreCase(String userName);
}
