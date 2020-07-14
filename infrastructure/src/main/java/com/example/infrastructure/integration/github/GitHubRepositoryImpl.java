package com.example.infrastructure.integration.github;

import com.example.domain.githubdata.domain.GitHubDetails;
import com.example.domain.githubdata.infrastructure.GitHubDetailsRepository;
import com.example.infrastructure.githubservice.GitHubService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GitHubRepositoryImpl implements GitHubDetailsRepository {
    private final GitHubService gitHubService;

    @Override
    public GitHubDetails getGithubDetails(String loginName) {
        return null;
    }
}
