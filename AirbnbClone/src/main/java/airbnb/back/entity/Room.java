package airbnb.back.entity;

import airbnb.back.entity.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Room extends BaseTimeEntity {
    private long id;

    @NonNull
    private String roomName;
    @NonNull
    private String region;   // 서울특별시 인천광역시 부산광역시.. 경상도 경기도 전라도 제주도
    @NonNull
    private String city;     // 서울시 인천시 부산시 안산시 제주시 서귀포시 ~군. 저장시에는 행정구분단위 제외하고 이름만
    @NonNull
    private String district; // 구/읍/면 (리는 제외). 저장시에는 행정구분 포함
    @NonNull
    private Float latitude;
    @NonNull
    private Float longitude;
    @NonNull
    private int price;
    private String main_image;
    @NonNull
    private int max_guest;
    private String description;
    @NonNull
    private LocalDateTime checkInTime;
    @NonNull
    private LocalDateTime checkOutTime;
    @NonNull
    private Status status;
    @NonNull
    private User user;

    private List<Review> reviews = new ArrayList<>();
    private List<Reservation> reservation;
    private List<RoomAvailable> roomAvailables = new ArrayList<>();
    private List<RoomImage> roomImages = new ArrayList<>();

    public void addReview(Review review) {
        this.reviews.add(review);
    }

}
