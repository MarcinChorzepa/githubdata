package com.example.app.services;

import com.example.app.dto.RequestStatsResponse;
import com.example.domain.StatsRequestsDomainService;
import com.example.domain.ststatsrequests.presentation.RequestStatsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppStatisticsService {

    private final StatsRequestsDomainService statsRequestsDomainService;

    @Async
    void runStatistics(String loginName) {
        statsRequestsDomainService.saveStatisticsInDB(loginName);
    }

    public RequestStatsResponse getStatisticsOfUserRequests(String loginName) throws RequestStatsException {
        return new RequestStatsResponse(statsRequestsDomainService.getStatsByLoginName(loginName));
    }
}
