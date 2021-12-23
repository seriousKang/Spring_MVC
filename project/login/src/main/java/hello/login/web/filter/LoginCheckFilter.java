package hello.login.web.filter;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {
    private static final String[] whiteList = {"/", "/member/add", "/login", "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();

        try {
            log.info("login check filter start [{}]", requestURI);

            if(isLoginCheckPath(requestURI)) {
                log.info("log check filter execute [{}]", requestURI);

                HttpSession session = httpRequest.getSession(false);
                if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("unauthenticated user request [{}]", requestURI);
                    // 로그인으로 리다이렉트
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    
                    // 미사용자는 다음으로 진행하지 않고 끝
                    // 이후의 필터, 서블릿, 컨트롤러는 더이상 호출 X
                    return;
                }
            }

            chain.doFilter(request, response);
        } catch(Exception e) {
            // 예외 로깅이 가능하지만, 톰캣(WAS)까지 예외를 던져야 함
            // 그렇지 않을 경우, 정상 흐름처럼 동작
            throw e;
        } finally {
            log.info("login check filter end [{}]", requestURI);
        }
    }

    /**
     * 화이트 리스트의 경우 인증 체크 X
     *   - 인증 필터를 적용해도 홈, 회원가입, 로그인 화면, css 같은 리소스에는 접근 가능해야
     *   - 화이트 리스트를 제외한 나머지 모든 경로에는 인증 체크 로직 적용
     */
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }
}
