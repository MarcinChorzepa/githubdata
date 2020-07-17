package com.example.domain.ststatsrequests.infrastructure;

import com.example.domain.ststatsrequests.domain.RequestStats;
import java.util.Optional;

public interface RequestStatsRepository {

    Optional<RequestStats> getStatsByLoginName(String loginName);

    void updateTheStatistics(String loginName);

    void createNewLoginInStatistics(String loginName);
}
