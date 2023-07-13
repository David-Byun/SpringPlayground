package jwt.redis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
    CustomUserDetails 클래스를 따로 만든 이유는 Redis에 캐싱할 때,
    UserDetails로 저장할 경우 역직렬화가 되지 않는 이슈가 있음
    저장할 때 관련이 없는 메서드들은 @JsonIgnore로 처리
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;

    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public static UserDetails of(Member member) {
        return CustomUserDetails.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .roles(member.getRoles())
                .build();
    }

    /*
        UserDetails 인터페이스는 사용자의 인증 및 권한 정보를 제공하기 위해 사용
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return false;
    }


    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }


    @Override
    public boolean isEnabled() {
        return false;
    }
}
