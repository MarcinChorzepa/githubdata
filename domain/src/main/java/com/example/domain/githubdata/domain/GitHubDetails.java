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
