package com.example.app.services;

import com.example.app.controllers.GitHubUserDetailsResponse;
import com.example.domain.api.GitHubDomainService;
import com.example.domain.api.RequestStatsDomainService;
import com.example.domain.githubdata.presentation.GitHubDetailsException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppService {

    private final GitHubDomainService gitHubDomainService;
    private final RequestStatsDomainService requestStatsDomainService;

    public GitHubUserDetailsResponse getUserDetailsFromGithub(String loginName) throws GitHubDetailsException {
        runStatistics(loginName);
        return new GitHubUserDetailsResponse(gitHubDomainService.getUserDetailsFormGithub(loginName));
    }

    @Async
    void runStatistics(String loginName) {
        requestStatsDomainService.saveStatisticsInDB(loginName);
    }
}
