package airbnb.back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Review extends BaseTimeEntity {
    private long review_id;
    private int score;
    private String content;
    private Status status;
    private Reservation reservation;
    private List<ReviewImage> reviewImages = new ArrayList<>();
    //post할 경우에만 request 필요해서 toEntity 필요


    @Builder
    public Review(int score, String content, Status status, Reservation reservation) {
        this.score = score;
        this.content = content;
        this.status = status;
        this.reservation = reservation;
    }
}
