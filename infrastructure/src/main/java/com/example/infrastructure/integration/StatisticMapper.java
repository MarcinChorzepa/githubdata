package com.example.infrastructure.integration;

import com.example.domain.statistic.domain.Statistic;
import com.example.infrastructure.jparepository.StatisticEntity;

public class StatisticMapper {

    private StatisticMapper() {
        throw new IllegalStateException("RequestCountMapper");
    }

    public static Statistic fromEntity(StatisticEntity statisticEntity) {
        return new Statistic(statisticEntity.getId(),
                statisticEntity.getUserLogin(),
                statisticEntity.getCountOfRequests());
    }

    public static StatisticEntity toEntity(Statistic statistic) {
        StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setUserLogin(statistic.getLoginName());
        statisticEntity.setCountOfRequests(statistic.getCountOfRequests());
        statisticEntity.setId(statistic.getId());
        return statisticEntity;
    }

    public static void mapJpaFields(Statistic statistic, StatisticEntity statisticEntity) {
        statisticEntity.setUserLogin(statistic.getLoginName());
        statisticEntity.setCountOfRequests(statistic.getCountOfRequests());
        statisticEntity.setId(statistic.getId());
    }
}
