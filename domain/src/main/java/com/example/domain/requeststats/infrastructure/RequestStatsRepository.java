package com.example.domain.requeststats.infrastructure;

import com.example.domain.requeststats.domain.RequestStats;
import java.util.Optional;

public interface RequestStatsRepository {

    Optional<RequestStats> getStatsByLoginName(String loginName);

    void saveOrUpdateStats(String loginName);
}
