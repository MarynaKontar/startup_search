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
<c:set var="userId" value="${user_id}"/>
<div class="flex-container">

    <%@include file="header.jsp" %>
    <%@include file="navbar.jsp" %>

    <aside>
        <%@include file="searchBar.jsp" %>
    </aside>
    <article class="article">
        <h3 align="center">Startups</h3>
        <c:forEach var="project" items="${projects}">
        <%@include file="short-project-info.jsp" %>
        </c:forEach>
        <br>
        <br>
        <h3 align="center">Interests</h3>
        <c:forEach var="interest" items="${interests}">
            <%@include file="short-interest-info.jsp" %>
        </c:forEach>

    </article>

    <%@include file="footer.jsp" %>
</div>
</body>
</html>