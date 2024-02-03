package spring.webflux.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.webflux.user.dto.UserProfile;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ExternalApiService externalApiService;
    public UserProfile getUserProfile(String userId) {
        String userName = externalApiService.getUserName(userId);
        int age = externalApiService.getUserAge(userId);

        return new UserProfile(userName, age);
    }


}
