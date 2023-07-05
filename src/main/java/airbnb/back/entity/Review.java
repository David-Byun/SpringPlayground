package airbnb.back.entity;

import airbnb.back.entity.user.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Review extends BaseTimeEntity {
    private long review_id;
    private int score;
    private String content;
    private Status status;
    private User user;
    private Room room;
    private Reservation reservation;

    //리뷰이미지 - 리뷰 N : 1관계
    private List<ReviewImage> reviewImages = new ArrayList<>();

    //post할 경우에만 request 필요해서 toEntity 필요

    @Builder
    public Review(int score, String content, User user, Room room, Reservation reservation, Status status) {
        this.user = user;
        if (user != null) {
            user.addReview(this);
        }

        this.reservation = reservation;
        if (reservation != null) {
            reservation.addReview(this);
        }

        this.room = room;
        if (room != null) {
            room.addReview(this);
        }

        this.score = score;
        this.content = content;
        this.status = status;
    }

    public void changeStatus() {
        this.status = Status.INACTIVE;
    }

    public void updateScore(int score) {
        this.score = score;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
