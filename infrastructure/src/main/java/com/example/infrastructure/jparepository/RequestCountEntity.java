package com.example.infrastructure.jparepository;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "requests_count")
public class RequestCountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_login")
    private String userLogin;

    @Column(name = "count_of_requests")
    private long countOfRequests;

    @Version
    private Integer version;
}
