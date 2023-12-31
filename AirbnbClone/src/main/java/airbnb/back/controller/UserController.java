package airbnb.back.controller;

import airbnb.back.dto.user.UserSignInRequestDto;
import airbnb.back.dto.user.UserSignInResponseDto;
import airbnb.back.dto.user.UserSignUpRequestDto;
import airbnb.back.dto.user.UserSignUpResponseDto;
import airbnb.back.service.UserService;
import airbnb.back.util.BaseResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
})
@RestController
@RequiredArgsConstructor
/*
    스프링 4.2 부터 지원되는 @CrossOrigin 어노테이션은 CORS를 스프링을 통해 설정할 수 있는 기능이다.
    @CrossOrigin 어노테이션을 붙여주면 기본적으로 '모든 도메인, 모든 요청방식' 에 대해 허용 한다는 뜻이다.
 */
@Tag(name = "user", description = "회원 API")
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    /*
        @RequestBody : JSON 또는 XML => JAVA 객체로
     */
    @PostMapping("/signup")
    @ApiOperation(value = "회원 등록", notes = "신규 회원 등록", response = UserSignUpResponseDto.class)
    public BaseResponse<UserSignUpResponseDto> signUp(@Valid @RequestBody UserSignUpRequestDto dto) {
        return new BaseResponse<>(userService.joinUser(dto));
    }

    @PostMapping("/signin")
    @ApiOperation(value = "회원 로그인", notes = "회원 로그인", response =UserSignInResponseDto.class)
    public BaseResponse<UserSignInResponseDto> signIn(@Valid @RequestBody UserSignInRequestDto dto) {
        UserSignInResponseDto signin = userService.signin(dto); // result가 true, false =>
        return new BaseResponse<>(signin);
    }
}
