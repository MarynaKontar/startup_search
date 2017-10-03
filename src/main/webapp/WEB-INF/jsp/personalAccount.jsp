<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <!-- <link rel="stylesheet" href="/WEB-INF/css/style.css"> -->
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

<c:set var="username">
    <sec:authentication property="principal.username"/>
</c:set>

<c:set var="isOwner" value="${user.username == username}"/>

<c:set var="isAdmin" value="false"/>

<sec:authorize access="hasRole('ADMIN')">
    <c:set var="isAdmin" value="true"/>
</sec:authorize>

<div class="flex-container">
    <header>
        <h3>Personal account</h3>
        <c:if test="${isOwner || isAdmin}">
            <a href=" /user/personalAccount/${user.username}/edit">Edit profile</a>
        </c:if>
    </header>

    <aside>
        <a href="${user.youtubeLink}" target="_blank" style="">link to youtube</a>

        <p><c:choose>
            <c:when test="${empty(user.lastName) || empty(user.firstName)}">
                <h2>${user.firstName} ${user.lastName}</h2>
            </c:when>
            <c:otherwise>
                <h2>${user.username}</h2>
            </c:otherwise>
            </c:choose>
        </p>

        <adress>
            <p>${user.contact.country} , ${user.contact.city}</p>
            <p>${user.contact.phoneNumber}</p>
            <p>${user.contact.email}</p>
        </adress>
    </aside>
    <nav class="nav">
        <ul>
            <li><a href="${pageContext.request.contextPath}/main/">Home</a></li>
            <br>
            <c:if test="${isOwner || isAdmin}">
                <li><a href="${pageContext.request.contextPath}/startup/create">Add project</a></li>
                </br>
                <li><a href="${pageContext.request.contextPath}/interest/create/">Add interest</a></li>
                </br>
                <li><a href="${pageContext.request.contextPath} /user/personalAccount/${user.username}/edit">Edit
                    profile</a></li>
                <br>
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a><br></li>
                <br>
                <li><a href="${pageContext.request.contextPath}/user/personalAccount/${user.username}/delete">Delete
                    profile</a></li>
                <br>
            </c:if>

            <c:if test="${!isOwner}">
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a><br></li>
                <br>
                <li><a href="${pageContext.request.contextPath}/user/personalAccount/${username}/">${username} account</a></li>
                <br>

            </c:if>


        </ul>
    </nav>

    <article class="article">
        <c:if test="${isOwner || isAdmin}">
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
                <a href="${pageContext.request.contextPath}/main/">Add new interest</a>
                <br>
            </section>
        </c:if>

        <section>
            <c:forEach var="project" items="${user.projects}">
                <section>
                    <div class="first"
                         style="float: left; width:27%; margin:0.5%; box-shadow: 10px 10px 5px grey; background-color: #f1f1f1">
                        <table>
                            <div class="second" style="height:80px">
                                <tr>
                                    <c:if test="${isOwner || isAdmin}">
                                        <a href="${pageContext.request.contextPath}/startup/${project.id}/edit">Edit</a><br>
                                        <a href="${pageContext.request.contextPath}/startup/${user.username}/${project.id}/delete">Delete</a>
                                    </c:if>
                                </tr>
                                <tr>
                                    <td class="tb1" style="width:30%">Project Name:</td>
                                    <td class="tb1" style="width:60%">${project.name}</td>
                                </tr>
                                <tr>
                                    <td class="tb1" style="width:30%">Industry:</td>
                                    <td class="tb1" style="width:60%">${project.industry}</td>
                                </tr>
                                <tr>
                                    <td class="tb1" style="width:30%">Description:</td>
                                    <td class="tb1" style="width:60%">${project.description}</td>
                                </tr>
                                <tr>
                                    <td class="tb1" style="width:30%">City:</td>
                                    <td class="tb1" style="width:60%">${project.address.city}</td>
                                </tr>
                                <tr>
                                    <td class="tb1" style="width:30%">Country:</td>
                                    <td class="tb1" style="width:60%">${project.address.country}</td>
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
                                    <td class="tb1" style="width:30%">Changed:</td>
                                    <td class="tb1" style="width:60%">${project.lastChange}</td>
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
    </article>


    <footer>Copyright &copy; javaEE group7</footer>
</div>

</body>
</html>
