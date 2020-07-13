package com.example.domain.requestcount.infrastructure;

import com.example.domain.requestcount.domain.RequestCount;

import java.util.Optional;

public interface RequestCountRepository {

    Optional<RequestCount> findRequestCountByLoginName(String loginName);

    void saveOrUpdateRequestCount(RequestCount requestCount);
}
