package com.example.infrastructure;

import com.example.domain.requestcount.infrastructure.RequestCountRepository;
import com.example.infrastructure.integration.RequestCountRepositoryImpl;
import com.example.infrastructure.jparepository.RequestCountJpaRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.example.infrastructure.jparepository")
@EntityScan("com.example.infrastructure.jparepository")
public class InfrastructureConfiguration {

    @Bean
    public RequestCountRepository requestCountRepository(RequestCountJpaRepository requestCountJpaRepository) {
        return new RequestCountRepositoryImpl(requestCountJpaRepository);
    }
}
