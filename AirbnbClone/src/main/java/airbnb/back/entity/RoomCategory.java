package airbnb.back.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class RoomCategory {
    private long id;
    @NonNull //null을 허용하지 않을 경우
    private Room room;
    @NonNull
    private Category category;
}
