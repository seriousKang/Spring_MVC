<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List"%>
<%@ page import="hello.servlet.domain.member.Member"%>
<%@ page import="hello.servlet.domain.member.MemberRepository"%>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = memberRepository.findAll();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <a href="index.html">메인</a>
    <table border=1>
        <th>id</th>
        <th>username</th>
        <th>age</th>
        <%
            for(Member member: members) {
                out.println("<tr>");
                out.println("<td>" + member.getId() + "</td>");
                out.println("<td>" + member.getUsername() + "</td>");
                out.println("<td>" + member.getAge() + "</td>");
                out.println("</tr>");
            }
        %>
    </table>
</body>
</html>