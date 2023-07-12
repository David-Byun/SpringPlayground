package airbnb.back.dto.room;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class RoomDetailResponseDto {

    private long roomId;
    private String hostName;
    private String metropolitan; //room
    private String city; //room
    private String town; //room
    private Float latitude; //room
    private Float longitude; //room
    private String roomName; //room
    private int price; //room
    private int maxGuest; //room
    private String roomDescription; //room
    private String checkinTime; //room
    private String checkoutTime; //room
    private double roomAverageScore;  //reservation > review AVG
    private long reviewCount; //Review Count
    private List<String> roomImageUrls; //Review Image Entity

}
