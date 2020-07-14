package com.example.infrastructure.githubservice;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.RequestListener;
import com.github.tomakehurst.wiremock.http.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {GitHubService.class})
@ActiveProfiles("test")
class GitHubServiceTest {
    private static final String LOGIN_NAME = "TestUser";

    private WireMockServer wireMockServer;

    @Autowired
    private GitHubService gitHubService;

    @BeforeEach
    void setUpWireMockServer() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
    }

    @AfterEach
    void stopWireMockServer() {
        wireMockServer.stop();
    }

    @Test
    void shouldBeAbleToReceiveDataFromEndpoint() {
        this.wireMockServer.stubFor(
                WireMock.get(urlPathMatching("/users/([a-z]*)"))
                        .willReturn(aResponse()
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(prepareExpectedResponse())));
        GitHubResponseJSON json = gitHubService.getGithubDetails(LOGIN_NAME);
        assertThat(json).isNotNull();
        assertThat(json.getLogin()).isEqualTo(LOGIN_NAME);
    }

    private String prepareExpectedResponse() {
        return String.format("{\"login\":\"%s\",\"id\":14186612,\"node_id\":\"MDQ6VXNlcjE0MTg2NjEy\"," +
                "\"avatar_url\":\"https://avatars3.githubusercontent.com/u/14186612?v=4\",\"gravatar_id\":\"\"" +
                ",\"url\":\"https://api.github.com/users/MarcinChorzepa\",\"html_url\":\"https://github.com/MarcinChorzepa\"" +
                ",\"followers_url\":\"https://api.github.com/users/MarcinChorzepa/followers\"," +
                "\"following_url\":\"https://api.github.com/users/MarcinChorzepa/following{/other_user}\"," +
                "\"gists_url\":\"https://api.github.com/users/MarcinChorzepa/gists{/gist_id}\"," +
                "\"starred_url\":\"https://api.github.com/users/MarcinChorzepa/starred{/owner}{/repo}\"," +
                "\"subscriptions_url\":\"https://api.github.com/users/MarcinChorzepa/subscriptions\"," +
                "\"organizations_url\":\"https://api.github.com/users/MarcinChorzepa/orgs\"," +
                "\"repos_url\":\"https://api.github.com/users/MarcinChorzepa/repos\"," +
                "\"events_url\":\"https://api.github.com/users/MarcinChorzepa/events{/privacy}\"," +
                "\"received_events_url\":\"https://api.github.com/users/MarcinChorzepa/received_events\",\"type\":\"User\"," +
                "\"site_admin\":false,\"name\":\"Marcin\",\"company\":null,\"blog\":\"\",\"location\":null,\"email\":null," +
                "\"hireable\":null,\"bio\":null,\"twitter_username\":null,\"public_repos\":19,\"public_gists\":0," +
                "\"followers\":0,\"following\":0,\"created_at\":\"2015-09-08T19:51:37Z\"," +
                "\"updated_at\":\"2020-05-29T19:02:18Z\"}",LOGIN_NAME);
    }

}