package com.mang.atdd.membership.tdd;

import com.mang.atdd.membership.*;
import com.mang.atdd.membership.mypoint.Membership;
import com.mang.atdd.membership.mypoint.MembershipDetailResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {

    @Mock
    private MembershipRepository membershipRepository;

    @InjectMocks
    private MemberShipService memberShipService;

    private final String userId = "userId";
    private final MembershipType membershipType = MembershipType.NAVER;
    private final Integer point = 10000;
    private final Long membershipId = 100L;

    @Test
    void 멤버십등록실패_이미존재함() {

        //given
        doReturn(com.mang.atdd.membership.mypoint.Membership.builder().build()).when(membershipRepository).findByUserIdAndMembershipType(userId, membershipType.NAVER);


        //when
        MembershipException result = assertThrows(MembershipException.class, () -> memberShipService.addMembership(userId, membershipType, point));


        //then
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
    }

    @Test
    void 멤버십등록성공() {
        //given
        doReturn(null).when(membershipRepository).findByUserIdAndMembershipType(userId, membershipType);
//        doReturn(membership()).when(membershipRepository).save(any(com.mang.atdd.membership.mypoint.Membership.class
//        ));

        //when
        MembershipAddResponse result = memberShipService.addMembership(userId, membershipType, point);

        //then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getMembershipType()).isEqualTo(MembershipType.NAVER);

        //verify
        verify(membershipRepository, times(1)).findByUserIdAndMembershipType(userId, membershipType);
//        verify(membershipRepository, times(1)).save(any(com.mang.atdd.membership.mypoint.Membership.class));
    }

    @Test
    void 멤버십목록조회() {

        //given
        doReturn(Arrays.asList(
                com.mang.atdd.membership.mypoint.Membership.builder().build(),
                com.mang.atdd.membership.mypoint.Membership.builder().build(),
                com.mang.atdd.membership.mypoint.Membership.builder().build()
        )).when(membershipRepository).findAllByUserId(userId);

        //when
        List<MembershipDetailResponse> result =  memberShipService.getMembershipList(userId);

        //then
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    void 멤버십상세조회실패_존재하지않음() {
        // given
        doReturn(Optional.empty()).when(membershipRepository).findById(membershipId);

        // when
        MembershipException result = assertThrows(MembershipException.class, () ->
                memberShipService.getMembership(membershipId, userId));

        // then
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.MEMBERSHIP_NOT_FOUND);
    }

    @Test
    void 멤버십상세조회실패_본인이아님() {
        // given
        doReturn(Optional.empty()).when(membershipRepository).findById(membershipId);

        // when
        MembershipException result = assertThrows(MembershipException.class, () -> memberShipService.getMembership(membershipId, "notowner"));

        // then
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.MEMBERSHIP_NOT_FOUND);
    }

    @Test
    void 멤버십상세조회성공() {
        // given
        doReturn(Optional.of(membership())).when(membershipRepository).findById(membershipId);

        // when
        MembershipDetailResponse result = memberShipService.getMembership(membershipId, userId);


        // then
        assertThat(result.getMembershipType()).isEqualTo(MembershipType.NAVER);
        assertThat(result.getPoint()).isEqualTo(point);
    }

    private final Long membershipId2 = -1L;

    @Test
    public void 멤버십삭제실패_존재하지않음() {
        //given
        Mockito.doReturn(Optional.empty()).when(membershipRepository).findById(membershipId);

        //when
        final MembershipException result = Assertions.assertThrows(MembershipException.class, () -> memberShipService.removeMembership(membershipId, userId));

        //then
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.MEMBERSHIP_NOT_FOUND);
    }

    @Test
    public void 멤버십삭제실패_본인이아님() {
        //given
        final com.mang.atdd.membership.mypoint.Membership membership = membership();
        doReturn(Optional.of(membership)).when(membershipRepository).findById(membershipId);

        //when
        final MembershipException result = assertThrows(MembershipException.class, () -> {
            memberShipService.removeMembership(membershipId, "notowner");
        });

        //then
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.NOT_MEMBERSHIP_OWNER);
    }

    @Test
    public void 멤버십삭제성공() {

        //given
        final com.mang.atdd.membership.mypoint.Membership membership = membership();
        doReturn(Optional.of(membership)).when(membershipRepository).findById(membershipId);

        //when
        memberShipService.removeMembership(membershipId, userId);

    }

    @Test
    void 멤버십적립성공() {

        //given
        final com.mang.atdd.membership.mypoint.Membership membership = membership();
        doReturn(Optional.of(membership)).when(membershipRepository).findById(membershipId);

        //when
        memberShipService.accumulateMembershipPoint(membershipId, userId, 10000);

    }



    private com.mang.atdd.membership.mypoint.Membership membership() {
        return Membership.builder()
                .id(-1L)
                .point(point)
                .build();
    }
}


























