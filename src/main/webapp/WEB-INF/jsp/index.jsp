<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Start page for startup application</title>
    <%--<%@include file="header.jsp" %>--%>
    <%--<<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">--%>
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

<div class="flex-container">
    <header>
        <%--<%@include file="navbar.jsp" %>--%>
        <h3>Startup</h3>
        <a href="${pageContext.request.contextPath}/login">Login</a>
    </header>

    <aside>


    </aside>
    <nav class="nav">
        <ul>
            <li><a href="${pageContext.request.contextPath}/login">Login</a><br></li><br>
            <li><a href="${pageContext.request.contextPath}/registration">Registration</a><br></li><br>
            <li></li>
        </ul>
    </nav>

    <article class="article">
        <section>
            <c:forEach var="project" items="${projects}">
                <section>
                    <div class="first"
                         style="float: left; width:27%; margin:0.5%; box-shadow: 10px 10px 5px grey; background-color: #f1f1f1">
                        <table>
                        <div class="second" style="height:80px">

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
                                <td class="tb2" style="width:50%"><a href="/login">Learn more</a>
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