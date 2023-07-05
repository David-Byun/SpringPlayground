package airbnb.back.mapper;

import airbnb.back.dto.user.UserSignUpRequestDto;
import airbnb.back.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void saveUser(User user); //Dto -> entity 변환 완료
    int checkEmail(String email);
    Optional<UserSignUpRequestDto> findUserByEmail(String email);

    Long findUserIdByEmail(String email);

    Optional<User> findById(Long userId);
}

