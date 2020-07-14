package com.example.infrastructure.integration;

import com.example.domain.requeststats.domain.RequestStats;
import com.example.domain.requeststats.domain.RequestStatsException;
import com.example.domain.requeststats.infrastructure.RequestStatsRepository;
import com.example.infrastructure.jparepository.RequestStatsEntity;
import com.example.infrastructure.jparepository.RequestStatsJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RequestStatsRepositoryImpl implements RequestStatsRepository {
    private final RequestStatsJpaRepository requestStatsJpaRepository;

    @Override
    public Optional<RequestStats> findRequestCountByLoginName(String loginName) {
        Optional<RequestStatsEntity> requestCountEntity = requestStatsJpaRepository.findByUserLogin(loginName);
        return requestCountEntity.map(RequestStatsMapper::fromEntity);
    }

    @Override
    public void saveOrUpdateRequestCount(RequestStats requestStats) throws RequestStatsException {
        if(requestStats.getId()==null) {
            RequestStatsEntity requestStatsEntity = RequestStatsMapper.toEntity(requestStats);
            requestStatsJpaRepository.save(requestStatsEntity);
        }else {
            Optional<RequestStatsEntity> requestCountEntity = requestStatsJpaRepository.findById(requestStats.getId());
            requestCountEntity.map(entity -> {
                RequestStatsMapper.mapJpaFields(requestStats, entity);
                return entity;
            }).orElseThrow(()->new RequestStatsException("Could not save stats in DB"));
        }
    }
}
