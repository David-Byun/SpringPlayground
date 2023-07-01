package com.mang.atdd.membership;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;

    @Enumerated(EnumType.STRING)
    private MembershipType membershipType;

    private int point;

    public Membership(String userId, MembershipType membershipType, int point) {
        this.userId = userId;
        this.membershipType = membershipType;
        this.point = point;
    }
}
