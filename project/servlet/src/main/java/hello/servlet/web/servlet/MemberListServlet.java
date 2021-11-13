package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        List<Member> members = memberRepository.findAll();

        PrintWriter writer = resp.getWriter();
        writer.write("<html>\n");
        writer.write("<body>\n");
        writer.write("<a href='/index.html'>메인</a>\n");
        writer.write("<table border='1'>  \n");
        writer.write("	<th>id</th>      \n");
        writer.write("	<th>username</th>\n");
        writer.write("	<th>age</th>     \n");
        for(Member member: members) {
            writer.write("	<tr>             \n");
            writer.write("		<td>" + member.getId() + "</td>   \n");
            writer.write("		<td>" + member.getUsername() + "</td>\n");
            writer.write("		<td>" + member.getAge() + "</td>  \n");
            writer.write("	</tr>            \n");
        }
        writer.write("</table>           \n");
        writer.write("</body>\n");
        writer.write("</html>\n");
    }
}
