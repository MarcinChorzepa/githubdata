package com.example.infrastructure.integration;

import com.example.domain.requeststats.domain.RequestStats;
import com.example.domain.requeststats.domain.RequestStatsException;
import com.example.domain.requeststats.infrastructure.RequestStatsRepository;
import com.example.infrastructure.InfrastructureConfiguration;
import com.example.infrastructure.jparepository.RequestStatsEntity;
import com.example.infrastructure.jparepository.RequestStatsJpaRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.Optional;

@ContextConfiguration(classes = {InfrastructureConfiguration.class})
@DataJpaTest
@AutoConfigureEmbeddedDatabase
@ActiveProfiles({"test"})
class RepositoryTest {
    private static final String USER_LOGIN = "testUser";
    private static final String USER_LOGIN_NOT_PRESENT = "new user";

    @Autowired
    RequestStatsRepository requestStatsRepository;

    @Autowired
    RequestStatsJpaRepository jpaRepository;

    @BeforeEach
    void setUp() {
        RequestStatsEntity requestStatsEntity = new RequestStatsEntity();
        requestStatsEntity.setUserLogin(USER_LOGIN);
        requestStatsEntity.setCountOfRequests(1L);
        jpaRepository.save(requestStatsEntity);

    }

    @Test
    void shouldWorkIfNotPresent() {
        Optional<RequestStats> result = requestStatsRepository.findRequestCountByLoginName(USER_LOGIN_NOT_PRESENT);
        assertThat(result).isNotPresent();
    }

    @Test
    void shouldFindRecordByUserLogin() {
        Optional<RequestStats> result = requestStatsRepository.findRequestCountByLoginName(USER_LOGIN);
        assertThat(result).isPresent();
        assertThat(result.get().getLoginName()).isEqualTo(USER_LOGIN);
        assertThat(result.get().getCountOfRequests()).isEqualTo(1L);
        assertThat(result.get().getCountOfRequests()).isNotNegative();
        assertThat(result.get().getId()).isNotNull();
    }

    @Test
    void shouldReturnExceptionIfSaveTheRequestCountThatExists() {
        RequestStats requestStats = new RequestStats(USER_LOGIN) ;
        Throwable throwable = catchThrowable(() -> requestStatsRepository.saveOrUpdateRequestCount(requestStats));
        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(DataIntegrityViolationException.class);
    }


    @Test
    void shouldUpdateIfRecordExists() throws RequestStatsException {
        RequestStats requestStats = requestStatsRepository.findRequestCountByLoginName(USER_LOGIN).get();
        requestStats.addRequestToCount();
        requestStatsRepository.saveOrUpdateRequestCount(requestStats);
        Optional<RequestStatsEntity> requestCountEntity = jpaRepository.findByUserLogin(USER_LOGIN);
        assertThat(requestCountEntity).isPresent();
        assertThat(requestCountEntity.get().getUserLogin()).isEqualTo(USER_LOGIN);
        assertThat(requestCountEntity.get().getCountOfRequests()).isEqualTo(2L);
    }

    @Test
    void shouldCreateRecordIfIdIsNull() throws RequestStatsException {
        RequestStats requestStats = new RequestStats(USER_LOGIN_NOT_PRESENT);
        requestStatsRepository.saveOrUpdateRequestCount(requestStats);
        Optional<RequestStatsEntity> requestCountEntity = jpaRepository.findByUserLogin(USER_LOGIN_NOT_PRESENT);
        assertThat(requestCountEntity).isPresent();
        assertThat(requestCountEntity.get().getUserLogin()).isEqualTo(USER_LOGIN_NOT_PRESENT);
        assertThat(requestCountEntity.get().getCountOfRequests()).isEqualTo(1L);
    }

}
