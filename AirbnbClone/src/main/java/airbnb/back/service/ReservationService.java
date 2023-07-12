package airbnb.back.service;

import airbnb.back.entity.Reservation;
import airbnb.back.entity.Room;
import airbnb.back.entity.Status;
import airbnb.back.entity.user.User;
import airbnb.back.mapper.ReservationMapper;
import airbnb.back.mapper.RoomMapper;
import airbnb.back.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {
    private final UserMapper userMapper;
    private final RoomMapper roomMapper;
    private final ReservationMapper reservationMapper;

    private int totalGeust;
    private int totalPet;
    private String startDate;
    private String endDate;

    //dto > entity
    public Reservation toEntity(User user, Room room) {
        LocalDate startDate = LocalDate.parse(this.startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDate = LocalDate.parse(this.endDate, DateTimeFormatter.ISO_LOCAL_DATE);

        return Reservation.builder()
                .user(user)
                .room(room)
                .reservationCode(UUID.randomUUID().toString())
                .totalGuest(totalGeust)
                .totalPrice(Period.between(startDate, endDate).getDays() * room.getPrice())
                .isReviewCreated(false)
                .status(Status.ACTIVE)
                .build();
    }
}
