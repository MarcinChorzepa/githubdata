package com.example.domain.requestcount.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RequestCount {

    private Long id;
    private String loginName;
    private long countOfRequests;

    public RequestCount(String loginName) {
        this.loginName = loginName;
        this.countOfRequests = 1L;
    }

    public RequestCount addRequestToCount() {
        this.countOfRequests ++;
        return this;
    }

}
