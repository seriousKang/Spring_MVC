package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printStartLine(req);
        printHeaders(req);
        printHeaderUtils(req);
        printEtc(req);
    }

    private void printStartLine(HttpServletRequest req) {
        System.out.println("--- START_LINE ---");
        System.out.println("req.getMethod() = " + req.getMethod());
        System.out.println("req.getProtocol() = " + req.getProtocol());
        System.out.println("req.getScheme() = " + req.getScheme());

        System.out.println("req.getRequestURL() = " + req.getRequestURL());
        System.out.println("req.getRequestURI() = " + req.getRequestURI());
        System.out.println("req.getQueryString() = " + req.getQueryString());
        System.out.println("req.isSecure() = " + req.isSecure());
        System.out.println("--- START_LINE ---\n");
    }

    private void printHeaders(HttpServletRequest req) {
        System.out.println("--- HEADER ---");
        Enumeration<String> headerNames = req.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + " = " + req.getHeader(headerName));
        }
        System.out.println("--- HEADER ---\n");
    }

    private void printHeaderUtils(HttpServletRequest req) {
        System.out.println("--- HEADER UTIL ---");
        System.out.println("[ HOST ]");
        System.out.println("req.getServerName() = " + req.getServerName());
        System.out.println("req.getServerPort() = " + req.getServerPort());

        System.out.println("[ ACCEPT-LANGUAGE ]");
        System.out.println("req.getLocale() = " + req.getLocale());
        Enumeration<Locale> locales = req.getLocales();
        while(locales.hasMoreElements()) {
            Locale locale = locales.nextElement();
            System.out.println("locale = " + locale);
        }

        System.out.println("[ COOKIE ]");
        if(req.getCookies() != null) {
            for(Cookie cookie: req.getCookies()) {
                System.out.println(cookie.getName() + " = " + cookie.getValue());
            }
        }

        System.out.println("[ CONTENT ]");
        System.out.println("req.getContentType() = " + req.getContentType());
        System.out.println("req.getContentLength() = " + req.getContentLength());
        System.out.println("req.getCharacterEncoding() = " + req.getCharacterEncoding());

        System.out.println("--- HEADER UTIL ---\n");
    }

    private void printEtc(HttpServletRequest req) {
        System.out.println("--- ETC ---");

        System.out.println("[ REMOTE ]");
        System.out.println("req.getRemoteHost() = " + req.getRemoteHost());
        System.out.println("req.getRemoteAddr() = " + req.getRemoteAddr());
        System.out.println("req.getRemotePort() = " + req.getRemotePort());

        System.out.println("[ LOCAL ]");
        System.out.println("req.getLocalName() = " + req.getLocalName());
        System.out.println("req.getLocalAddr() = " + req.getLocalAddr());
        System.out.println("req.getLocalPort() = " + req.getLocalPort());

        System.out.println("--- ETC ---\n");
    }
}
