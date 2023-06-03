package airbnb.back.dto.room;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
    Request가 아닌 Response는 값을 받아오기만 하기 때문에 필요없음!
 */
@NoArgsConstructor
@Data
public class RoomListResponseDto {
    private long roomId;
    private String city;
    private String district;
    private int price;
    private String roomMainImage;
    private double reviewAverageScore;
    private int reviewCount;
}
