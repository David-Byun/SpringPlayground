package airbnb.back.controller;

import airbnb.back.dto.user.UserSignInRequestDto;
import airbnb.back.dto.user.UserSignUpRequestDto;
import airbnb.back.dto.user.UserSignUpResponseDto;
import airbnb.back.entity.Status;
import airbnb.back.entity.user.User;
import airbnb.back.mapper.UserMapper;
import airbnb.back.service.UserService;
import airbnb.back.util.exception.UserException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import static airbnb.back.util.BaseResponseStatus.INVALID_EMAIL_OR_PASSWORD;
import static airbnb.back.util.BaseResponseStatus.NONE_EXIST_USER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService service;

    @MockBean
    private UserMapper mapper;

    /**
     * 유저 가입 테스트
     *
     * @throws Exception
     */
    //security 권한부여 해야 테스트 가능
    @DisplayName("회원가입 Validation 테스트")
    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    void testAddUserHaveValidateError() throws Exception {
        User user = new User().builder().username("abc").nickname("").email("byun@naver").password("12312312sdf!!").status(Status.INACTIVE).privacyAgreement(true).marketingAgreement(true).hostPermission(false).build();

        String requestBody = objectMapper.writeValueAsString(user);

        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");
        bindingResult.rejectValue("email", "invalid.email", "이메일 형식이 올바르지 않습니다.");

        /*
            JSONPath : JSON 데이터에서 원하는 데이터를 선택하기 위한 쿼리 언어
            $는 JSON 데이터의 루트를 나타내는 기호로 JSON 경로의 시작을 나타냄
            $대신 @ 사용할 수 있음
            따라서, 아래 예시에서 .andExpect(jsonPath("$.responseMessage").value("잘못된 요청이 존재합니다."))는 JSON 데이터의 최상위(root)에서 responseMessage 필드를 선택하고, 그 값이 "잘못된 요청이 존재합니다."인지 검증하는 것을 의미합니다.
         */

        mockMvc.perform(post("/app/signup").with(csrf()).contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseMessage").value("잘못된 요청이 존재합니다."))
                .andExpect(jsonPath("$.result.nickname").value("nickname은 필수 입력사항 입니다."))
                .andExpect(jsonPath("$.result.email").value("이메일 형식이 올바르지 않습니다."));
    }

    @DisplayName("회원가입 정상 테스트")
    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    void testSignUpShouldReturn200OK() throws Exception {
        UserSignUpRequestDto signUpRequestDto = new UserSignUpRequestDto();
        signUpRequestDto.setUsername("testUser");
        signUpRequestDto.setNickname("Test User");
        signUpRequestDto.setEmail("test@example.com");
        signUpRequestDto.setPassword("password123!!");

        when(service.joinUser(any(UserSignUpRequestDto.class))).thenReturn(UserSignUpResponseDto.builder().id(11L).build());

        mockMvc.perform(post("/app/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpRequestDto))
                        //security 로 인해 csrf 권한 부여해야만 403에러 발생하지 않음
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value(11)).andDo(print());
    }

    /*
        회원은 존재하나 이메일 또는 패스워드 잘못될 경우 테스트
     */
    @DisplayName("로그인 패스워드/비밀번호 오류시 테스트")
    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    void testIdOrPasswordNotValid() throws Exception {

        UserSignInRequestDto userSignInRequestDto = new UserSignInRequestDto();
        userSignInRequestDto.setEmail("test1@example.com");
        userSignInRequestDto.setPassword("password123!!");

        when(service.signin(any(UserSignInRequestDto.class))).thenThrow(new UserException(INVALID_EMAIL_OR_PASSWORD));

        mockMvc.perform(post("/app/signin")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userSignInRequestDto))
                        //security 로 인해 csrf 권한 부여해야만 403에러 발생하지 않음
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(false)).andDo(print());

    }


    @DisplayName("회원이 존재하지 않는 경우 테스트")
    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    void testNotUserExist() throws Exception {


        UserSignInRequestDto userSignInRequestDto = new UserSignInRequestDto();
        userSignInRequestDto.setEmail("test1@example.com");
        userSignInRequestDto.setPassword("password123!!");

        when(service.signin(any(UserSignInRequestDto.class))).thenThrow(new UserException(NONE_EXIST_USER));

        mockMvc.perform(post("/app/signin")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userSignInRequestDto))
                        //security 로 인해 csrf 권한 부여해야만 403에러 발생하지 않음
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(false)).andDo(print());


    }
}