package study.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//인증 도중 발생한 에러를 처리하는 클래스
//AuthenticationException을 토대로 respose를 생성

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    //인증 관련 에러 처리, 401
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //추후 기입 예정
        authException.getCause().printStackTrace();

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
