package com.example.infrastructure;

import com.example.domain.requeststats.infrastructure.RequestStatsRepository;
import com.example.infrastructure.integration.RequestStatsRepositoryImpl;
import com.example.infrastructure.jparepository.RequestStatsJpaRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.example.infrastructure.jparepository")
@EntityScan("com.example.infrastructure.jparepository")
public class InfrastructureConfiguration {

    @Bean
    public RequestStatsRepository requestCountRepository(RequestStatsJpaRepository requestStatsJpaRepository) {
        return new RequestStatsRepositoryImpl(requestStatsJpaRepository);
    }
}
