package com.mang.atdd.membership.mypoint;

import com.mang.atdd.membership.MembershipType;
import lombok.*;

@Data
@RequiredArgsConstructor
@Builder
public class MembershipDetailResponse {
    private final Long id;
    private final Integer point;
    private final MembershipType membershipType;
}
