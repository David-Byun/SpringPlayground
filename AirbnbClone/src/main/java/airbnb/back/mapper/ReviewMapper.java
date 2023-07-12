package airbnb.back.mapper;

import airbnb.back.dto.review.ReviewListResponseDto;
import airbnb.back.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReviewMapper {
    List<ReviewListResponseDto> findAllDesc(int roomId, int limit, int offset);

    Optional<ReviewListResponseDto> findById(long reviewId);

    //review 객체 안에서 reservation를 꺼내오기 때문에 @Param으로 특정지었다고 추정
    void save(@Param("review") Review review);

    Optional<Review> findByIdAndUserId(Long reviewId, Long userId);
}
