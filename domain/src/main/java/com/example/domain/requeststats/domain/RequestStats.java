package com.example.domain.requeststats.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestStats {

    private int id;
    private String loginName;
    private long countOfRequests;

}
