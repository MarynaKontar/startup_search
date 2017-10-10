<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Interest info page</title>
    <%@include file="head.jsp" %>
</head>

<body>
<div class="flex-container">
    <%@include file="navbar.jsp" %>
    <c:set var="isOwner" value="${interest.user.id == user_id}"/>
    <aside>
    </aside>
    <nav class="nav">
        <ul>
            <c:if test="${isOwner || isAdmin}">
                    <li><a href="${pageContext.request.contextPath}/startup/create">Add project</a></li>
                    <br>
                    <li><a href="${pageContext.request.contextPath}/interest/create">Add interest</a></li>
                    <br>
                    <li><a href="${pageContext.request.contextPath}/user/personalAccount/${interest.user.id}/edit">Edit
                        profile</a></li>
                    <br>
                    <li><a href="${pageContext.request.contextPath}/logout">Logout</a><br></li>
                    <br>
                    <li><a href="${pageContext.request.contextPath}/user/personalAccount/${interest.user.id}/delete">Delete
                        profile</a></li>
                    <br>
                </c:if>
            <c:if test="${!(isOwner || isAdmin)}">
                <li><a href="${pageContext.request.contextPath}/main">Home</a></li>
                <br>
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a><br></li>
                <br>
            </c:if>
        </ul>
    </nav>

    <article class="article">
        <%@include file="full-interest-info.jsp" %>
        <%--<c:if test="${!(isOwner)}">--%>
        <%--<tr>--%>
        <%--<td>--%>
        <%--<a href="${pageContext.request.contextPath}/messenger/${user_id}/${interest.user.id}">Submit project</a>--%>
        <%--</td>--%>
        <%--</tr>--%>
        <%--</c:if>--%>
    </article>
    <footer>Copyright &copy; javaEE group7</footer>
</div>
</body>
</html>