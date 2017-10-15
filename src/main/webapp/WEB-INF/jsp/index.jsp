<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Start page for startup application</title>
    <%@include file="head.jsp" %>

</head>

<body>

<div class="flex-container">
    <%@include file="navbar.jsp" %>
    <aside>
    </aside>

    <%--<nav class="nav">--%>
        <%--<ul>--%>
            <%--<li><a href="${pageContext.request.contextPath}/login">Login</a><br></li>--%>
            <%--<br>--%>
            <%--<li><a href="${pageContext.request.contextPath}/registration/">Registration</a><br></li>--%>
            <%--<br>--%>
            <%--<li></li>--%>
        <%--</ul>--%>
    <%--</nav>--%>

    <article class="article">
        <c:forEach var="project" items="${projects}">
        <%@include file="short-project-info.jsp" %>
        </c:forEach>

        <c:forEach var="interest" items="${interests}">
            <%@include file="short-interest-info.jsp" %>
        </c:forEach>

    </article>

    <footer>Copyright &copy; javaEE group7</footer>
</div>
</body>
</html>