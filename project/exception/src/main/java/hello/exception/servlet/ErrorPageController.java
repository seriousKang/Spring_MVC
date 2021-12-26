package hello.exception.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
