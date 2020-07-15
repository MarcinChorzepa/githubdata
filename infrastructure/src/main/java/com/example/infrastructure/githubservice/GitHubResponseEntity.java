package com.example.infrastructure.githubservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * class used as entity to get data from github
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GitHubResponseEntity implements Serializable{

    private static final long serialVersionUID = 1312788060345717062L;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("created_at")
    private String createdAt;
    private String id;
    private String login;
    private String name;
    private String type;

    @JsonProperty("public_repos")
    private int publicRepos;
    private int followers;

    }
