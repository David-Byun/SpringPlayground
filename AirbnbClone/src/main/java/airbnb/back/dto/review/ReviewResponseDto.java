package airbnb.back.dto.review;

import airbnb.back.entity.Review;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewResponseDto {

    private Long id;
    private Integer score;
    private String content;

    public static ReviewRequestDto of(Review review) {
        return ReviewRequestDto.builder()
                .score(review.getScore())
                .content(review.getContent())
                .build();
    }
}
