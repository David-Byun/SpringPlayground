package airbnb.back.service;

import airbnb.back.dto.user.UserSignInRequestDto;
import airbnb.back.dto.user.UserSignInResponseDto;
import airbnb.back.dto.user.UserSignUpRequestDto;
import airbnb.back.dto.user.UserSignUpResponseDto;
import airbnb.back.entity.user.User;
import airbnb.back.mapper.UserMapper;
import airbnb.back.util.JwtProvider;
import airbnb.back.util.Token;
import airbnb.back.util.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static airbnb.back.util.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public UserSignUpResponseDto joinUser(UserSignUpRequestDto userRequest) {

        validateDuplicateEmail(userRequest.getEmail()); //중복확인, 캡슐화

        User user = userRequest.toEntity();
        String encodedPassword =  passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userMapper.saveUser(user);

        return UserSignUpResponseDto.builder()
                .id(user.getUser_id())
                .build();
    }

    private boolean validateDuplicateEmail(String email) {
        if (userMapper.checkEmail(email) > 0) {
            throw new UserException(DUPLICATED_EMAIL);
        }
        return true;
    }

    //로그인 -> UserSignInResponseDto : email, accessToken, refreshToken
    public UserSignInResponseDto signin(UserSignInRequestDto userRequest) {
        User user = userMapper.findUserByEmail(userRequest.getEmail()).orElseThrow(() -> new UserException(NONE_EXIST_USER)).toEntity();

        if (!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw new UserException(INVALID_EMAIL_OR_PASSWORD);
        }

        String email = user.getEmail();
        Token token = jwtProvider.createToken(email);

        return UserSignInResponseDto.builder()
                .email(email)
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }
}
