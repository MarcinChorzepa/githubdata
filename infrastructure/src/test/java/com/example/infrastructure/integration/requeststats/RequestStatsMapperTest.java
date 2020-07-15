package com.example.infrastructure.integration.requeststats;

import com.example.domain.requeststats.domain.RequestStats;
import com.example.infrastructure.integration.requeststats.RequestStatsMapper;
import com.example.infrastructure.jparepository.RequestStatsEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RequestStatsMapperTest {
    private static final String USER_LOGIN = "testUser";
    private static final Long COUNT_OF_REQUESTS = 1L;


    @Test
    void shouldMapFieldsFromEntity() {
        RequestStatsEntity requestStatsEntity = prepareRequestStatsEntity();
        RequestStats result = RequestStatsMapper.fromEntity(requestStatsEntity);
        assertThat(result).isNotNull();
        assertThat(result.getCountOfRequests()).isEqualTo(COUNT_OF_REQUESTS);
        assertThat(result.getLoginName()).isEqualTo(USER_LOGIN);

    }

    private RequestStatsEntity prepareRequestStatsEntity() {
        RequestStatsEntity requestStatsEntity = new RequestStatsEntity(USER_LOGIN);
        requestStatsEntity.setUserLogin(USER_LOGIN);
        requestStatsEntity.setCountOfRequests(COUNT_OF_REQUESTS);
        return requestStatsEntity;
    }

}
