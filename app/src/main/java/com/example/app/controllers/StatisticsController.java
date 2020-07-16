package com.example.app.controllers;

import com.example.app.dto.RequestStatsResponse;
import com.example.app.services.AppStatisticsService;
import com.example.domain.ststatsrequests.presentation.RequestStatsException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final AppStatisticsService appStatisticsService;

    @GetMapping(value = "/{login}", produces = "application/json")
    @ResponseBody
    public RequestStatsResponse getUserStatistics(@PathVariable("login") String userLogin)
            throws UnsupportedEncodingException, RequestStatsException {
        return appStatisticsService.getStatisticsOfUserRequests(URLDecoder.decode(userLogin, "UTF-8"));
    }
}
