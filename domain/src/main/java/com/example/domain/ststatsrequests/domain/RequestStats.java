package com.example.domain.ststatsrequests.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestStats {
    private final int id;
    private final String loginName;
    private final long countOfRequests;

}
