package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8081/request-param?username=hello&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RequestParamServlet.service");

        System.out.println("[ 전체 파라미터 조회 ]");
        Enumeration<String> parameterNames = req.getParameterNames();
        while(parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            System.out.println(parameterName + " = " + req.getParameter(parameterName));
        }

        System.out.println("[ 단일 파라미터 조회 ]");
        System.out.println("username = " + req.getParameter("username"));
        System.out.println("age = " + req.getParameter("age"));

        System.out.println("[ 이름이 같은 복수 파라미터 조회 ]");
        String[] usernames = req.getParameterValues("username");
        System.out.println("usernames = " + Arrays.toString(usernames));

        resp.getWriter().write("OK");
    }
}
