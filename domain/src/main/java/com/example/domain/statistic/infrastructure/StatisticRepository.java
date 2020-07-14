package com.example.domain.statistic.infrastructure;

import com.example.domain.statistic.domain.Statistic;

import java.util.Optional;

public interface StatisticRepository {

    Optional<Statistic> findRequestCountByLoginName(String loginName);

    void saveOrUpdateRequestCount(Statistic statistic);
}
