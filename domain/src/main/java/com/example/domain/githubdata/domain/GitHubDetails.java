package com.example.domain.githubdata.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class GitHubDetails {

    private final String avatarUrl;
    private final LocalDateTime createdAt;
    private final Long id;
    private final String login;
    private final String name;
    private final String type;
    private final int publicRepos;
    private final int followers;

}
