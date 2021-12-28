package hello.exception.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class ErrorPageController {

    //RequestDispatcher 상수로 정의
    public static final String ERROR_EXCEPTION = "javax.servlet.error.exception";
    public static final String ERROR_EXCEPTION_TYPE = "javax.servlet.error.exception_type";
    public static final String ERROR_MESSAGE = "javax.servlet.error.message";
    public static final String ERROR_REQUEST_URI = "javax.servlet.error.request_uri";
    public static final String ERROR_SERVLET_NAME = "javax.servlet.error.servlet_name";
    public static final String ERROR_STATUS_CODE = "javax.servlet.error.status_code";

    @RequestMapping("/error-page/404")
    public String errorPage404(HttpServletRequest req, HttpServletResponse resp) {
        log.info("errorPage 404");
        printErrorInfo(req);
        return "error-page/404";
    }

    @RequestMapping("/error-page/500")
    public String errorPage500(HttpServletRequest req, HttpServletResponse resp) {
        log.info("errorPage 500");
        printErrorInfo(req);
        return "error-page/500";
    }

    /**
     * `produces = MediaType.APPLICATION_JSON_VALUE`
     *   - 클라이언트가 요청하는 HTTP header의 accept 값이 `application/json`일 경우, 메서드 호출
     *   - 즉, 클라이언트가 받고 싶은 미디어 타입이 json일 경우 호출
     */
    @RequestMapping(value = "/error-page/500", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> errorPage500Api(HttpServletRequest req, HttpServletResponse resp) {
        log.info("API errorPage 500");

        Integer statusCode = (Integer) req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Exception ex = (Exception) req.getAttribute(ERROR_EXCEPTION);

        Map<String, Object> result = new HashMap<>();
        result.put("status", statusCode);
        result.put("message", ex.getMessage());

        return new ResponseEntity<>(result, HttpStatus.valueOf(statusCode));
    }

    private void printErrorInfo(HttpServletRequest req) {
        log.info("ERROR_EXCEPTION = {}", req.getAttribute(ERROR_EXCEPTION));
        log.info("ERROR_EXCEPTION_TYPE = {}", req.getAttribute(ERROR_EXCEPTION_TYPE));
        log.info("ERROR_MESSAGE = {}", req.getAttribute(ERROR_MESSAGE));
        log.info("ERROR_REQUEST_URI = {}", req.getAttribute(ERROR_REQUEST_URI));
        log.info("ERROR_SERVLET_NAME = {}", req.getAttribute(ERROR_SERVLET_NAME));
        log.info("ERROR_STATUS_CODE = {}", req.getAttribute(ERROR_STATUS_CODE));

        log.info("dispatcherType = {}", req.getDispatcherType());
    }
}
