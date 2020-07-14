package com.example.infrastructure.jparepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticJpaRepository extends CrudRepository<StatisticEntity, Long> {


    Optional<StatisticEntity> findByUserLogin(String loginName);
}
