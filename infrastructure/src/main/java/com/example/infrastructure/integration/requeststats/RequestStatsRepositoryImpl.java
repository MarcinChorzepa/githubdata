package com.example.infrastructure.integration.requeststats;

import com.example.domain.ststatsrequests.domain.RequestStats;
import com.example.domain.ststatsrequests.infrastructure.RequestStatsRepository;
import com.example.infrastructure.jparepository.RequestStatsEntity;
import com.example.infrastructure.jparepository.RequestStatsJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.PersistenceException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class RequestStatsRepositoryImpl implements RequestStatsRepository {
    private final RequestStatsJpaRepository requestStatsJpaRepository;

    @Override
    public Optional<RequestStats> getStatsByLoginName(String loginName) {
        Optional<RequestStatsEntity> requestCountEntity = requestStatsJpaRepository.findById(loginName);
        return requestCountEntity.map(RequestStatsMapper::fromEntity);
    }

    @Override
    public void updateTheStatistics(String loginName) {
        requestStatsJpaRepository.updateTheStatistics(loginName);
    }

    @Override
    public void createNewLoginInStatistics(String loginName) {
        if (!requestStatsJpaRepository.existsById(loginName)) {
            try {
                requestStatsJpaRepository.save(new RequestStatsEntity(loginName));
            } catch (PersistenceException e) {
                log.info("Record already exists");
            }
        }
    }


}
