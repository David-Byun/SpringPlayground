package com.mang.atdd.membership.tdd;


import com.mang.atdd.membership.MemberShipService;
import com.mang.atdd.membership.MembershipAddResponse;
import com.mang.atdd.membership.mypoint.MembershipDetailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.mang.atdd.membership.MembershipConstants.USER_ID_HEADER;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MembershipController {

    private final MemberShipService membershipService;

    @PostMapping("/api/v1/memberships")
    public ResponseEntity<MembershipAddResponse> addMembership(
            @RequestHeader(USER_ID_HEADER) String userId,
            @RequestBody @Valid MembershipRequest membershipRequest
    ) {
        MembershipAddResponse membershipAddResponse = membershipService.addMembership(userId, membershipRequest.getMembershipType(), membershipRequest.getPoint());
        return ResponseEntity.status(HttpStatus.CREATED).body(membershipAddResponse);
    }

    @GetMapping("/api/v1/memberships")
    public ResponseEntity<List<MembershipDetailResponse>> getMembershipList(
            @RequestHeader(USER_ID_HEADER) final String userId
    ) {
        return ResponseEntity.ok(membershipService.getMembershipList(userId));
    }

    @GetMapping("/api/v1/memberships/{id}")
    public ResponseEntity<MembershipDetailResponse> getMembership(@RequestHeader(USER_ID_HEADER) String userId, @PathVariable Long id) {
        log.info("========== result : {} ==============", membershipService.getMembership(id, userId));

        return ResponseEntity.ok(membershipService.getMembership(id, userId));
    }

    @DeleteMapping("/api/v1/memberships/{id}")
    public ResponseEntity<Void> removeMembership(
            @RequestHeader(USER_ID_HEADER) final String userId,
            @PathVariable final Long id
    ) {
        membershipService.removeMembership(id, userId);
        return ResponseEntity.noContent().build();
    }

}
