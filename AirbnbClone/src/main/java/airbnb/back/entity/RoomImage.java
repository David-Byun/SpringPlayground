package airbnb.back.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomImage {
    private long id;
    private String roomIamgeUrl;
    private Room room;
    //Room - RoomImage 관계에서 room에서는 RoomImage List가 없는데 존재하는 경우는 어떠한 경우인가?
}
