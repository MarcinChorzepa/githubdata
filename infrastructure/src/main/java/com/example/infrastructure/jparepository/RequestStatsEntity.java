package com.example.infrastructure.jparepository;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "request_stats")
public class RequestStatsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_login", unique = true)
    private String userLogin;

    @Column(name = "count_of_requests")
    private long countOfRequests;

    @Version
    private Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestStatsEntity that = (RequestStatsEntity) o;
        return countOfRequests == that.countOfRequests &&
                id.equals(that.id) &&
                userLogin.equals(that.userLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userLogin, countOfRequests);
    }
}