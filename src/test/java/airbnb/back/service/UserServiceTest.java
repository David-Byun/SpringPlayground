package airbnb.back.service;

import airbnb.back.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {


    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserService service;

    @Spy
    private BCryptPasswordEncoder passwordEncoder;
    

    @DisplayName("회원 가입 테스트")
    @Test
    void testUniqueEmailWhenCreatingNewUser() {

//        //given
//        UserSignUpRequestDto signUpRequestDto = new UserSignUpRequestDto();
//        signUpRequestDto.setUsername("testUser");
//        signUpRequestDto.setNickname("Test User");
//        signUpRequestDto.setEmail("test@example.com");
//        signUpRequestDto.setPassword("password123!!");
//
//        User user = User.builder().username("testUser").nickname("Test User").password("password123!!").email("test@example.com").build();
//
//        //then
//        assertThat(service.joinUser(signUpRequestDto)).isEqualTo(UserSignUpResponseDto.builder().id(1L).build());
    }

}