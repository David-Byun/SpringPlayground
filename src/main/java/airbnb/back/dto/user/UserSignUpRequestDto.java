package airbnb.back.dto.user;

import airbnb.back.entity.Status;
import airbnb.back.entity.user.User;
import airbnb.back.entity.user.oauth.OauthProvider;
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
    @NotBlank(message = "username은 필수 입력사항 입니다.")
    private String username;
    @NotBlank(message = "nickname은 필수 입력사항 입니다.")
    private String nickname;
    private String birth;
    @NotBlank(message = "이메일은 필수 입력사항 입니다.")
    @Pattern(regexp = "\\w+@\\w+\\.\\w+(\\.\\w+)?", message = "이메일 형식이 올바르지 않습니다.")
    // @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
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
