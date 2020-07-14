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
}
