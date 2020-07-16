package com.example.domain;

import com.example.domain.ststatsrequests.domain.RequestStats;
import com.example.domain.ststatsrequests.presentation.RequestStatsException;
import com.example.domain.ststatsrequests.infrastructure.RequestStatsRepository;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
public class StatsRequestsDomainService {

    private final RequestStatsRepository requestStatsRepository;

    /**
     * method is synchonized to be sure that threads will not try to find user before it is processed by different thread
     * @param loginName
     */
    @Transactional
    public synchronized void saveStatisticsInDB(String loginName) {
        requestStatsRepository.saveOrUpdateStats(loginName);
    }

    @Transactional
    public RequestStats getStatsByLoginName(String loginName) throws RequestStatsException {
        return requestStatsRepository.getStatsByLoginName(loginName)
                .orElseThrow(() -> new RequestStatsException("Record does not exists"));
    }
}
