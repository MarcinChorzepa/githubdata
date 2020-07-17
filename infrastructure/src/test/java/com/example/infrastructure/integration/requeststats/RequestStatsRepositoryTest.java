package com.example.infrastructure.integration.requeststats;

import com.example.domain.ststatsrequests.domain.RequestStats;
import com.example.domain.ststatsrequests.infrastructure.RequestStatsRepository;
import com.example.infrastructure.InfrastructureConfiguration;
import com.example.infrastructure.jparepository.RequestStatsEntity;
import com.example.infrastructure.jparepository.RequestStatsJpaRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@ContextConfiguration(classes = {InfrastructureConfiguration.class})
@DataJpaTest
@AutoConfigureEmbeddedDatabase
@ActiveProfiles({"test"})
class RequestStatsRepositoryTest {
    private static final String USER_LOGIN = "testUser";
    private static final String USER_LOGIN_NOT_PRESENT = "new user";

    @Autowired
    RequestStatsRepository requestStatsRepository;

    @Autowired
    RequestStatsJpaRepository jpaRepository;

    @BeforeEach
    void setUp() {
        RequestStatsEntity requestStatsEntity = new RequestStatsEntity(USER_LOGIN);
        jpaRepository.save(requestStatsEntity);

    }

    @Test
    void shouldWorkIfNotPresent() {
        Optional<RequestStats> result = requestStatsRepository.getStatsByLoginName(USER_LOGIN_NOT_PRESENT);
        assertThat(result).isNotPresent();
    }

    @Test
    void shouldFindRecordByUserLogin() {
        Optional<RequestStats> result = requestStatsRepository.getStatsByLoginName(USER_LOGIN);
        assertThat(result).isPresent();
        assertThat(result.get().getLoginName()).isEqualTo(USER_LOGIN);
        assertThat(result.get().getCountOfRequests()).isEqualTo(1L);
        assertThat(result.get().getCountOfRequests()).isNotNegative();
    }


    @Test
    void shouldUpdateIfRecordExists() {
        requestStatsRepository.updateTheStatistics(USER_LOGIN);
        Optional<RequestStatsEntity> requestCountEntity = jpaRepository.findById(USER_LOGIN);
        assertThat(requestCountEntity).isPresent();
        assertThat(requestCountEntity.get().getUserLogin()).isEqualTo(USER_LOGIN);
        assertThat(requestCountEntity.get().getCountOfRequests()).isEqualTo(2L);
    }

    @Test
    void shouldNotUpdateRecordWithNotExistingLogin() {
        requestStatsRepository.updateTheStatistics(USER_LOGIN_NOT_PRESENT);
        Optional<RequestStatsEntity> requestCountEntity = jpaRepository.findById(USER_LOGIN_NOT_PRESENT);
        assertThat(requestCountEntity).isNotPresent();
    }

    @Test
    void shouldNotCreateNewIfUserExists() {
        Optional<RequestStatsEntity> requestCountEntity = jpaRepository.findById(USER_LOGIN);
        requestStatsRepository.createNewLoginInStatistics(USER_LOGIN);
        Optional<RequestStatsEntity> requestCountEntityAfter = jpaRepository.findById(USER_LOGIN);
        assertThat(requestCountEntityAfter).isPresent();
        assertThat(requestCountEntityAfter.get().getCountOfRequests()).isEqualTo(requestCountEntity.get().getCountOfRequests());
    }

    @Test
    void shouldCreateNewIfUserDoeNotExists() {
        requestStatsRepository.createNewLoginInStatistics(USER_LOGIN_NOT_PRESENT);
        Optional<RequestStatsEntity> requestCountEntity = jpaRepository.findById(USER_LOGIN_NOT_PRESENT);
        assertThat(requestCountEntity).isPresent();
        assertThat(requestCountEntity.get().getCountOfRequests()).isEqualTo(1L);
    }


}
