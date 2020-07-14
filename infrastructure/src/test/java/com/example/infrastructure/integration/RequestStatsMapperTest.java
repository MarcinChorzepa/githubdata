package com.example.infrastructure.integration;

import com.example.domain.requeststats.domain.RequestStats;
import com.example.infrastructure.integration.requeststats.RequestStatsMapper;
import com.example.infrastructure.jparepository.RequestStatsEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RequestStatsMapperTest {
    private static final Long ID = 1L;
    private static final String USER_LOGIN = "testUser";
    private static final Long COUNT_OF_REQUESTS = 1L;


    @Test
    void shouldMapFieldsFromEntity() {
        RequestStatsEntity requestStatsEntity = prepareRequestStatsEntity();
        RequestStats result = RequestStatsMapper.fromEntity(requestStatsEntity);
        assertThat(result).isNotNull();
        assertThat(result.getCountOfRequests()).isEqualTo(COUNT_OF_REQUESTS);
        assertThat(result.getLoginName()).isEqualTo(USER_LOGIN);
        assertThat(result.getId()).isEqualTo(ID);

    }

    @Test
    void shouldMapFieldsToEntity() {
        RequestStats requestStats = prepareRequestStats();
        RequestStatsEntity result = RequestStatsMapper.toEntity(requestStats);
        assertThat(result).isNotNull();
        assertThat(result.getUserLogin()).isEqualTo(USER_LOGIN);
        assertThat(result.getCountOfRequests()).isEqualTo(COUNT_OF_REQUESTS);
        assertThat(result.getId()).isNull();
        assertThat(result.getVersion()).isNull();
    }

    @Test
    void shouldMapFieldsFromDomainToEntity() {
        RequestStatsEntity requestStatsEntity = prepareRequestStatsEntity();
        RequestStats requestStats = RequestStatsMapper.fromEntity(requestStatsEntity);
        requestStats.addRequestToCount();
        assertThat(requestStats.getCountOfRequests()).isEqualTo(COUNT_OF_REQUESTS + 1);

        RequestStatsMapper.mapJpaFields(requestStats, requestStatsEntity);
        assertThat(requestStatsEntity.getId()).isEqualTo(requestStats.getId());
        assertThat(requestStatsEntity.getUserLogin()).isEqualTo(requestStats.getLoginName());
        assertThat(requestStatsEntity.getCountOfRequests()).isEqualTo(requestStats.getCountOfRequests());
    }

    private RequestStatsEntity prepareRequestStatsEntity() {
        RequestStatsEntity requestStatsEntity = new RequestStatsEntity();
        requestStatsEntity.setId(ID);
        requestStatsEntity.setUserLogin(USER_LOGIN);
        requestStatsEntity.setCountOfRequests(COUNT_OF_REQUESTS);
        requestStatsEntity.setVersion(1);
        return requestStatsEntity;
    }

    private RequestStats prepareRequestStats() {
        return new RequestStats(USER_LOGIN);
    }
}
