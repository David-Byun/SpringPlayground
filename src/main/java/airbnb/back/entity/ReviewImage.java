package airbnb.back.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewImage {
    private long id;
    @NonNull
    private String reviewImageUrl;
    @NonNull
    private Review review;
}
