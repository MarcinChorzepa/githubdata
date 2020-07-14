package com.example.infrastructure.githubservice;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubService {

    @Value("${github.endpoint:https://api.github.com/users/}")
    private String githubUrl;

    public GitHubResponseJSON getGithubDetails(String loginName) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
                = restTemplate.getForEntity(githubUrl + loginName, String.class);
        return parseResponseToObject(response);
    }

    @SneakyThrows
    private GitHubResponseJSON parseResponseToObject(ResponseEntity<String> response) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(response.getBody(), GitHubResponseJSON.class);
    }
}
