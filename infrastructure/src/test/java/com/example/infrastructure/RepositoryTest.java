package com.example.infrastructure;

import com.example.domain.requestcount.domain.RequestCount;
import com.example.domain.requestcount.infrastructure.RequestCountRepository;
import com.example.infrastructure.jparepository.RequestCountEntity;
import com.example.infrastructure.jparepository.RequestCountJpaRepository;
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
public class RepositoryTest {
    private static final String USER_LOGIN = "testUser";
    private static final String USER_LOGIN_NOT_PRESENT = "new user";

    @Autowired
    RequestCountRepository requestCountRepository;

    @Autowired
    RequestCountJpaRepository jpaRepository;

    @BeforeEach
    void setUp() {
        RequestCountEntity requestCountEntity = new RequestCountEntity();
        requestCountEntity.setUserLogin(USER_LOGIN);
        requestCountEntity.setCountOfRequests(1L);
        jpaRepository.save(requestCountEntity);

    }

    @Test
    void shouldWorkIfNotPresent() {
        Optional<RequestCount> result = requestCountRepository.findRequestCountByLoginName(USER_LOGIN_NOT_PRESENT);
        assertThat(result).isNotPresent();
    }

    @Test
    void shouldFindRecordByUserLogin() {
        Optional<RequestCount> result = requestCountRepository.findRequestCountByLoginName(USER_LOGIN);
        assertThat(result).isPresent();
        assertThat(result.get().getLoginName()).isEqualTo(USER_LOGIN);
        assertThat(result.get().getCountOfRequests()).isEqualTo(1L);
        assertThat(result.get().getCountOfRequests()).isNotNegative();
        assertThat(result.get().getId()).isNotNull();
    }

    @Test
    void shouldReturnExceptionIfSaveTheRequestCountThatExists() {
        RequestCount requestCount = new RequestCount(USER_LOGIN) ;
        Throwable throwable = catchThrowable(() -> requestCountRepository.saveOrUpdateRequestCount(requestCount));
        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(DataIntegrityViolationException.class);
    }


    @Test
    void shouldUpdateIfRecordExists() {
        RequestCount requestCount = requestCountRepository.findRequestCountByLoginName(USER_LOGIN).get();
        requestCount.addRequestToCount();
        requestCountRepository.saveOrUpdateRequestCount(requestCount);
        Optional<RequestCountEntity> requestCountEntity = jpaRepository.findByUserLogin(USER_LOGIN);
        assertThat(requestCountEntity).isPresent();
        assertThat(requestCountEntity.get().getUserLogin()).isEqualTo(USER_LOGIN);
        assertThat(requestCountEntity.get().getCountOfRequests()).isEqualTo(2L);
    }

    @Test
    void shouldCreateRecordIfIdIsNull() {
        RequestCount requestCount = new RequestCount(USER_LOGIN_NOT_PRESENT);
        requestCountRepository.saveOrUpdateRequestCount(requestCount);
        Optional<RequestCountEntity> requestCountEntity = jpaRepository.findByUserLogin(USER_LOGIN_NOT_PRESENT);
        assertThat(requestCountEntity).isPresent();
        assertThat(requestCountEntity.get().getUserLogin()).isEqualTo(USER_LOGIN_NOT_PRESENT);
        assertThat(requestCountEntity.get().getCountOfRequests()).isEqualTo(1L);
    }

}
