package com.example.infrastructure.jparepository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "request_stats")
public class RequestStatsEntity {

    @Id
    @Column(name = "login", unique = true)
    private String userLogin;

    @Column(name = "request_count")
    private long countOfRequests;

    public RequestStatsEntity(String userLogin) {
        this.userLogin = userLogin;
        this.countOfRequests = 1;
    }
}
