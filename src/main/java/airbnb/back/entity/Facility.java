package airbnb.back.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
@AllArgsConstructor
public class Facility {
    private long id;
    @NonNull
    private String facilityName;
    @NonNull
    private String facilityImageUrl;
    private List<RoomFacility> roomFacilities; //왜 RoomFacility를 리스트로 받고 있는지 궁금? -> N 대 N 관계이기 때문에 RoomFacility 가 중간다리 역할 1:N N: 1

}