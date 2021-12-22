package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

// 필터를 사용하기 위해 필터 인터페이스 구현
@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    // HTTP 요청이 오면 doFilter 메소드 호출
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");

        // 더 많은 기능을 활용하기 위해 다운캐스팅
        // (HTTP 요청이 아닌 경우를 고려하여 ServletRequest 인터페이스를 사용)
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        try {
            log.info("REQUEST [{}][{}]", uuid, requestURI);
            // 다음 필터가 있으면 필터를 호출. 없으면 서블릿 호출
            // 이 로직 호출하지 않으면, 다음 단계 미진행(가장 중요한 부분)
            chain.doFilter(request, response);
        } catch(Exception e) {
            throw e;
        } finally {
            log.info("RESPONSE [{}][{}]", uuid, requestURI);
        }
    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
