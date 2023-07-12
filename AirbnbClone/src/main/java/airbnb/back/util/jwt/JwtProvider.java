package airbnb.back.util.jwt;

import airbnb.back.util.Token;
import airbnb.back.util.jwt.PrincipalDetails;
import airbnb.back.util.exception.JwtInvalidException;
import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Random;

import static airbnb.back.util.BaseResponseStatus.*;

@Component
@Getter
@RequiredArgsConstructor
@Slf4j
public class JwtProvider {

    private final PrincipalDetailsService principalDetailsService;

    @Value("${jwt.access-token.expire-length}")
    private long accessTokenExpiredIn;

    @Value("${jwt.refresh-token.expire-length}")
    private long refreshTokenExpiredIn;

    @Value("${jwt.token.secret-key}")
    private String secretKey;

    public Token createToken(String payload) {
        return Token.builder()
                .accessToken(createAccessToken(payload))
                .refreshToken(createRefreshToken())
                .refreshTokenExpiredIn(getRefreshTokenExpiredIn())
                .build();
    }

    public String createAccessToken(String payload) {
        // claims : JWT payload 에 저장되는 정보단위
        Claims claims = Jwts.claims().setSubject(payload);
        Date now = new Date(); // 현재 시간을 가져옵니다.
        Date validity = new Date(now.getTime() + accessTokenExpiredIn);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey) // 서명 알고리즘과 비밀 키를 사용하여 토큰을 서명합니다.
                .compact(); // 토큰을 문자열 형태로 변환하여 반환합니다.
    }

    public String createRefreshToken() {
        byte[] array = new byte[7]; // 길이가 7인 바이트 배열 생성
        new Random().nextBytes(array); // 배열에 랜덤한 값을 채움

        String randomString = new String(array, StandardCharsets.UTF_8); //바이트 배열을 문자열로 변환

        Claims claims = Jwts.claims().setSubject(randomString);
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenExpiredIn);
        return Jwts.builder()
                .setClaims(claims) //JWT 정보조각들(claims)을 담을게요
                .setIssuedAt(now)
                .setExpiration(validity) //만료시간은 application.yml에 등록했어요
                .signWith(SignatureAlgorithm.HS256, secretKey) //다음의 정보로 sign 할게요
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            /*
                1. JwtParserBuilder 객체를 생성하기 위해 Jwts.parseBuilder() 메서드를 사용
                2. JWS 서명을 증명하기 위해 사용하고 싶은 SecretKey 혹은 비대칭 Publickey 명세
                3. parseClaimsJws(String) : 원본 JWS를 만드는 jws String와 함께 호출
                유효성 또는 파싱 실패시 try/catch 진행

             */
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            log.info("expiredDate={}", claimsJws.getBody().getExpiration());
            log.info("expired?={}", claimsJws.getBody().getExpiration().before(new Date()));
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            throw new JwtInvalidException(EXPIRED_ACCESS_TOKEN);
        } catch (MalformedJwtException e) {
            throw new JwtInvalidException(MALFORMED_TOKEN_TYPE);
        } catch (SignatureException e) {
            throw new JwtInvalidException(INVALID_SIGNATURE_JWT);
        } catch (IllegalArgumentException e) {
            throw new JwtInvalidException(INVALID_TOKEN_TYPE);
        } catch (UnsupportedJwtException e) {
            throw new JwtInvalidException(UNSUPPORTED_TOKEN_TYPE);
        }
    }

    public Authentication getAuthentication(String token) {
        PrincipalDetails principalDetails = principalDetailsService.loadUserByUsername(getPayload(token));
        log.info("getAuthentication, email={}", principalDetails.getUsername());
        return new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
    }

    public String getPayload(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody().getSubject();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }
}
