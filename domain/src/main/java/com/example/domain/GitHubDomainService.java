package com.example.domain;

import com.example.domain.githubdata.infrastructure.GitHubDetailsRepository;
import com.example.domain.githubdata.presentation.GitHubDetailsException;
import com.example.domain.githubdata.presentation.GithubDetailsCalculated;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GitHubDomainService {
    private final GitHubDetailsRepository gitHubDetailsRepository;

    public GithubDetailsCalculated getUserDetailsFormGithub(String loginName) throws GitHubDetailsException {
        try {
            return GithubDetailsCalculated.factory(gitHubDetailsRepository.getGithubDetails(loginName));
        } catch (Exception e) {
            throw new GitHubDetailsException("Could not get data from github",e.getCause());
        }
    }
}
