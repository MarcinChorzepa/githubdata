package com.example.domain.requestcount.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestCount {

    private final String loginName;
    private long countOfRequests;

    public RequestCount(String loginName) {
        this.loginName = loginName;
        this.countOfRequests = 0L;
    }

    public RequestCount addRequestToCount() {
        this.countOfRequests ++;
        return this;
    }

}
