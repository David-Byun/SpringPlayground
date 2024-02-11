package com.example.demo.Common;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @Column(length = 10, nullable = false)
    private String region;

    @Column(length = 10, nullable = false)
    private String city;

    @Column(length = 10, nullable = false)
    private String district;

    @Column(length = 45, nullable = false)
    private String addressDetail;

    private String latitude;
    private String longitude;
}
