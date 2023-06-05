package airbnb.back.util.jwt;

import airbnb.back.util.BaseResponse;
import airbnb.back.util.BaseResponseStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static airbnb.back.util.BaseResponseStatus.EMPTY_AUTHORIZATION_HEADER;

//인증 실패시에 호출
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    //commerce 메서드는 인증이 실패하였을 때 호출
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.info("in EntryPoint");
        //JwtException 이라는 저장된 속성값을 가져옴
        BaseResponseStatus jwtExceptionStatus = (BaseResponseStatus) request.getAttribute("JwtException");
        log.info("Exception={}", jwtExceptionStatus);
        if (jwtExceptionStatus == null) { // 헤더를 아예 추가하지 않을 시
            customizeError(response, EMPTY_AUTHORIZATION_HEADER);
        } else{ // 토큰 값이 잘못된 경우
            customizeError(response, jwtExceptionStatus);
        }
    }

    public void customizeError(HttpServletResponse response, BaseResponseStatus status) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        BaseResponse<Object> baseResponse = new BaseResponse<>(status);
        objectMapper.writeValue(response.getWriter(), baseResponse);
    }
}