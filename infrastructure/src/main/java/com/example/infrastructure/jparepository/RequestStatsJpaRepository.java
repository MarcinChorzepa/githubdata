package com.example.infrastructure.jparepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestStatsJpaRepository extends CrudRepository<RequestStatsEntity, Long> {


    Optional<RequestStatsEntity> findByUserLogin(String loginName);
}
