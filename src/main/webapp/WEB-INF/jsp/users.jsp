<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Table of all users for admn</title>
    <%@include file="head.jsp" %>
</head>

<body>
<div class="flex-container">
    <%@include file="navbar.jsp" %>
    <aside>

    </aside>

    <nav class="nav">
        <ul>
            <%--<li><a href="${pageContext.request.contextPath}/registration/"><h4>Add new user</h4></a><br></li><br>--%>
            <li><a href="${pageContext.request.contextPath}/user/personalAccount/${user_id}">Account</a><br></li><br>
            <li><a href="${pageContext.request.contextPath}/logout">Logout</a><br></li><br>
        </ul>
    </nav>

    <article class="article">
        <c:if test="${isAdmin}">
        <table border="2" width="70%" cellpadding="2">
            <tr><th>Login</th><th>Name</th><th>Last name</th><th>Email</th><th>Phone number</th><th>City</th><th>Country</th>
                <th>Profile photo link</th><th>Personal page photo link</th><th>youtube link</th><th>About</th>
                <th>Edit</th><th>Delete</th></tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.contact.email}</td>
                    <td>${user.contact.phoneNumber}</td>
                    <td>${user.contact.city}</td>
                    <td>${user.contact.country.label}</td>
                    <td><img src="${pageContext.request.contextPath}/${user.profileFotoLink}" alt=" ${user.username} profile photo" style="width: 76px;height: 82px;"></td>
                    <td><img src="${pageContext.request.contextPath}/${user.personalPageFotoLink}" alt=" ${user.username} personal page photo" class="image" style="max-width:100%;height:auto;">
                    </td>
                    <td>
                        <a href="${user.youtubeLink}" target="_blank">Video</a>
                        <!-- <iframe width="420" height="315"
                                src="$<!-- {user.youtubeLink}">
                        </iframe> -->
                    </td>

                    <td>${user.aboutMe}</td>
                    <td><a href="${pageContext.request.contextPath}/user/personalAccount/${user.id}/edit">edit</a></td>
                    <td><a href="${pageContext.request.contextPath}/user/personalAccount/${user.id}/delete">delete</a></td>

                </tr>
            </c:forEach>
        </table>
        </c:if>
    </article>

    <footer>Copyright &copy; javaEE group7</footer>
</div>
</body>
</html>

