package com.example.infrastructure.integration;

import com.example.domain.statistic.domain.Statistic;
import com.example.domain.statistic.infrastructure.StatisticRepository;
import com.example.infrastructure.jparepository.StatisticEntity;
import com.example.infrastructure.jparepository.StatisticJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class StatisticRepositoryImpl implements StatisticRepository {
    private final StatisticJpaRepository statisticJpaRepository;

    @Override
    public Optional<Statistic> findRequestCountByLoginName(String loginName) {
        Optional<StatisticEntity> requestCountEntity = statisticJpaRepository.findByUserLogin(loginName);
        return requestCountEntity.map(StatisticMapper::fromEntity);
    }

    @Override
    public void saveOrUpdateRequestCount(Statistic statistic) {
        if(statistic.getId()==null) {
            StatisticEntity statisticEntity = StatisticMapper.toEntity(statistic);
            statisticJpaRepository.save(statisticEntity);
        }else {
            Optional<StatisticEntity> requestCountEntity = statisticJpaRepository.findById(statistic.getId());
            requestCountEntity.map(entity -> {
                StatisticMapper.mapJpaFields(statistic, entity);
                return entity;
            }).orElseThrow(IllegalArgumentException::new);
        }
    }
}
