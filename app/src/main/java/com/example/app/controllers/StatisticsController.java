package com.example.app.controllers;

import com.example.app.dto.RequestStatsResponse;
import com.example.app.services.AppService;
import com.example.domain.ststatsrequests.presentation.RequestStatsException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final AppService appService;

    @GetMapping(value = "/{login}", produces = "application/json")
    @ResponseBody
    public RequestStatsResponse getUserStatistics(@PathVariable("login") String userLogin)
            throws UnsupportedEncodingException, RequestStatsException {
        return appService.getStatisticsOfUserRequests(URLDecoder.decode(userLogin, "UTF-8"));
    }
}
