package airbnb.back.mapper;

import airbnb.back.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface ReservationMapper {
    Optional<Reservation> findByIdAndUserId(long reservationId, long userId);

    void updateReviewStatus(long reservationId);
}
