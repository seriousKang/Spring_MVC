package hello.servlet.web.servlet;

import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter writer = resp.getWriter();
        writer.write("<html>\n");
        writer.write("	<body>\n");
        writer.write("		<form action='/servlet/members/save' method='post'>\n");
        writer.write("			username:<input type='text' name='username'/><br/>\n");
        writer.write("			age:<input type='text' name='age'/><br/>\n");
        writer.write("			<button type='submit'>전송</button><br/>\n");
        writer.write("		</form>\n");
        writer.write("	</body>\n");
        writer.write("</html>\n");
    }
}
