package com.example.demo.User;

import com.example.demo.BaseTimeEntity;
import com.example.demo.Favorite.Favorite;
import com.example.demo.Reservation.Reservation;
import com.example.demo.Review.Review;
import com.example.demo.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = AccessLevel.PROTECTED)
    @Column(name = "user_id")
    private Long id;

    private String profileImage;

    @Column(length = 45)
    private String username;

    @Column(length = 45)
    private String nickname;

    @Column(length = 45)
    private String birth;

    @Column(length = 45, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Favorite> favorites = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Reservation> reservations = new ArrayList<>();

    private boolean privacyAgreement;
    private boolean marketingAgreement;
    private boolean hostPermission;



    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

}
