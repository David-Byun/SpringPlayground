package airbnb.back.util.jwt;

import airbnb.back.util.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static airbnb.back.util.BaseResponseStatus.ACCESS_DENIED;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException, IOException {

        //객체를 JSON으로 직렬화 하기 위한 ObjectMapper 객체 생성
        ObjectMapper objectMapper = new ObjectMapper();

        //response 값 설정
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        BaseResponse<Object> baseResponse = new BaseResponse<>(ACCESS_DENIED);

        //objectMapper 를 사용하여 baseResponse 객체를 JSON 형식으로 직렬화한 후, response의 'Writer'를 통해 응답으로 출력한다.
        objectMapper.writeValue(response.getWriter(), baseResponse);
    }

}
