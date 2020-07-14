package com.example.domain.statistic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Statistic {

    private Long id;
    private String loginName;
    private long countOfRequests;

    public Statistic(String loginName) {
        this.loginName = loginName;
        this.countOfRequests = 1L;
    }

    public Statistic addRequestToCount() {
        this.countOfRequests ++;
        return this;
    }

}
