package study.config;
//spring security에ㅓ JWT가 필요한 설정을 지정하는 클래스
//JwtFilter를 UsernamePasswordAuthenticationFilter 앞에 추가함
//-> 전통적인 id와 pw로 인증을 하는 것이 나닌, jwt 토큰을 이용한 인증방식으로 시큐리티 동작

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import study.filter.JwtFilter;
import study.jwt.JwtTokenProvider;

@Configuration
@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtTokenProvider tokenProvider;
    @Override
    public void configure(HttpSecurity http) {
        JwtFilter customFilter = new JwtFilter(tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
