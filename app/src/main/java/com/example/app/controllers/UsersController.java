package com.example.app.controllers;

import com.example.app.dto.GitHubUserDetailsResponse;
import com.example.app.services.AppService;
import com.example.domain.githubdata.presentation.GitHubDetailsException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final AppService appService;

    @GetMapping(value = "/{login}", produces = "application/json")
    @ResponseBody
    public GitHubUserDetailsResponse getUserGithubDetails(@PathVariable("login") String userLogin)
            throws GitHubDetailsException, UnsupportedEncodingException {
        return  appService.getUserDetailsFromGithub(URLDecoder.decode(userLogin, "UTF-8"));
    }

}
