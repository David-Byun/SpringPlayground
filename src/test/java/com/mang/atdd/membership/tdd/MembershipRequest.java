package com.mang.atdd.membership.tdd;

import com.mang.atdd.membership.MembershipType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class MembershipRequest {

    @NotNull
    @Min(0)
    private int point;

    @NotNull
    private MembershipType membershipType;
}
