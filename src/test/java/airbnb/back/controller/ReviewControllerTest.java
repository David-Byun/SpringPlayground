package airbnb.back.controller;

import airbnb.back.dto.review.ReviewListResponseDto;
import airbnb.back.service.ReviewService;
import airbnb.back.util.jwt.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReviewService service;

    @MockBean
    JwtProvider jwtProvider;

    @WithMockUser(roles = {"USER", "ADMIN"})
    @DisplayName("개별 리뷰 조회 테스트")
    @Test
    void getOneReview() throws Exception {
        //given
        ReviewListResponseDto instance1 = new ReviewListResponseDto();
        instance1.setReviewId(1);
        instance1.setReservationId(1001);
        instance1.setScore(4);
        instance1.setContent("Great experience!");
        instance1.setUpdatedAt("2023-06-01");
        instance1.setReviewImageUrls(Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg"));

        //when
        when(service.findById(1)).thenReturn(instance1);

        //then
        mockMvc.perform(get("/api/v1/reviews/1")
                        .with(csrf())
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.reviewId").value(1)).andDo(print());
    }

    @WithMockUser(roles = {"USER", "ADMIN"})
    @DisplayName("전체 리뷰 조회 테스트")
    @Test
    void getAllReviews() throws Exception {
        //given
        ReviewListResponseDto instance1 = new ReviewListResponseDto();
        instance1.setReviewId(1);
        instance1.setReservationId(1001);
        instance1.setScore(4);
        instance1.setContent("Great experience!");
        instance1.setUpdatedAt("2023-06-01");
        instance1.setReviewImageUrls(Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg"));

        //when
        ReviewListResponseDto instance2 = new ReviewListResponseDto();
        instance2.setReviewId(2);
        instance2.setReservationId(1002);
        instance2.setScore(3);
        instance2.setContent("Average experience.");
        instance2.setUpdatedAt("2023-05-30");
        instance2.setReviewImageUrls(Arrays.asList("https://example.com/image3.jpg", "https://example.com/image4.jpg"));

        List<ReviewListResponseDto> reviewList = new ArrayList<>();
        reviewList.add(instance1);
        reviewList.add(instance2);


        //then
    }
}




















