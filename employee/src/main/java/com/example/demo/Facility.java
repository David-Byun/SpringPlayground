package com.example.demo;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_id")
    private Long id;

    @Column(length = 45, nullable = false)
    private String facilityName;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String facilityImageUrl;

    @Column(nullable = false)
    private String category;

    @OneToMany(mappedBy = "facility")
    private List<RoomFacility> roomFacilities;

    @Builder
    public Facility(String facilityName, String facilityImageUrl, String category) {
        this.facilityName = facilityName;
        this.facilityImageUrl = facilityImageUrl;
        this.category = category;
    }

}
