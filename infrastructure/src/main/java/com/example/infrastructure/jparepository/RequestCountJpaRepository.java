package com.example.infrastructure.jparepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestCountJpaRepository extends CrudRepository<RequestCountEntity, Long> {


}
