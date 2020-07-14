package com.example.infrastructure.integration.github;

import com.example.domain.githubdata.domain.GitHubDetails;
import com.example.infrastructure.githubservice.GitHubResponseJSON;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GitHubResponseMapper {

    private GitHubResponseMapper() {
        throw new IllegalStateException("GitHubResponseMapper");
    }

    public static GitHubDetails fromEntity(GitHubResponseJSON entity) {
        return GitHubDetails.builder()
                .createdAt(toLocalDateTime(entity.getCreatedAt()))
                .avatarUrl(entity.getAvatarUrl())
                .id(Long.valueOf(entity.getId()))
                .login(entity.getLogin())
                .name(entity.getName())
                .type(entity.getType())
                .publicRepos(entity.getPublicRepos())
                .followers(entity.getFollowers())
                .build();
    }

    private static LocalDateTime toLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        return LocalDateTime.parse(date,formatter);
    }
}
