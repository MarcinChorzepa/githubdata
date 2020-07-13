package com.example.infrastructure.jparepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestCountJpaRepository extends CrudRepository<RequestCountEntity, Long> {


    Optional<RequestCountEntity> findByUserLogin(String loginName);
}
