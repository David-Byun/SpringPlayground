package airbnb.back.mapper;

import airbnb.back.entity.ReviewImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReviewImageMapper {
    //리뷰 객체 안의 리뷰 아이디를 가져와서 Insert 해주기 때문에 @Param으로 전달
    void save(@Param("reviewImage") ReviewImage reviewImage);

}
