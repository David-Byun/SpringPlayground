package airbnb.back.entity;

import airbnb.back.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @Builder
    public Reservation(String reservationCode, int totalPrice, int totalGuest, LocalDateTime startDate, LocalDateTime endDate, Status status, boolean isReviewCreated, Room room, User user, Review review) {
        this.reservationCode = reservationCode;
        this.totalPrice = totalPrice;
        this.totalGuest = totalGuest;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.isReviewCreated = isReviewCreated;
        this.room = room;
        this.user = user;
        user.getReservations().add(this); // this : reservation 객체
        this.review = review;
        room.getReservation().add(this); // 객체가 생성될때 예약에 추가
    }

    // 삭제 메서드
    public void deleteReservation() {
        if (this.getStatus() != Status.ACTIVE) {
            //exception
        }
        LocalDate startDate = this.getStartDate().toLocalDate();
        LocalDate endDate = this.getEndDate().toLocalDate();

        //isBefore LocalDate 함수
        List<RoomAvailable> roomAvailables = this.getRoom().getRoomAvailables().stream()
                .filter(roomAvailable -> roomAvailable.getAvailableDay().compareTo(startDate) >= 0)
                .filter(roomAvailable -> roomAvailable.getAvailableDay().isBefore(endDate)).collect(Collectors.toList());

        for (RoomAvailable roomAvailable : roomAvailables) {
            if (roomAvailable.getStatus() == Status.ACTIVE) {
                //exception
            }
            roomAvailable.changeStatus(Status.ACTIVE);
        }
        this.changeStatus(Status.INACTIVE);
    }

    public void addReview(Review review) {
        this.review = review;
        this.isReviewCreated = true;
    }

    public void deleteReview() {
        this.review.changeStatus();
    }

    public void changeStatus(Status status) {
        this.status = status;
    }
}
