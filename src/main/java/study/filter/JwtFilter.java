package study.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import study.jwt.JwtTokenProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//HttpServletRequest에 존재하는 jwt 토큰을 토대로 인증 작업을 수행하는 클래쓰
//doFilterInternal 메서드는 jwt토큰이 유효하면
// JwtTokenProvider를 통해 만든 Authentication 객체를 SecurityContextHolder에 저장
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    public static final String HeaderString = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    private final JwtTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Request Header에서 JWT 토큰 꺼내기
        String jwt = resolveToken(request);

        // 유효성검사
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request,response);
    }

    private String resolveToken(HttpServletRequest request) {
        String token = request.getHeader(HeaderString);

        if (StringUtils.hasText(token) && token.startsWith(TOKEN_PREFIX)) {
            return token.replace(TOKEN_PREFIX, "");
        }
        return null;
    }
}
