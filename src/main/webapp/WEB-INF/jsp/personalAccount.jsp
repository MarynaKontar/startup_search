<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
    <title>Personal account</title>
    <%@include file="head.jsp" %>
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
        }

        /* Create two equal columns that floats next to each other */
        .column {
            float: left;
            width: 50%;
            padding: 10px;
        }

        /* Clear floats after the columns */
        .row:after {
            content: "";
            display: table;
            clear: both;
        }

        /* Responsive layout - makes the two columns stack on top of each other instead of next to each other */
        @media (max-width: 600px) {
            .column {
                width: 100%;
            }
        }
    </style>
</head>
<body>
<div class="flex-container">
    <%@include file="navbar.jsp" %>
    <c:set var="isOwner" value="${user.id == user_id}"/>
    <h3 align="center">Personal account</h3>
    <aside>
        <div class="row">
            <div class="column">
                <img src="${pageContext.request.contextPath}/${user.profileFotoLink}"
                     style="width:512px;height:auto;">
            </div>
            <div class="column" align="right">
                <img src="${pageContext.request.contextPath}/${user.personalPageFotoLink}"
                     alt=" ${user.username} personal page photo" class="image" style="width: auto;height:84px;">
                <p>
                    <c:choose>
                    <c:when test="${!empty(user.lastName) || !empty(user.firstName)}">
                <h2>${user.firstName} ${user.lastName}</h2>
                </c:when>
                <c:otherwise>
                    <h2>${user.username}</h2>
                </c:otherwise>
                </c:choose>
                </p>
                <adress>
                    <p>${user.contact.country.label} ${user.contact.city}</p>
                    <p>${user.contact.phoneNumber}</p>
                    <p>${user.contact.email}</p>
                </adress>
                <a href="${user.youtubeLink}" target="_blank" style="">link to youtube</a>
                <%--<iframe width="420" height="315"--%>
                <%--src="${user.youtubeLink}">--%>
                <%--</iframe>--%>

            </div>
        </div>
    </aside>

    <nav class="nav">
        <ul>
            <li><a href="${pageContext.request.contextPath}/main">Home</a></li>
            <br>
            <c:if test="${isOwner || isAdmin}">
                <li><a href="${pageContext.request.contextPath}/startup/create">Add project</a></li>
                </br>
                <li><a href="${pageContext.request.contextPath}/interest/create/">Add interest</a></li>
                </br>
                <li><a href="${pageContext.request.contextPath}/user/personalAccount/${user.id}/edit">Edit
                    profile</a></li>
                <br>
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a><br></li>
                <br>
                <li><a href="${pageContext.request.contextPath}/user/personalAccount/${user.id}/delete">Delete
                    profile</a></li>
                <br>
            </c:if>

            <c:if test="${!isOwner && !isAdmin}">
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a><br></li>
                <br>
                <li><a href="${pageContext.request.contextPath}/user/personalAccount/${user_id}/">Account</a></li>
                <br>
            </c:if>
        </ul>
    </nav>

    <article class="article">
        <c:if test="${isOwner}">
            <section>
                <h1>Add project</h1>
                <p>Place your project on the Startup.Network or operational business in order to:</p>
                <ul>
                    <li>find investments</li>
                    <li>get a loan for development</li>
                    <li>sell a business (share)</li>
                </ul>
                <br>
                <a href="${pageContext.request.contextPath}/startup/create">Add new project</a>
                <br>
            </section>
            <section>
                <h1>Add interest</h1>
                <p>Detail your investment interests and get up-to-date info about projects and existing businesses that
                    are:</p>
                <ul>
                    <li>attracting investments</li>
                    <li>looking for a loan for development</li>
                    <li>selling shares in the business</li>
                </ul>
                <br>
                <a href="${pageContext.request.contextPath}/interest/create">Add new interest</a>
                <br>
                <br>
            </section>
        </c:if>

        <section>
            <h4>Startups</h4>
            <c:forEach var="project" items="${user.projects}">
                <%@include file="short-project-info.jsp" %>
            </c:forEach>
        </section>
        <br>
        <br>
        <br>
        <section>
            <h4>Interests</h4>
            <c:forEach var="interest" items="${user.interests}">
                <%@include file="short-interest-info.jsp" %>
            </c:forEach>
        </section>
    </article>


    <footer>Copyright &copy; javaEE group7</footer>
</div>

</body>
</html>
