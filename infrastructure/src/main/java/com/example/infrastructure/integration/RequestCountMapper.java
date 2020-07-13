package com.example.infrastructure.integration;

import com.example.domain.requestcount.domain.RequestCount;
import com.example.infrastructure.jparepository.RequestCountEntity;

public class RequestCountMapper {

    private RequestCountMapper() {
        throw new IllegalStateException("RequestCountMapper");
    }

    public static RequestCount fromEntity(RequestCountEntity requestCountEntity) {
        return new RequestCount(requestCountEntity.getId(),
                requestCountEntity.getUserLogin(),
                requestCountEntity.getCountOfRequests());
    }

    public static RequestCountEntity toEntity(RequestCount requestCount) {
        RequestCountEntity requestCountEntity = new RequestCountEntity();
        requestCountEntity.setUserLogin(requestCount.getLoginName());
        requestCountEntity.setCountOfRequests(requestCount.getCountOfRequests());
        requestCountEntity.setId(requestCount.getId());
        return requestCountEntity;
    }

    public static void mapJpaFields(RequestCount requestCount, RequestCountEntity requestCountEntity) {
        requestCountEntity.setUserLogin(requestCount.getLoginName());
        requestCountEntity.setCountOfRequests(requestCount.getCountOfRequests());
        requestCountEntity.setId(requestCount.getId());
    }
}
