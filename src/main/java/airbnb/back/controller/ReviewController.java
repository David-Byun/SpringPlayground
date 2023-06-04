package airbnb.back.controller;

import airbnb.back.dto.review.ReviewListResponseDto;
import airbnb.back.dto.review.ReviewSaveRequestDto;
import airbnb.back.dto.review.ReviewSaveResponseDto;
import airbnb.back.service.ReviewService;
import airbnb.back.util.BaseResponse;
import airbnb.back.util.exception.ReviewException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static airbnb.back.util.BaseResponseStatus.INVALID_REQUEST;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/app")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public BaseResponse<List<ReviewListResponseDto>> getList(@RequestParam int roomId,
                                                             @RequestParam(defaultValue = "4") int limit,
                                                             @RequestParam(defaultValue = "0") int page) {
        int offset = limit * page;
        List<ReviewListResponseDto> reviewList = reviewService.findAllDesc(roomId, limit, page);
        if (reviewList.size() == 0) throw new ReviewException(INVALID_REQUEST);

        return new BaseResponse<>(reviewList);
    }

    //특정 리뷰 Get
    @GetMapping("/reviews/{reviewId}")
    public BaseResponse<ReviewListResponseDto> get(@PathVariable Long reviewId) {
        ReviewListResponseDto review = reviewService.findById(reviewId);
        return new BaseResponse<>(review);
    }

    //리뷰, 리뷰이미지와 함께 POST, 사용자는 1번 user로 하드코딩, JWT TASK 후 수정 예정
    @PostMapping("/reviews")
    public BaseResponse<ReviewSaveResponseDto> createReview(
            @RequestParam("reservationId") Long reservationId,
            @RequestParam(value = "reviewImages", required = false) List<MultipartFile> reviewImages,
            @RequestParam(value = "score", required = false, defaultValue = "5") int score,
            @RequestParam(value = "content", required = false) String content
    )
    {
        //받는 변수들로 ReviewSaveRequestDto를 생성
        ReviewSaveRequestDto requestDto = ReviewSaveRequestDto.of(reviewImages, score, content);
        ReviewSaveResponseDto save = reviewService.save(Long.valueOf(1), reservationId, requestDto);
        return new BaseResponse<>(save);

    }
}
