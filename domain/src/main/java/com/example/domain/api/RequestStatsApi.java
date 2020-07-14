package com.example.domain.api;

import com.example.domain.requeststats.domain.RequestStats;
import com.example.domain.requeststats.domain.RequestStatsException;
import com.example.domain.requeststats.infrastructure.RequestStatsRepository;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
public class RequestStatsApi {

    private final RequestStatsRepository requestStatsRepository;

    @Transactional
    public void saveStatisticsInDB(String loginName) throws RequestStatsException {
        RequestStats requestStats = requestStatsRepository.findRequestCountByLoginName(loginName)
                .orElse(new RequestStats(loginName));
        requestStats.addRequestToCount();
        requestStatsRepository.saveOrUpdateRequestCount(requestStats);
    }
}
