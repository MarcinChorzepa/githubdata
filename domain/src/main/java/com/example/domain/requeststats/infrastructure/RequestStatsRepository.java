package com.example.domain.requeststats.infrastructure;

import com.example.domain.requeststats.domain.RequestStats;
import com.example.domain.requeststats.domain.RequestStatsException;

import java.util.Optional;

public interface RequestStatsRepository {

    Optional<RequestStats> findRequestCountByLoginName(String loginName);

    void saveOrUpdateRequestCount(RequestStats requestStats) throws RequestStatsException;
}
