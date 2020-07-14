package com.example.domain.requeststats.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestStatsTest {

    private static final String USER_LOGIN = "TestUser";
    private static final long COUNT_OF_REQUESTS = 1L;

    private RequestStats requestStats;

    @BeforeEach
    void setUp() {
        requestStats = new RequestStats(USER_LOGIN);
    }

    @Test
    void shouldBeAbleToCreateRequestCount() {
        assertNotNull(requestStats);
        assertEquals(requestStats.getCountOfRequests(), COUNT_OF_REQUESTS);
    }

    @Test
    void shouldBeAbleToAddRequestToTotalCount() {
        requestStats.addRequestToCount();
        assertEquals(requestStats.getCountOfRequests(), (COUNT_OF_REQUESTS + 1));
    }

}