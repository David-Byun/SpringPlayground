package airbnb.back.entity;

import java.util.ArrayList;
import java.util.List;

public class Review extends BaseTimeEntity {
    private long review_id;
    private int score;
    private String content;
    private Status status;
    private Reservation reservation;
    private List<ReviewImage> reviewImages = new ArrayList<>();
}
