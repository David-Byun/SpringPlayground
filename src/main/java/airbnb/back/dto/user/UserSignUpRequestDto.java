package airbnb.back.dto.user;

import airbnb.back.entity.Status;
import airbnb.back.entity.user.User;
import airbnb.back.entity.user.oauth.OauthProvider;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/*
    Dto이기 때문에 @Data 사용해도 됨
    UserSignUpRequestDto 에는 toEntity() 함수 있지만, UserSignInRequestDto 에는 없음
 */
@Data
public class UserSignUpRequestDto {

    // NotBlank : null 과 "", " " 모두 허용하지 않는다.
    @Schema(description = "회원 이름", required = true, example = "철수")
    @NotBlank(message = "username은 필수 입력사항 입니다.")
    private String username;

    @Schema(description = "회원 닉네임", required = true, example = "Good Man")
    @NotBlank(message = "nickname은 필수 입력사항 입니다.")
    private String nickname;

    @Schema(description = "생년월일", required = false, example = "940203")
    private String birth;

    @Schema(description = "회원 아이디(이메일)", required = true, example = "david@email.com")
    @NotBlank(message = "이메일은 필수 입력사항 입니다.")
    @Pattern(regexp = "\\w+@\\w+\\.\\w+(\\.\\w+)?", message = "이메일 형식이 올바르지 않습니다.")
    // @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @Schema(description = "회원 비밀번호", required = true, example = "password123!")
    @NotBlank(message = "비밀번호는 필수 입력사항 입니다.")
    @Pattern(regexp = "^(?=.*[~!@#$%^&*()_+])[A-Za-z0-9~!@#$%^&*()_+]{8,}",
            message = "특수문자 포함 8자 이상 입력해야 합니다.")
    private String password;

    private boolean marketingAgreement;

    /*
        User라는 타입의 Entity를 반환해오는 형식
        Dto를 Entity로 변환하고 mapper를 통해서 DB까지 저장시키는 과정
     */
    public User toEntity() {
        return User.builder()
                .username(username)
                .nickname(nickname)
                .birth(birth)
                .email(email)
                .password(password)
                .status(Status.ACTIVE)
                .marketingAgreement(marketingAgreement)
                .hostPermission(false)
                .oauthProvider(OauthProvider.NONE)
                .build();
    }


}
