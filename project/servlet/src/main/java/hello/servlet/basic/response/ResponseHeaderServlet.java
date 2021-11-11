package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //[status-line]
        resp.setStatus(HttpServletResponse.SC_OK);
//        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        //[response-headers]
//        resp.setHeader("Content-Type", "text/plain;charset=utf-8");
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("my-header", "hello");

        //[header 편의 method]
        content(resp);

        //[cookie]
        cookie(resp);

        //[redirect]
        redirect(resp);

        //[message body]
        PrintWriter writer = resp.getWriter();
        writer.println("ok!");
    }

    private void content(HttpServletResponse resp) {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
    }

    private void cookie(HttpServletResponse resp) {
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600);  // 600s
        resp.addCookie(cookie);
    }

    private void redirect(HttpServletResponse resp) throws IOException {
//        resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
//        resp.setHeader("Location", "/basic/hello-form.html");
        resp.sendRedirect("/basic/hello-form.html");
    }
}
