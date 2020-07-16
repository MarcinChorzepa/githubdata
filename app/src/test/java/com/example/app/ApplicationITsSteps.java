package com.example.app;


import com.example.app.dto.GitHubUserDetailsResponse;
import com.example.app.dto.RequestStatsResponse;
import com.example.infrastructure.githubservice.GitHubResponseEntity;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.array;

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ITConfiguration.class)
@TestPropertySource(locations = "classpath:application-IT.properties")
@AutoConfigureEmbeddedDatabase
@RequiredArgsConstructor
public class ApplicationITsSteps {

    private final TestRestTemplate restTemplate ;
    private final CucumberWorld cucumberWorld;

    private  WireMockServer wireMockServer = new WireMockServer() ;


    @When("^User \"([^\"]*)\" read github data$")
    public void readGithubData(String userLogin) {
        wireMockServer.start();
        this.wireMockServer.stubFor(
                WireMock.get(urlPathMatching("/users/([a-z]*)"))
                        .willReturn(aResponse()
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(prepareExpectedResponse(userLogin))));

        ResponseEntity<String> responseEntity = restTemplate
                .getForEntity("/users/" + userLogin, String.class);
        cucumberWorld.gitHubUserDetailsResponse = parseResponseToObject(responseEntity, GitHubUserDetailsResponse.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        wireMockServer.stop();
    }

    @Then("^Response is not null , Login is \"([^\"]*)\" and calculation is equal \"([^\"]*)\"$")
    public void responseLoginAndCalculation(String login, double calculate) {
        assertThat(cucumberWorld.gitHubUserDetailsResponse).isNotNull();
        assertThat(cucumberWorld.gitHubUserDetailsResponse.getLogin()).isEqualTo(login);
        assertThat(cucumberWorld.gitHubUserDetailsResponse.getCalculations()).isEqualTo(calculate);

    }

    @Then("^Count of request for user \"([^\"]*)\" is equals to \"([^\"]*)\" and has been saved in DB")
    public void countOfRequestForUser(String userLogin, Long countOfRequests) {
        ResponseEntity<String> responseEntity = restTemplate
                .getForEntity("/statistics/" + userLogin, String.class);
        cucumberWorld.requestStatsResponse = parseResponseToObject(responseEntity, RequestStatsResponse.class);

        assertThat(cucumberWorld.requestStatsResponse).isNotNull();
        assertThat(cucumberWorld.requestStatsResponse.getLoginName()).isEqualTo(userLogin);
        assertThat(cucumberWorld.requestStatsResponse.getCountOfRequests()).isEqualTo(countOfRequests);

    }

    @SneakyThrows
    private  <T> T parseResponseToObject(ResponseEntity<String> response, Class<T> valueTypeClass) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(response.getBody(), valueTypeClass);
    }

    private String prepareExpectedResponse(String loginName) {
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
                "\"updated_at\":\"2020-05-29T19:02:18Z\"}",loginName);
    }
}
