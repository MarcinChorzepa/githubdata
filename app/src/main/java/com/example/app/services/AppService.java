package com.example.app.services;

import com.example.app.dto.GitHubUserDetailsResponse;
import com.example.app.dto.RequestStatsResponse;
import com.example.domain.GitHubDomainService;
import com.example.domain.StatsRequestsDomainService;
import com.example.domain.githubdata.presentation.GitHubDetailsException;
import com.example.domain.ststatsrequests.presentation.RequestStatsException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppService {

    private final GitHubDomainService gitHubDomainService;
    private final StatsRequestsDomainService statsRequestsDomainService;

    public GitHubUserDetailsResponse getUserDetailsFromGithub(String loginName) throws GitHubDetailsException {
        runStatistics(loginName);
        return new GitHubUserDetailsResponse(gitHubDomainService.getUserDetailsFormGithub(loginName));
    }

    @Async
    void runStatistics(String loginName) {
        statsRequestsDomainService.saveStatisticsInDB(loginName);
    }


    public RequestStatsResponse getStatisticsOfUserRequests(String loginName) throws RequestStatsException {
        return new RequestStatsResponse(statsRequestsDomainService.getStatsByLoginName(loginName));
    }
}
