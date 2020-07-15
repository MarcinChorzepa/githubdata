package com.example.app;

import com.example.domain.api.GitHubDomainService;
import com.example.domain.api.RequestStatsDomainService;
import com.example.domain.githubdata.infrastructure.GitHubDetailsRepository;
import com.example.domain.requeststats.infrastructure.RequestStatsRepository;
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
    public RequestStatsDomainService requestStatsDomainService(RequestStatsRepository requestStatsRepository) {
        return new RequestStatsDomainService(requestStatsRepository);
    }
}
