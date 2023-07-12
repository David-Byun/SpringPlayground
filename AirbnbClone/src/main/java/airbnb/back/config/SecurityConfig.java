package airbnb.back.config;

import airbnb.back.util.jwt.JwtAccessDeniedHandler;
import airbnb.back.util.jwt.JwtAuthenticationEntryPoint;
import airbnb.back.util.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http
                .csrf().disable()
                .formLogin().disable()
                .authorizeRequests()
                .antMatchers("/", "/v3/api-docs/**", "/api/v1/**","/swagger-resources/**", "/swagger-ui/**", "/app/signup/**", "/app/signin/**", "/app/rooms/**", "/app/reviews/**", "/app/user/kakao/**" ).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling() // security exception 발생시 아래 로직 진행
                //인증이 실패했을 경우 Custom
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                //인가가 실패했을 경우 Custom
                .accessDeniedHandler(jwtAccessDeniedHandler);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
