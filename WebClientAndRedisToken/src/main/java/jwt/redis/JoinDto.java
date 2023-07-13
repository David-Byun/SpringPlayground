package jwt.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JoinDto {

    private String email;
    private String password;
    private String nickname;
}
