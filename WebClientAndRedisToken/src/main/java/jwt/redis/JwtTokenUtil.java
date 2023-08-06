package jwt.redis;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import static jwt.redis.JwtExpirationEnums.ACCESS_TOKEN_EXPIRATION_TIME;

@Slf4j
@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    /*
        extractAllClaims : 토큰을 파싱하고, 서명을 확인한 후에 토큰의 클레임을 추출하여 반환
        이를 통해 토큰에 포함된 사용자 정보나 기타 데이터를 확인할 수 있음
        클레임 jwt header/payload/signature 중 payload에 위치하여 토큰에 포함될 정보를 제공(발급자가 원하는 정보 사용자의 식별자, 권한, 만료시간 같은 데이터 포함하며 DB 조회하지 않고 토큰 신뢰)
     */
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningkey(SECRET_KEY))
                .build()
                // Jws객체를 반환하는데, 이 객체에서 getBody 메서드를 호출하여 클레임을 가져옴
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername(String token) {
        return extractAllClaims(token).get("username", String.class);
    }

    public Boolean isTokenExpired(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public String generateAccessToken(String username) {
        return doGenerateToken(username, ACCESS_TOKEN_EXPIRATION_TIME.getValue());
    }

    private String doGenerateToken(String username, long expireTime) {
        Claims claims = Jwts.claims();
        claims.put("username", username);

        /*
            compact() 메서드를 호출하여 최종적으로 토큰을 생성하고 문자열로 반환(토큰에 사용자 이름과 토큰의 유효기간 포함)
         */
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigningkey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsername(token);
        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    public long getRemainMilliSeconds(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        Date now = new Date();
        return expiration.getTime() - now.getTime();
    }













    /*
        secretKey를 UTF-8 문자열로 인코딩하여 'byte' 배열인 keyBytes에 저장
        Keys.hmacShaKeyFor() 메서드를 사용하여 HMAC-SHA 키를 생성.
        시크릿키 -> HMAC_SHA 키 생성 반환
     */
    private Key getSigningkey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }



}
