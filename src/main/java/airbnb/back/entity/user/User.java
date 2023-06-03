package airbnb.back.entity.user;

import airbnb.back.entity.BaseTimeEntity;
import airbnb.back.entity.Status;
import airbnb.back.entity.user.oauth.OauthProvider;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity {
    private long user_id;
    @NonNull
    private String username;
    @NonNull
    private String nickname;
    private String birth;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private Status status;
    @NonNull
    private boolean privacyAgreement;
    @NonNull
    private boolean marketingAgreement;
    @NonNull
    private boolean hostPermission;
    private OauthProvider oauthProvider;

    /*
        개발 진행시 필요하다면 constructor 추가로 만들어서 Builder 패턴 생성
     */
    @Builder
    public User(@NonNull String username, @NonNull String nickname, String birth, @NonNull String email, @NonNull String password, @NonNull Status status, @NonNull boolean privacyAgreement, @NonNull boolean marketingAgreement, @NonNull boolean hostPermission, OauthProvider oauthProvider) {
        this.username = username;
        this.nickname = nickname;
        this.birth = birth;
        this.email = email;
        this.password = password;
        this.status = status;
        this.privacyAgreement = privacyAgreement;
        this.marketingAgreement = marketingAgreement;
        this.hostPermission = hostPermission;
        this.oauthProvider = oauthProvider;
    }

    //Password 암호화를 위해 Set 함수 만듦 -> Setter 를 열어두면 값이 쉽게 변질되므로 Set 메서드 사용
    public void setPassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}