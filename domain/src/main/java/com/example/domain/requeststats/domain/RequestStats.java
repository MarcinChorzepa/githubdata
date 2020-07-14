package com.example.domain.requeststats.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RequestStats {

    private Long id;
    private String loginName;
    private long countOfRequests;

    public RequestStats(String loginName) {
        this.loginName = loginName;
        this.countOfRequests = 1L;
    }

    public RequestStats addRequestToCount() {
        this.countOfRequests ++;
        return this;
    }

}
