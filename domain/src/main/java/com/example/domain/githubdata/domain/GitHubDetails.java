package com.example.domain.githubdata.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class GitHubDetails {

    private String avatarUrl;
    private String calculations;
    private LocalDateTime createdAt;
    private Long id;
    private String login;
    private String name;
    private String type;
    private int publicRepos;
    private int followers;
}

/*
*   "login": "MarcinChorzepa",
    "id": 14186612,
    "created_at": "2015-09-08T19:51:37Z",
    "avatar_url": "https://avatars3.githubusercontent.com/u/14186612?v=4",
    "name": "Marcin",
    "type": "User",
    *    "public_repos": 17,
    *     "followers": 0,
    * zwrócony wynik działania 6 / liczba_followers * (2 + liczba_public_repos).

* */
