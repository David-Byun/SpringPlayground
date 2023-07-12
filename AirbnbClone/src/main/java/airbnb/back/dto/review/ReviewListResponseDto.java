package airbnb.back.dto.review;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ReviewListResponseDto {
    private long reviewId; //review
    private long reservationId; //review
    private int score; //review
    private String content; //review
    private String updatedAt;
    private List<String> reviewImageUrls; //reviewImage
}
