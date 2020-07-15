package com.example.app.controllers;

import com.example.domain.githubdata.presentation.GithubDetailsCalculated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping(value = "/{login}", produces = "application/json")
    @ResponseBody
    public GithubDetailsCalculated getUserGithubDetails(@PathVariable("login") String userLogin)
            throws UnsupportedEncodingException {
        return null;
    }

}
