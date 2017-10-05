<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Main page for startup application after login</title>

    <!-- <link rel="stylesheet" href="../css/style.css" type="text/css"> -->
    <style>

        .flex-container {
            display: -webkit-flex;
            display: flex;
            -webkit-flex-flow: row wrap;
            flex-flow: row wrap;
            text-align: center;
        }

        .flex-container > * {
            padding: 15px;
            -webkit-flex: 1 100%;
            flex: 1 100%;
        }

        .article {
            text-align: left;
        }

        aside {
            text-align: right;
        }

        header {
            background: burlywood;
            color: white;
        }

        footer {
            background: #aaa;
            color: white;
        }

        .nav {
            background: #eee;
        }

        .nav ul {
            list-style-type: none;
            padding: 0;
        }

        .nav ul a {
            text-decoration: none;
        }

        @media all and (min-width: 768px) {
            .nav {
                text-align: left;
                -webkit-flex: 1 auto;
                flex: 1 auto;
                -webkit-order: 1;
                order: 1;
            }

            .article {
                -webkit-flex: 5 0px;
                flex: 5 0px;
                -webkit-order: 2;
                order: 2;
            }

            footer {
                -webkit-order: 3;
                order: 3;
            }
        }
    </style>

</head>

<body>

<c:set var="user_id">
    <sec:authentication property="principal.id"/>
</c:set>

<c:set var="isAdmin" value="false"/>

<sec:authorize access="hasRole('ADMIN')">
    <c:set var="isAdmin" value="true"/>
</sec:authorize>
<div class="flex-container">

    <header>
        <h3>STARTUP</h3>
        <th><a href="${pageContext.request.contextPath}/user/personalAccount/${user_id}">Account</a></th>
    </header>

    <aside>

    </aside>

    <nav class="nav">
        <ul>
            <li><a href="${pageContext.request.contextPath}/registration/"><h4>Add new user</h4></a><br></li><br>
            <li><a href="${pageContext.request.contextPath}/user/personalAccount/${user_id}">Account</a><br></li><br>
            <li><a href="${pageContext.request.contextPath}/logout">Logout</a><br></li><br>
        </ul>
    </nav>

    <article class="article">
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
                    <td>${user.contact.country}</td>
                    <td><img src="${user.profileFotoLink}" alt=" ${user.username} profile photo" style="width: 76px;height: 82px;"></td>
                    <td><img src="${user.personalPageFotoLink}" alt=" ${user.username} personal page photo" style="width: 76px;height: 82px;"></td>
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
    </article>

    <footer>Copyright &copy; javaEE group7</footer>
</div>
</body>
</html>

