package airbnb.back.controller;

import airbnb.back.dto.review.ReviewListResponseDto;
import airbnb.back.service.ReviewService;
import airbnb.back.util.BaseResponse;
import airbnb.back.util.BaseResponseStatus;
import airbnb.back.util.exception.ReviewException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        if(reviewList.size() == 0) throw new ReviewException(INVALID_REQUEST);

        return new BaseResponse<>(reviewList);
    }
}
