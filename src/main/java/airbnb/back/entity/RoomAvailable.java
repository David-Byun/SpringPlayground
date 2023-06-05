package airbnb.back.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RoomAvailable {
    private long id;
    private LocalDate availableDay;
    private Room room;
    private boolean isAvailable;
}