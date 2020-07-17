package com.example.infrastructure.jparepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RequestStatsJpaRepository extends CrudRepository<RequestStatsEntity, String> {


    @Modifying(clearAutomatically = true)
    @Query("update RequestStatsEntity stat set stat.countOfRequests = stat.countOfRequests+1 " +
            "where stat.userLogin = :userLogin")
    void updateTheStatistics(@Param("userLogin") String userLogin);

}
