package com.example.infrastructure;

import com.example.infrastructure.jparepository.RequestCountEntity;
import com.example.infrastructure.jparepository.RequestCountJpaRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {InfrastructureConfiguration.class})
@DataJpaTest
@AutoConfigureEmbeddedDatabase
@ActiveProfiles({"test"})
public class RepositoryTest {

    @Autowired
    RequestCountJpaRepository requestCountJpaRepository;

    @Test
    void testRepository() {
       long list =  requestCountJpaRepository.count();
    }
}
