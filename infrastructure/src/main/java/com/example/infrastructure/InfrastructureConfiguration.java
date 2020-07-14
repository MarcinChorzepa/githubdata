package com.example.infrastructure;

import com.example.domain.statistic.infrastructure.StatisticRepository;
import com.example.infrastructure.integration.StatisticRepositoryImpl;
import com.example.infrastructure.jparepository.StatisticJpaRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.example.infrastructure.jparepository")
@EntityScan("com.example.infrastructure.jparepository")
public class InfrastructureConfiguration {

    @Bean
    public StatisticRepository requestCountRepository(StatisticJpaRepository statisticJpaRepository) {
        return new StatisticRepositoryImpl(statisticJpaRepository);
    }
}
