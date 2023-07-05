package airbnb.back.entity;

import lombok.*;

@Getter
@AllArgsConstructor
public class ReviewImage {
    private long id;
    @NonNull
    private String reviewImageUrl;

    //리뷰 - 리뷰 이미지 1 : N 관계
    @NonNull
    private Review review;

    @Builder
    public ReviewImage(@NonNull String reviewImageUrl, @NonNull Review review) {
        this.reviewImageUrl = reviewImageUrl;
        this.review = review;
    }
}
