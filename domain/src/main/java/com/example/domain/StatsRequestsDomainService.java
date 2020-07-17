package com.example.domain;

import com.example.domain.ststatsrequests.domain.RequestStats;
import com.example.domain.ststatsrequests.presentation.RequestStatsException;
import com.example.domain.ststatsrequests.infrastructure.RequestStatsRepository;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
public class StatsRequestsDomainService {

    private final RequestStatsRepository requestStatsRepository;


    @Transactional
    public void saveStatisticsInDB(String loginName) {
        requestStatsRepository.updateTheStatistics(loginName);
    }

    @Transactional
    public RequestStats getStatsByLoginName(String loginName) throws RequestStatsException {
        return requestStatsRepository.getStatsByLoginName(loginName)
                .orElseThrow(() -> new RequestStatsException("Record does not exists"));
    }

    @Transactional
    public void createNewLoginInStatistics(String loginName) {
        requestStatsRepository.createNewLoginInStatistics(loginName);
    }
}
