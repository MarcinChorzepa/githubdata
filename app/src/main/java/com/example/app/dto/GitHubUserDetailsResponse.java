package com.example.app.dto;

import com.example.domain.githubdata.presentation.GithubDetailsCalculated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GitHubUserDetailsResponse {

    private String avatarUrl;
    private String createdAt;
    private Long id;
    private String login;
    private String name;
    private String type;
    private double calculations;

    public GitHubUserDetailsResponse(GithubDetailsCalculated domainObject) {
        this.avatarUrl = domainObject.getAvatarUrl();
        this.createdAt = domainObject.getCreatedAt().toString();
        this.id = domainObject.getId();
        this.login = domainObject.getLogin();
        this.name = domainObject.getName();
        this.type = domainObject.getType();
        this.calculations = domainObject.getCalculations();
    }
}
