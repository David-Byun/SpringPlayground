package airbnb.back.entity;

import airbnb.back.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private long id;
    private String reservationCode;
    private int totalPrice;
    private int totalGuest;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Status status;
    private boolean isReviewCreated;
    private Room room;
    private User user;
    private Review review;

}
