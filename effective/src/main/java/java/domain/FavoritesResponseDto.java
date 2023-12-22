package java.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Builder
@Slf4j
public class FavoritesResponseDto {

    private Long id;
    private List<String> roomImages;
    private String roomAddress;
    private double averageReviewScore;

    public static FavoritesResponseDto of(Favorite favorite) {
        Room room = favorite.getRoom();
        

    }
}
