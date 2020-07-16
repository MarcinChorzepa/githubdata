package com.example.infrastructure.integration.requeststats;

import com.example.domain.ststatsrequests.domain.RequestStats;
import com.example.domain.ststatsrequests.infrastructure.RequestStatsRepository;
import com.example.infrastructure.jparepository.RequestStatsEntity;
import com.example.infrastructure.jparepository.RequestStatsJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RequestStatsRepositoryImpl implements RequestStatsRepository {
    private final RequestStatsJpaRepository requestStatsJpaRepository;

    @Override
    public Optional<RequestStats> getStatsByLoginName(String loginName) {
        Optional<RequestStatsEntity> requestCountEntity = requestStatsJpaRepository.findByUserLogin(loginName);
        return requestCountEntity.map(RequestStatsMapper::fromEntity);
    }

    @Override
    public void saveOrUpdateStats(String loginName) {
            if(requestStatsJpaRepository.existsByUserLogin(loginName)) {
                requestStatsJpaRepository.updateTheStatistics(loginName);
            } else {
                requestStatsJpaRepository.save(new RequestStatsEntity(loginName));
            }
        }
}
