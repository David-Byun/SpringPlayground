package com.example.demo.Room;

import com.example.demo.BaseTimeEntity;
import com.example.demo.Common.Address;
import com.example.demo.Common.Status;
import com.example.demo.Favorite.Favorite;
import com.example.demo.Reservation.Reservation;
import com.example.demo.Review.Review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Embedded
    private Address address;

    @Column(length = 45, nullable = false)
    private String roomName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int maxPet;

    @Column(nullable = false)
    private Status status;

    @OneToMany(mappedBy = "room")
    private List<Favorite> favorites = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

}
