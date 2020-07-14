package com.example.domain.githubdata.infrastructure;

import com.example.domain.githubdata.domain.GitHubDetails;

public interface GitHubDetailsRepository {
    GitHubDetails getGithubDetails(String loginName);
}
