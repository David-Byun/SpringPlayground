package com.example.demo.Room;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select r, count(rv.id) from Room r " +
            "left outer join fetch Reservation rs on (r.id = rs.room.id)" +
            "left outer join fetch Review rv on (rs.id = rv.reservation.id) " +
            "where r.status = 'ACTIVE'" +
            "group by r.id")
    List<Room> findAllDesc(Pageable pageable);
}
