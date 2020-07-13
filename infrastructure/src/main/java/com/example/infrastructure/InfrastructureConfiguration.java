package com.example.infrastructure;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.example.infrastructure.jparepository")
@EntityScan("com.example.infrastructure.jparepository")
public class InfrastructureConfiguration {
}
