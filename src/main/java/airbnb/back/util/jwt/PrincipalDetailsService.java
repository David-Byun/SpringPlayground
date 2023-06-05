package airbnb.back.util.jwt;

import airbnb.back.entity.user.User;
import airbnb.back.mapper.UserMapper;
import airbnb.back.util.exception.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static airbnb.back.util.BaseResponseStatus.NONE_EXIST_USER;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {
    private final UserMapper userMapper;

    @Override
    public PrincipalDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userMapper.findUserByEmail(email)
                .orElseThrow(() -> new UserException(NONE_EXIST_USER)).toEntity();

        log.info("loadUserByUsername, user={}", user.getEmail());

        return new PrincipalDetails(user);
    }
}
