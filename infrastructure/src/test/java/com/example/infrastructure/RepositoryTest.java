package com.example.infrastructure;

import com.example.domain.statistic.domain.Statistic;
import com.example.domain.statistic.infrastructure.StatisticRepository;
import com.example.infrastructure.jparepository.StatisticEntity;
import com.example.infrastructure.jparepository.StatisticJpaRepository;
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
    StatisticRepository statisticRepository;

    @Autowired
    StatisticJpaRepository jpaRepository;

    @BeforeEach
    void setUp() {
        StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setUserLogin(USER_LOGIN);
        statisticEntity.setCountOfRequests(1L);
        jpaRepository.save(statisticEntity);

    }

    @Test
    void shouldWorkIfNotPresent() {
        Optional<Statistic> result = statisticRepository.findRequestCountByLoginName(USER_LOGIN_NOT_PRESENT);
        assertThat(result).isNotPresent();
    }

    @Test
    void shouldFindRecordByUserLogin() {
        Optional<Statistic> result = statisticRepository.findRequestCountByLoginName(USER_LOGIN);
        assertThat(result).isPresent();
        assertThat(result.get().getLoginName()).isEqualTo(USER_LOGIN);
        assertThat(result.get().getCountOfRequests()).isEqualTo(1L);
        assertThat(result.get().getCountOfRequests()).isNotNegative();
        assertThat(result.get().getId()).isNotNull();
    }

    @Test
    void shouldReturnExceptionIfSaveTheRequestCountThatExists() {
        Statistic statistic = new Statistic(USER_LOGIN) ;
        Throwable throwable = catchThrowable(() -> statisticRepository.saveOrUpdateRequestCount(statistic));
        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(DataIntegrityViolationException.class);
    }


    @Test
    void shouldUpdateIfRecordExists() {
        Statistic statistic = statisticRepository.findRequestCountByLoginName(USER_LOGIN).get();
        statistic.addRequestToCount();
        statisticRepository.saveOrUpdateRequestCount(statistic);
        Optional<StatisticEntity> requestCountEntity = jpaRepository.findByUserLogin(USER_LOGIN);
        assertThat(requestCountEntity).isPresent();
        assertThat(requestCountEntity.get().getUserLogin()).isEqualTo(USER_LOGIN);
        assertThat(requestCountEntity.get().getCountOfRequests()).isEqualTo(2L);
    }

    @Test
    void shouldCreateRecordIfIdIsNull() {
        Statistic statistic = new Statistic(USER_LOGIN_NOT_PRESENT);
        statisticRepository.saveOrUpdateRequestCount(statistic);
        Optional<StatisticEntity> requestCountEntity = jpaRepository.findByUserLogin(USER_LOGIN_NOT_PRESENT);
        assertThat(requestCountEntity).isPresent();
        assertThat(requestCountEntity.get().getUserLogin()).isEqualTo(USER_LOGIN_NOT_PRESENT);
        assertThat(requestCountEntity.get().getCountOfRequests()).isEqualTo(1L);
    }

}
