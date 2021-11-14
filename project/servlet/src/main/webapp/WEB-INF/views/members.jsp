<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>title</title>
</head>
<body>
        <a href="/index.html">메인</a>
        <table border=1>
            <th>id</th>
            <th>username</th>
            <th>age</th>
            <c:forEach items="${members}" var="member">
                <tr>
                    <td>${member.id}</td>
                    <td>${member.username}</td>
                    <td>${member.age}</td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>