package com.example.app.config;

import com.example.domain.GitHubDomainService;
import com.example.domain.StatsRequestsDomainService;
import com.example.domain.githubdata.infrastructure.GitHubDetailsRepository;
import com.example.domain.ststatsrequests.infrastructure.RequestStatsRepository;
import com.example.infrastructure.InfrastructureConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({InfrastructureConfiguration.class})
public class DomainConfiguration {

    @Bean
    public GitHubDomainService gitHubDomainService(GitHubDetailsRepository gitHubDetailsRepository) {
        return new GitHubDomainService(gitHubDetailsRepository);
    }

    @Bean
    public StatsRequestsDomainService requestStatsDomainService(RequestStatsRepository requestStatsRepository) {
        return new StatsRequestsDomainService(requestStatsRepository);
    }
}
