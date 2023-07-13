package jwt.redis;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtEntryPoint jwtEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailService customUserDetailService; // UserDetailsService 유저 정보를 가져오기 위한 클래스. JWT 기반으로 구현해야 하기 때문에 따로 커스터마이징


    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()

                .and()
                .csrf().disable()
                .authorizeRequests() //5
                .antMatchers("/", "/join/**", "/login", "/health").permitAll()
                .anyRequest().hasRole("USER")

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint)

                .and()
                .logout().disable() //6 JWT기반으로 로그인 로그아웃을 처리할 것이기 때문에 logout은 disable 해주었고, 스프링 시큐리티는 기본 로그인 / 로그아웃 시 세션을 통해 유저 정보들을 저장하지만, Redis 사용하므로 STATELESS로 설정
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and() //7 JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 필터 추가
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/h2-console/**", "/favicon.ico");
    }

    /*
        AuthenticationManagerBuilder : 인증객체를 만듦
        auth.userDetailsService(customUserDetailService)는 사용자 정보를 조회하는 UserDetailService를 설정하는 부분(인증에 필요한 사용자 정보를 가져오며, 데이터베이스 파일 외부서비스 등에서 가져옴)
        customUserDetailService는 사용자 정보를 제공하는 커스텀한 서비스
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }


}
