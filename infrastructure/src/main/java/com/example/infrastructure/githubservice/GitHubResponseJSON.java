package com.example.infrastructure.githubservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * class used as entity to get data from github
 */
@Data
@Builder
public class GitHubResponseJSON implements Serializable{

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

    /*
    *             "login":"MarcinChorzepa",
            "id":14186612,
            "node_id":"MDQ6VXNlcjE0MTg2NjEy",
            "avatar_url":"https://avatars3.githubusercontent.com/u/14186612?v=4",
            "gravatar_id":"",
            "url":"https://api.github.com/users/MarcinChorzepa",
            "html_url":"https://github.com/MarcinChorzepa",
            "followers_url":"https://api.github.com/users/MarcinChorzepa/followers",
            "following_url":"https://api.github.com/users/MarcinChorzepa/following{/other_user}",
            "gists_url":"https://api.github.com/users/MarcinChorzepa/gists{/gist_id}",
            "starred_url":"https://api.github.com/users/MarcinChorzepa/starred{/owner}{/repo}",
            "subscriptions_url":"https://api.github.com/users/MarcinChorzepa/subscriptions",
            "organizations_url":"https://api.github.com/users/MarcinChorzepa/orgs",
            "repos_url":"https://api.github.com/users/MarcinChorzepa/repos",
            "events_url":"https://api.github.com/users/MarcinChorzepa/events{/privacy}",
            "received_events_url":"https://api.github.com/users/MarcinChorzepa/received_events",
            "type":"User",
            "site_admin":false,
            "name":"Marcin",
            "company":null,
            "blog":"",
            "location":null,
            "email":null,
            "hireable":null,
            "bio":null,
            "twitter_username":null,
            "public_repos":19,
            "public_gists":0,
            "followers":0,
            "following":0,
            "created_at":"2015-09-08T19:51:37Z",
            "updated_at":"2020-05-29T19:02:18Z"*/

