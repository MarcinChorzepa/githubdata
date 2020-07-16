package com.example.app.controllers;

import com.example.app.dto.GitHubUserDetailsResponse;
import com.example.app.services.AppGithubService;
import com.example.domain.githubdata.presentation.GitHubDetailsException;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
@RequestMapping("/users")
@Api("users")
@RequiredArgsConstructor
public class UsersController {

    private final AppGithubService appGithubService;

    @GetMapping(value = "/{login}", produces = "application/json")
    @ResponseBody
    public GitHubUserDetailsResponse getUserGithubDetails(@PathVariable("login") String userLogin)
            throws GitHubDetailsException, UnsupportedEncodingException {
        return  appGithubService.getUserDetailsFromGithub(URLDecoder.decode(userLogin, "UTF-8"));
    }

}
