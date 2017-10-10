<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Main page for startup application after login</title>
    <%@include file="head.jsp" %>
</head>

<body>

<div class="flex-container">
    <%@include file="navbar.jsp" %>

    <aside>
    </aside>

    <nav class="nav">
        <ul>
            <%--<li>--%>
                <%--<form action="${pageContext.request.contextPath}/search/projectsByIndustry/" method="get">--%>
                    <%--<select path="industry" name="industry">--%>
                        <%--<option value="">Industry</option>--%>
                        <%--<c:forEach items="${industries}" var="industry">--%>
                            <%--<option value="${industry}">${industry.label}</option>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>
                    <%--<br><button type="submit">Search projects by industry</button>--%>
                <%--</form>--%>
                <%--<br></li>--%>
            <br>
            <li><a href="${pageContext.request.contextPath}/main">Home</a><br></li>
                <br>
            <li><a href="${pageContext.request.contextPath}/user/personalAccount/${user_id}">Account </a><br></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/startup/create">Add project</a><br></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/interest/create/">Add interest</a></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/logout">Logout</a><br></li>
            <br>
            <c:if test="${isAdmin}">
                <li><a href="${pageContext.request.contextPath}/user/users">All users</a><br></li>
                <br>
            </c:if>
        </ul>
    </nav>

    <article class="article">
        <section>
            <h3 align="center">Startups</h3>
            <c:forEach var="project" items="${projects}">
                <%@include file="short-project-info.jsp" %>
            </c:forEach>
        </section>
        <br>
        <section>
            <h3 align="center">Interests</h3>
            <c:forEach var="interest" items="${interests}">
                <%@include file="short-interest-info.jsp" %>
            </c:forEach>
        </section>
    </article>
    <footer>Copyright &copy; javaEE group7</footer>
</div>
</body>
</html>