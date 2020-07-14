package com.example.infrastructure.githubservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {GitHubService.class})
class GitHubServiceTest {

    @Autowired
    private GitHubService gitHubService;

    @Test
    void getGithubDetails() {
       GitHubResponseJSON json =  gitHubService.getGithubDetails("MarcinChorzepa");
       assertThat(json).isNotNull();
    }
}