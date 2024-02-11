package com.example.demo.Reservation;

import com.example.demo.BaseTimeEntity;
import com.example.demo.Common.Status;
import com.example.demo.Review.Review;
import com.example.demo.Room.Room;
import com.example.demo.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Column(nullable = false)
    private String reservationCode;

    @Column(nullable = false)
    private int totalPrice;

    @Column(nullable = false)
    private int totalGuest;

    @Column(nullable = false)
    private int totalPet;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private boolean isReviewCreated;

    @Enumerated(EnumType.STRING)
    @Column(length = 45, nullable = false)
    private Status status;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private Room room;

    //reservation 삭제할때 review가 고아가 되므로 고아를 삭제하다는 의미
    @OneToOne(mappedBy = "reservation", orphanRemoval = true)
    private Review review;

    @Builder
    public Reservation(String reservationCode, int totalPrice, int totalGuest, int totalPet, LocalDateTime startDate, LocalDateTime endDate, boolean isReviewCreated, Status status, User user, Room room) {
        this.reservationCode = reservationCode;
        this.totalPrice = totalPrice;
        this.totalGuest = totalGuest;
        this.totalPet = totalPet;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isReviewCreated = isReviewCreated;
        this.status = status;
        this.user = user;
        this.room = room;
        user.getReservations().add(this); // 연관 관계 설정
        room.getReservations().add(this); // 연관 관계 설정
    }

    public void deleteReservation() {

    }
}
