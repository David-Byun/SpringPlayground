package com.mang.atdd.membership.tdd;

import com.mang.atdd.membership.Membership;
import com.mang.atdd.membership.MembershipException;
import com.mang.atdd.membership.MembershipRepository;
import com.mang.atdd.membership.MembershipType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MembershipRepositoryTest {

    @Autowired
    private MembershipRepository membershipRepository;

    @Test
    void MembershipRepository가Null이아님() {
        assertThat(membershipRepository).isNotNull();
    }

    @Test
    void 멤버십등록() {

        //given
        final Membership member = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.NAVER)
                .point(1000)
                .build();

        //when
        membershipRepository.save(member);
        Membership save = membershipRepository.findByUserIdAndMembershipType("userId", MembershipType.NAVER);

        //then
        assertThat(save.getUserId()).isEqualTo("userId");
        assertThat(save.getId()).isNotNull();
        assertThat(save.getMembershipType()).isEqualTo(MembershipType.NAVER);
        assertThat(save.getPoint()).isEqualTo(1000);
    }

    @Test
    void 멤버십조회_사이즈가0() {
        // given
        // when
        List<Membership> result =  membershipRepository.findAllByUserId("userId");

        // then
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    void 멤버십조회_사이즈가2() {

        // given
        Membership naverMembership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.NAVER)
                .point(10000)
                .build();

        Membership kakaoMembership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.KAKAO)
                .point(10000)
                .build();

        membershipRepository.save(naverMembership);
        membershipRepository.save(kakaoMembership);

        // when
        List<Membership> result = membershipRepository.findAllByUserId("userId");

        // then
        assertThat(result.size()).isEqualTo(2);

    }

    @Test
    public void 멤버십추가후삭제() {
        // given
        final Membership naverMembership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.NAVER)
                .point(10000)
                .build();

        final Membership savedMembership = membershipRepository.save(naverMembership);

        //when
        membershipRepository.deleteById(savedMembership.getId());
    }


}



















