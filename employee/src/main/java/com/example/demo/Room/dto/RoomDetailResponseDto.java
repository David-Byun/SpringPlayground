package com.example.demo.Room.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class RoomDetailResponseDto {

    private Long roomId;
    private boolean isFavorite;
    private Long hostId;
    private String hostName;
    private String address;
    private String latitude; // 위도
    private String longitude; // 경도
    private String roomName;
    private int price;
    private int maxGuest;
    private int maxPet;
    private String roomDecription;
    private String checkinTime;
    private String checkoutTime;
    private double roomAverageScore; // Review AVG
    private long reviewCount; // Review COUNT
    private List<String> roomImageUrls;
}
