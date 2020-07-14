package com.example.infrastructure.integration.requeststats;

import com.example.domain.requeststats.domain.RequestStats;
import com.example.infrastructure.jparepository.RequestStatsEntity;

public class RequestStatsMapper {

    private RequestStatsMapper() {
        throw new IllegalStateException("RequestCountMapper");
    }

    public static RequestStats fromEntity(RequestStatsEntity requestStatsEntity) {
        return new RequestStats(requestStatsEntity.getId(),
                requestStatsEntity.getUserLogin(),
                requestStatsEntity.getCountOfRequests());
    }

    public static RequestStatsEntity toEntity(RequestStats requestStats) {
        RequestStatsEntity requestStatsEntity = new RequestStatsEntity();
        requestStatsEntity.setUserLogin(requestStats.getLoginName());
        requestStatsEntity.setCountOfRequests(requestStats.getCountOfRequests());
        requestStatsEntity.setId(requestStats.getId());
        return requestStatsEntity;
    }

    public static void mapJpaFields(RequestStats requestStats, RequestStatsEntity requestStatsEntity) {
        requestStatsEntity.setUserLogin(requestStats.getLoginName());
        requestStatsEntity.setCountOfRequests(requestStats.getCountOfRequests());
        requestStatsEntity.setId(requestStats.getId());
    }
}
