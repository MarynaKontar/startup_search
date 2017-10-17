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
    <%@include file="header.jsp" %>
    <%@include file="navbar.jsp" %>

    <aside>
    </aside>

    <%--<nav class="nav">--%>
        <%--<ul>--%>
            <%--&lt;%&ndash;<li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<form action="${pageContext.request.contextPath}/search/projectsByIndustry/" method="get">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<select path="industry" name="industry">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<option value="">Industry</option>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<c:forEach items="${industries}" var="industry">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<option value="${industry}">${industry.label}</option>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</select>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<br><button type="submit">Search projects by industry</button>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<br></li>&ndash;%&gt;--%>
            <%--<br>--%>
            <%--<li><a href="${pageContext.request.contextPath}/main">Home</a><br></li>--%>
                <%--<br>--%>
            <%--<li><a href="${pageContext.request.contextPath}/user/personalAccount/${user_id}">Account </a><br></li>--%>
            <%--<br>--%>
            <%--<li><a href="${pageContext.request.contextPath}/startup/create">Add project</a><br></li>--%>
            <%--<br>--%>
            <%--<li><a href="${pageContext.request.contextPath}/interest/create/">Add interest</a></li>--%>
            <%--<br>--%>
            <%--<li><a href="${pageContext.request.contextPath}/logout">Logout</a><br></li>--%>
            <%--<br>--%>
            <%--<c:if test="${isAdmin}">--%>
                <%--<li><a href="${pageContext.request.contextPath}/user/users">All users</a><br></li>--%>
                <%--<br>--%>
            <%--</c:if>--%>
        <%--</ul>--%>
    <%--</nav>--%>

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
    <%@include file="footer.jsp" %>
</div>
</body>
</html>