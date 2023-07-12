package airbnb.back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RoomAvailable {
    private long id;
    private LocalDate availableDay;
    private Room room;
    private Status status;

    public void changeStatus(Status status) {
        this.status = status;
    }

    @Builder
    public RoomAvailable(LocalDate availableDay, Room room, Status status) {
        this.availableDay = availableDay;
        this.room = room;
        this.status = status;
    }
}