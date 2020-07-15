package com.example.domain.api;

import com.example.domain.requeststats.domain.RequestStats;
import com.example.domain.requeststats.domain.RequestStatsException;
import com.example.domain.requeststats.infrastructure.RequestStatsRepository;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
public class RequestStatsDomainService {

    private final RequestStatsRepository requestStatsRepository;

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
