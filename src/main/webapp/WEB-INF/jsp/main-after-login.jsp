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
        <th>                           </th>
        <th><a href="${pageContext.request.contextPath}/user/personalAccount/${user_id}">Account</a></th>

    </header>
    <aside>



    </aside>
    <nav class="nav">
        <ul>
            <li><a href="${pageContext.request.contextPath}/user/personalAccount/${user_id}">Account </a><br></li><br>
            <li><a href="${pageContext.request.contextPath}/startup/create">Add project</a><br></li><br>
            <li><a href="${pageContext.request.contextPath}/interest/create/">Add interest</a></li><br>
            <li><a href="${pageContext.request.contextPath}/logout">Logout</a><br></li><br>
            <c:if test="${isAdmin}">
                <li><a href="${pageContext.request.contextPath}/user/users">All users</a><br></li><br>
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

                                <c:if test="${!(isOwner)}">
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
                                    <td class="tb1" style="width:60%">${project.address.country}</td>
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
                                    <td class="tb1" style="width:60%">${project.industry}</td>
                                </tr>
                                <tr>
                                    <td class="tb2" style="width:50%"><a href="${pageContext.request.contextPath}/startup/${project.id}">Learn more</a>
                                    </td>
                                </tr>
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