package com.example.infrastructure.integration.github;

import com.example.domain.githubdata.domain.GitHubDetails;
import com.example.infrastructure.githubservice.GitHubResponseEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class GitHubResponseMapperTest {
    private static final String AVATAR_URL = "http://avatar";
    private static final String CREATED_AT = "2015-09-08T19:51:37Z";
    private static final LocalDateTime LOCAL_CREATED_AT = LocalDateTime.of(2015, 9, 8, 19, 51, 37);
    private static final String ID = "1";
    private static final String USER_LOGIN = "testUser";
    private static final String USER_NAME = "name";
    private static final String TYPE = "type";
    private static final int PUBLIC_REPOS = 15;
    private static final int FOLLOWERS = 0;

    @Test
    void shouldBeAbleToMapFieldsToDomainObject() {
        GitHubDetails result = GitHubResponseMapper.fromEntity(prepareGitHubJSON());
        assertThat(result).isNotNull();
        assertThat(result.getAvatarUrl()).isEqualTo(AVATAR_URL);
        assertThat(result.getCreatedAt()).isEqualTo(LOCAL_CREATED_AT);
        assertThat(result.getId()).isEqualTo(Long.valueOf(ID));
        assertThat(result.getLogin()).isEqualTo(USER_LOGIN);
        assertThat(result.getName()).isEqualTo(USER_NAME);
        assertThat(result.getType()).isEqualTo(TYPE);
        assertThat(result.getPublicRepos()).isEqualTo(PUBLIC_REPOS);
        assertThat(result.getFollowers()).isEqualTo(FOLLOWERS);
    }

    private GitHubResponseEntity prepareGitHubJSON() {
        return GitHubResponseEntity.builder()
                .avatarUrl(AVATAR_URL)
                .createdAt(CREATED_AT)
                .id(ID)
                .login(USER_LOGIN)
                .name(USER_NAME)
                .type(TYPE)
                .publicRepos(PUBLIC_REPOS)
                .followers(FOLLOWERS)
                .build();
    }
}