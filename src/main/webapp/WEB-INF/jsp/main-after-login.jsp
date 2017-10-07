<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Main page for startup application after login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
</head>

<body>
<c:set var="user_id">
    <sec:authentication property="principal.id"/>
</c:set>

<c:set var="isAdmin" value="false"/>
<%--<c:set var="isOwner" value="${user.id == user_id}"/>--%>
<sec:authorize access="hasRole('ADMIN')">
    <c:set var="isAdmin" value="true"/>
</sec:authorize>
<div class="flex-container">


    <header>
        <h3>STARTUP</h3>
        <th></th>
        <th><a href="${pageContext.request.contextPath}/user/personalAccount/${user_id}">Account</a></th>

    </header>
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
            <h3 align="center">All startups</h3>
        </section>
        <section>
            <c:forEach var="project" items="${projects}">
                <section>
                    <div class="first"
                         style="float: left; width:27%; margin:0.5%; box-shadow: 10px 10px 5px grey; background-color: #f1f1f1">
                        <table>
                            <div class="second" style="height:80px">

                                <h3 align="center">Startup ${project.name}</h3>

                                <c:if test="${!(project.user.id == user_id)}">
                                    <tr>
                                        <td class="tb1" style="width:30%">
                                            <a href="${pageContext.request.contextPath}/user/personalAccount/${project.user.id}">
                                                To startup's owner page</a>
                                        </td>
                                    </tr>
                                </c:if>

                                <tr>
                                    <td class="tb1" style="width:60%">${project.lastChange}</td>
                                </tr>
                                <tr>
                                    <td class="tb1" style="width:30%">Description:</td>
                                    <td class="tb1" style="width:60%">${project.description}</td>
                                </tr>
                                <tr>
                                    <td class="tb1" style="width:30%">Country:</td>
                                    <td class="tb1" style="width:60%">${project.address.country.label}</td>
                                </tr>
                                <tr>
                                    <td class="tb1" style="width:30%">City:</td>
                                    <td class="tb1" style="width:60%">${project.address.city}</td>
                                </tr>
                                <tr>
                                    <td class="tb1" style="width:30%">Total:</td>
                                    <td class="tb1" style="width:60%">${project.funds}</td>
                                </tr>
                                <tr>
                                    <td class="tb1" style="width:30%">Minimum Investment:</td>
                                    <td class="tb1" style="width:60%">${project.minInvestment}</td>
                                </tr>
                                <tr>
                                    <td class="tb1" style="width:30%">Industry:</td>
                                    <td class="tb1" style="width:60%">${project.industry.label}</td>
                                </tr>
                                <tr>
                                    <td class="tb2" style="width:50%"><a
                                            href="${pageContext.request.contextPath}/startup/${project.id}">Learn
                                        more</a>
                                    </td>
                                </tr>
                            </div>
                        </table>
                    </div>
                </section>
            </c:forEach>
        </section>


        <br>
        <section>
            <%--<h3>Interests</h3>--%>
            <c:forEach var="interest" items="${interests}">
                <section>
                    <div class="first"
                         style="float: left; width:27%; margin:0.5%; box-shadow: 10px 10px 5px grey; background-color: #f1f1f1">
                        <table>
                            <div class="second" style="height:80px">
                                <h3 align="center">Interest ${interest.name}</h3>
                                <c:if test="${!(interest.user.id == user_id)}">
                                    <tr>
                                        <td class="tb1" style="width:30%">
                                            <a href="${pageContext.request.contextPath}/user/personalAccount/${interest.user.id}">
                                                To interest's owner page</a>
                                        </td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <td class="tb1" style="width:60%">${interest.lastChange}</td>
                                </tr>
                                <tr>
                                    <td class="tb1" style="width:30%">Description:</td>
                                    <td class="tb1" style="width:60%">${interest.description}</td>
                                </tr>
                                <tr>
                                    <td class="tb1" style="width:30%">Country:</td>
                                    <td class="tb1" style="width:60%">${interest.country.label}</td>
                                </tr>
                                <tr>
                                    <td class="tb1" style="width:30%">Industry:</td>
                                    <td class="tb1" style="width:60%">${interest.industry.label}</td>
                                </tr>
                                <tr>
                                    <td class="tb1" style="width:30%">Budget:</td>
                                    <td class="tb1" style="width:60%">${interest.budget}</td>
                                </tr>
                                <br>
                                <tr>
                                    <td class="tb2" style="width:50%"><a
                                            href="${pageContext.request.contextPath}/login">Learn
                                        more</a>
                                    </td>
                                </tr>
                                <br>

                            </div>
                        </table>
                    </div>
                </section>
            </c:forEach>
        </section>


    </article>

    <footer>Copyright &copy; javaEE group7</footer>
</div>
</body>
</html>