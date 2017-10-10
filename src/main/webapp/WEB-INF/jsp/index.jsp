<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Start page for startup application</title>
    <%@include file="head.jsp" %>
</head>

<body>

<div class="flex-container">
    <%@include file="navbar.jsp" %>

    <aside>
    </aside>
    <nav class="nav">
        <ul>
            <li><a href="${pageContext.request.contextPath}/login">Login</a><br></li><br>
            <li><a href="${pageContext.request.contextPath}/registration/">Registration</a><br></li><br>
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

                                <h3 align="center">Startup ${project.name}</h3>
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
                                    <td class="tb2" style="width:50%"><a href="${pageContext.request.contextPath}/login">Learn more</a>
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