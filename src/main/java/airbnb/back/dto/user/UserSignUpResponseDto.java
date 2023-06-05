package airbnb.back.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSignUpResponseDto {
    @ApiModelProperty(notes = "회원 고유 번호", example = "1")
    private long id;
}
