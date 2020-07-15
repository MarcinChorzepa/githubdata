package com.example.app.controllers;

import com.example.app.services.AppService;
import com.example.domain.githubdata.presentation.GitHubDetailsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final AppService appService;

    @GetMapping(value = "/{login}", produces = "application/json")
    public ResponseEntity<GitHubUserDetailsResponse> getUserGithubDetails(@PathVariable("login") String userLogin)
            throws GitHubDetailsException, UnsupportedEncodingException {
        GitHubUserDetailsResponse result =  appService.getUserDetailsFromGithub(URLDecoder.decode(userLogin, "UTF-8"));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
