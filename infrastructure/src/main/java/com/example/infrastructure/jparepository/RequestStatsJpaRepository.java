package com.example.infrastructure.jparepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestStatsJpaRepository extends CrudRepository<RequestStatsEntity, Long> {


    Optional<RequestStatsEntity> findByUserLogin(String loginName);

    @Modifying(clearAutomatically = true)
    @Query("update RequestStatsEntity stat set stat.countOfRequests = stat.countOfRequests+1 " +
            "where stat.userLogin = :userLogin")
    void updateTheStatistics(@Param("userLogin") String userLogin);
}
