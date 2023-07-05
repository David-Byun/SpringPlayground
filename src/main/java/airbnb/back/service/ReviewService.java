package airbnb.back.service;

import airbnb.back.dto.ReservationReadResponseDto;
import airbnb.back.dto.review.*;
import airbnb.back.entity.Reservation;
import airbnb.back.entity.Review;
import airbnb.back.entity.ReviewImage;
import airbnb.back.entity.Status;
import airbnb.back.entity.user.User;
import airbnb.back.mapper.ReservationMapper;
import airbnb.back.mapper.ReviewImageMapper;
import airbnb.back.mapper.ReviewMapper;
import airbnb.back.mapper.UserMapper;
import airbnb.back.util.BaseResponseStatus;
import airbnb.back.util.aws.AwsS3Uploader;
import airbnb.back.util.exception.ReservationException;
import airbnb.back.util.exception.ReviewException;
import airbnb.back.util.exception.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

import static airbnb.back.util.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReviewService {

    private final ReviewMapper reviewMapper;
    private final ReservationMapper reservationMapper;
    private final AwsS3Uploader awsS3Uploader;
    private final ReviewImageMapper reviewImageMapper;
    private final UserMapper userMapper;

    public List<ReviewListResponseDto> findAllDesc(int roomId, int limit, int offset) {
        List<ReviewListResponseDto> allDesc = reviewMapper
                .findAllDesc(roomId, limit, offset);
        return allDesc;
    }

    //해당 리뷰(id:1, id:2 ... ) 찾는 메서드
    public ReviewListResponseDto findById(long reviewId) {
        ReviewListResponseDto reviewResponse = reviewMapper.findById(reviewId).orElseThrow(() -> new ReservationException(NONE_REVIEW));
        return reviewResponse;
    }

    public ReviewSaveResponseDto save(long userNumber, long reservationId, ReviewSaveRequestDto requestDto) {
        long userId = userNumber;

        // Reservation 객체 꺼내오기, 예외 - 리뷰 작성시 예약이 없는 경우
        Reservation reservation = reservationMapper.findByIdAndUserId(reservationId, userId).orElseThrow(() -> new ReservationException(POST_REVIEW_NONE_RESERVATION));

        // 예외 - 리뷰가 이미 있는 경우
        if (reservation.isReviewCreated()) {
            throw new ReviewException(POST_REVIEW_ALREADY_CREATED);
        }

        // Dto -> Entity
        Review review = requestDto.toEntity(reservation);
        log.info("==========review============ : {}", review.toString());

        // review db 저장
        reviewMapper.save(review);

        //리뷰 이미지 저장
        uploadReviewImages(requestDto, review);

        //리뷰 작성시 예약테이블 isReviewCreated 컬럼 변환
        reservationMapper.updateReviewStatus(reservationId);

        //응답 response build
        return ReviewSaveResponseDto.builder()
                .id(review.getReview_id())
                .build();

    }

    private void uploadReviewImages(ReviewSaveRequestDto requestDto, Review review) {
        List<MultipartFile> reviewImages = requestDto.getReviewImages();
        for (MultipartFile multipartFile : reviewImages) {
            String reviewImageUrl = awsS3Uploader.upload(multipartFile, "review");
            reviewImageMapper.save(ReviewImage.builder()
                    .review(review)
                    .reviewImageUrl(reviewImageUrl)
                    .build());
        }
    }

    public ReviewResponseDto updateReview(Long userId, Long reviewId, ReviewRequestDto reviewRequestDto) {
        Review review = reviewMapper.findByIdAndUserId(reviewId, userId).orElseThrow(() -> new ReviewException(INVALID_REQUEST));

        if (reviewRequestDto.getScore() != null) {
            review.updateScore(reviewRequestDto.getScore());
        }

        if (reviewRequestDto.getContent() != null) {
            review.updateContent(reviewRequestDto.getContent());
        }

        if (reviewRequestDto.getReviewImages().isEmpty()) {

        }
        return null;
    }
}


















