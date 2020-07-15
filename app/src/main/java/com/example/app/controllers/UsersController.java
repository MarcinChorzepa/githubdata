package com.example.app.controllers;

import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
@RequestMapping("/users")
public class UsersController {

//    @GetMapping(value = "/{login}", produces = "application/json")
//    @ResponseBody
//    public UserGithubDetailsResponse getUserGithubDetails(@PathVariable("login") @Valid @NotBlank String userLogin)
//            throws UnsupportedEncodingException {
//        return new UserGithubDetailsResponse(URLDecoder.decode(userLogin,"UFT-8"));
//    }

}
