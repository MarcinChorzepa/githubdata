package com.example.app.dto;

import com.example.domain.ststatsrequests.domain.RequestStats;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestStatsResponse {
    private String loginName;
    private Long countOfRequests;

    public RequestStatsResponse(RequestStats requestStats) {
        this.loginName = requestStats.getLoginName();
        this.countOfRequests = requestStats.getCountOfRequests();
    }
}
