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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_login", unique = true)
    private String userLogin;

    @Column(name = "count_of_requests")
    private long countOfRequests;

    public RequestStatsEntity(String userLogin) {
        this.userLogin = userLogin;
        this.countOfRequests = 1;
    }
}
