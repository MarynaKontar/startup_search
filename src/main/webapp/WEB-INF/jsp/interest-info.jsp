<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Interest info page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
</head>

<body>
<c:set var="user_id">
    <sec:authentication property="principal.id"/>
</c:set>

<c:set var="isAdmin" value="false"/>
<c:set var="isOwner" value="${interest.user.id == user_id}"/>
<sec:authorize access="hasRole('ADMIN')">
    <c:set var="isAdmin" value="true"/>
</sec:authorize>

<div class="flex-container">

    <header>
        <h3>STARTUP</h3>
        <th>                           </th>
        <th><a href="${pageContext.request.contextPath}/user/personalAccount/${user_id}">Account</a></th>

    </header>

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
        <section>

        </section>

                <section>
                    <div class="first"
                         style="float: left; width:27%; margin:0.5%; box-shadow: 10px 10px 5px grey; background-color: #f1f1f1">
                        <table>
                            <div class="second" style="height:80px">
                                <h3 align="center">Interest ${interest.name}</h3>

                                <c:if test="${!(isOwner)}">
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
                                    <td class="tb1" style="width:60%">${interest.country}</td>
                                </tr>
                                <tr>
                                    <td class="tb1" style="width:30%">Industry:</td>
                                    <td class="tb1" style="width:60%">${interest.industry}</td>
                                </tr>
                                <tr>
                                    <td class="tb1" style="width:30%">Funds:</td>
                                    <td class="tb1" style="width:60%">${interest.budget}</td>
                                </tr>
                                <%--<c:if test="${!(isOwner)}">--%>
                                <%--<tr>--%>
                                    <%--<td>--%>
                                        <%--<a href="${pageContext.request.contextPath}/messenger/${user_id}/${interest.user.id}">Submit project</a>--%>
                                    <%--</td>--%>
                                <%--</tr>--%>
                                <%--</c:if>--%>
                            </div>
                        </table>
                    </div>
                </section>
    </article>
    <footer>Copyright &copy; javaEE group7</footer>
</div>
</body>
</html>