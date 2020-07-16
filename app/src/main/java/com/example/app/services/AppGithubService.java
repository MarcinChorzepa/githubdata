package com.example.app.services;

import com.example.app.dto.GitHubUserDetailsResponse;
import com.example.domain.GitHubDomainService;
import com.example.domain.githubdata.presentation.GitHubDetailsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppGithubService {

    private final GitHubDomainService gitHubDomainService;
    private final AppStatisticsService appStatisticsService;

    public GitHubUserDetailsResponse getUserDetailsFromGithub(String loginName) throws GitHubDetailsException {
        appStatisticsService.runStatistics(loginName);
        return new GitHubUserDetailsResponse(gitHubDomainService.getUserDetailsFormGithub(loginName));
    }





}
