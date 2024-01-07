package study.jwt;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//리소스에 대한 자격을 갖추지 않은 접근에 대한 처리를 하는 클래스.
//accessDeniedException을 토대로 response를 생성
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    // 자격 관련 에러 처리, 403
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.getCause().printStackTrace();

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(accessDeniedException.getMessage());
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
}
