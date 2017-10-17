<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Error page for startup application</title>
    <%@include file="head.jsp" %>


</head>

<body>
<c:set var="userId" value="${user_id}"/>
<div class="flex-container">

    <%@include file="header.jsp" %>
    <%@include file="navbar.jsp" %>

    <aside>

    </aside>
    <article class="article">
        <h1 align="center">You have an error</h1>
        <div align="center">
            <button class="btn btn-outline-secondary" onclick="goBack()">Back to previous page</button>
            <script>
                function goBack() {
                    window.history.back();
                }
            </script>
        </div>
    </article>

    <%@include file="footer.jsp" %>
</div>
</body>
</html>