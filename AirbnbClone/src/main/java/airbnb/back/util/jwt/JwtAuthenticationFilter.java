package airbnb.back.util.jwt;

import airbnb.back.util.exception.JwtInvalidException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static airbnb.back.util.BaseResponseStatus.EXPIRED_ACCESS_TOKEN;

/*
    Filter는 Request에 포함된 쿠키에서 토큰을 추출하고, 토큰의 payload에 존재하는 email을 바탕으로 인증에 필요한 토큰(UsernamePasswordToken)을 생성
    위 토큰은 JWT 토큰과 관련이 없고, 시큐리티 인증 로직에 필요한 하나의 오브젝트로 볼 수 있음
 */
@Slf4j
@Component
@RequiredArgsConstructor
/*
    OncePerRequestFilter는 그 이름에서도 알 수 있듯이 모든 서블릿에 일관된 요청을 처리하기 위해 만들어진 필터이다
    이 추상 클래스를 구현한 필터는 사용자의 한번에 요청 당 딱 한번만 실행되는 필터를 만들 수 있다.

    JwtAuthenticationFilter : JWT 토큰으로 인증하고 SecurityContextHolder에 추가하는 필터 설정 클래스

 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String PREFIX_TOKEN = "Bearer ";

    /*
                    principal(주체)의 identity(신원)을 증명하는 과정
                    주체는 보통 유저(사용자)를 가르키며 주체는 자신을 인증해달라고 신원증명정보 즉 Credential을 제시한다. 대개 패스워드.
                 */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);
        try {
            if (token != null) {
                if (!jwtProvider.isValidToken(token)) {
                    throw new JwtInvalidException(EXPIRED_ACCESS_TOKEN);
                }
                Authentication authentication = jwtProvider.getAuthentication(token);
                /*
                    Principal : 보호된 리소스에 접근하는 사용자 식별자(아이디)
                    SecurityContext : Authentication 객체를 포함하고 있으며 SecurityContextHolder를 통해 접근할 수 있다.
                    Credentials : 인증을 위해 필요한 정보(비밀번호)
                    GrantedAuthority : 인증된 사용자의 인증정보 표현
                 */
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("authentication={}", authentication.getPrincipal());
            }
        } catch (JwtInvalidException e) {
            log.warn("e={}", e.getStatus().getResponseMessage());
            request.setAttribute("JwtException", e.getStatus());
            throw new JwtInvalidException(e.getStatus());
        }
        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HEADER_AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(PREFIX_TOKEN)) {
            return bearerToken.substring(PREFIX_TOKEN.length());
        }
        return null; // Not Login => null
    }

}
