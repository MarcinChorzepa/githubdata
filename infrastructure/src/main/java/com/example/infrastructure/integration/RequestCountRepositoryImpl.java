package com.example.infrastructure.integration;

import com.example.domain.requestcount.domain.RequestCount;
import com.example.domain.requestcount.infrastructure.RequestCountRepository;
import com.example.infrastructure.jparepository.RequestCountEntity;
import com.example.infrastructure.jparepository.RequestCountJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RequestCountRepositoryImpl implements RequestCountRepository {
    private final RequestCountJpaRepository requestCountJpaRepository;

    @Override
    public Optional<RequestCount> findRequestCountByLoginName(String loginName) {
        Optional<RequestCountEntity> requestCountEntity = requestCountJpaRepository.findByUserLogin(loginName);
        return requestCountEntity.map(RequestCountMapper::fromEntity);
    }

    @Override
    public void saveOrUpdateRequestCount(RequestCount requestCount) {
        if(requestCount.getId()==null) {
            RequestCountEntity requestCountEntity = RequestCountMapper.toEntity(requestCount);
            requestCountJpaRepository.save(requestCountEntity);
        }else {
            Optional<RequestCountEntity> requestCountEntity = requestCountJpaRepository.findById(requestCount.getId());
            requestCountEntity.map(entity -> {
                RequestCountMapper.mapJpaFields(requestCount, entity);
                return entity;
            }).orElseThrow(IllegalArgumentException::new);
        }
    }
}
