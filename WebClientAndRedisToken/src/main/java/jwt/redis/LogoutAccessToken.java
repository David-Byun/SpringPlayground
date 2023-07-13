package jwt.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

/*
    @RedisHash value 값(redis keyspace)에 특정한 값을 넣어줌으로써 추후 해당 데이터에 대한 key가 생성될때 prefix를 지정할 수 있음
 */
@Getter
@RedisHash("logoutAccessToken")
@AllArgsConstructor
@Builder
public class LogoutAccessToken {

    @Id
    private String id;
    private String username;

    private Long expiration;

    public static LogoutAccessToken of(String accessToken, String username, Long remainingMilliSeconds) {
        return LogoutAccessToken.builder()
                .id(accessToken)
                .username(username)
                .expiration(remainingMilliSeconds / 1000)
                .build();
    }

}
