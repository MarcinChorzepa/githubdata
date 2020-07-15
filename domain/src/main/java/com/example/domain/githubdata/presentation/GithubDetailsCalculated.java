package com.example.domain.githubdata.presentation;

import com.example.domain.githubdata.domain.GitHubDetails;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class GithubDetailsCalculated {
    private final String avatarUrl;
    private final LocalDateTime createdAt;
    private final Long id;
    private final String login;
    private final String name;
    private final String type;
    private final double calculations;


    public static GithubDetailsCalculated factory(GitHubDetails gitHubDetails) {

        double resultOfcalculation = calculate(gitHubDetails.getPublicRepos(), gitHubDetails.getFollowers());

        return new GithubDetailsCalculated(gitHubDetails.getAvatarUrl(), gitHubDetails.getCreatedAt(), gitHubDetails.getId(),
                gitHubDetails.getLogin(), gitHubDetails.getName(), gitHubDetails.getType(), resultOfcalculation);
    }


    private static double calculate(int publicRepos, int followers) {
        if (followers == 0) {
            return 0.0;
        }
        return 6.0 / followers * (2 + publicRepos);
    }
}
