package airbnb.back.util.jwt;

import airbnb.back.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/*
   SecurityContextHolder 클래스 > SecurityContextHolderStrategy 타입 객체 > SecurityContext
   strategy : SecurityContext를 HttpSession에 저장할 건지, 다른방식으로 저장할건지(저장방식)

  SecurityContext에 들어갈 수 있는 객체 타입은 정해짐 : Authentication 객체에는 User 정보를 저장.
  Authentication 객체에 저장할 수 있는 객체 타입 : UserDetails, OAuth2User 타입

  소셜로그인 구현시에는 OAuth2User 타입 객체에 저장
  회원가입-로그인 프로세스 직접 구현한 경우에는 UserDetails 타입 객체에 저장.

   Security Session => Authentication => UserDetails(PrincipalDetails)
 */
@RequiredArgsConstructor
public class PrincipalDetails implements UserDetails, OAuth2User {
    private final User user;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }


    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }


    /*
        User를 authentication 할때 사용할 username을 return
        이메일로 authentication 할 경우 username으로 email을 리턴
     */
    @Override
    public String getUsername() {
        return String.valueOf(user.getUsername());
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }
}
