package airbnb.back.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Getter
@AllArgsConstructor
//@RequiredArgsConstructor : final 이나 @NonNull인 필드 값만 파라미터로 받는 생성자 만듦
//NoArgsConstructor : 이 경우 초기값 세팅이 필요한 final 변수가 있을 경우 컴파일 에러가 발생함으로 주의한다.
public class Category {
    //Entity는 id로 명시하고 테이블에서는 실제 해당 Entity의 id로 명시함
    private long id;
    @NonNull
    private String categoryName;

    @NonNull
    private String categoryImage;
    private List<RoomCategory> roomCategory;
}
