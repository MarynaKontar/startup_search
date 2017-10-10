<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Startup info page</title>
    <%@include file="head.jsp" %>
</head>

<body>
<div class="flex-container">
    <%@include file="navbar.jsp" %>
    <c:set var="isOwner" value="${project.user.id == user_id}"/>
    <aside>
    </aside>
    <nav class="nav">
        <ul>
            <c:if test="${isOwner || isAdmin}">
                    <li><a href="${pageContext.request.contextPath}/startup/create">Add project</a></li>
                    <br>
                    <li><a href="${pageContext.request.contextPath}/interest/create">Add interest</a></li>
                    <br>
                    <li><a href="${pageContext.request.contextPath}/user/personalAccount/${project.user.id}/edit">Edit
                        profile</a></li>
                    <br>
                    <li><a href="${pageContext.request.contextPath}/logout">Logout</a><br></li>
                    <br>
                    <li><a href="${pageContext.request.contextPath}/user/personalAccount/${project.user.id}/delete">Delete
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
                                    <td class="tb1" style="width:60%">${project.address.country.label}</td>
                                </tr>
                                <tr>
                                    <td class="tb1" style="width:30%">Region:</td>
                                    <td class="tb1" style="width:60%">${project.address.region}</td>
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
                                    <td class="tb1" style="width:30%">Idea:</td>
                                    <td class="tb1" style="width:60%">${project.businessPlan.idea}</td>
                                </tr>
                                <tr>
                                    <td class="tb1" style="width:30%">Current state:</td>
                                    <td class="tb1" style="width:60%">${project.businessPlan.currentState}</td>
                                </tr>
                                <tr>
                                    <td class="tb1" style="width:30%">Market:</td>
                                    <td class="tb1" style="width:60%">${project.businessPlan.market}</td>
                                </tr>
                            </div>
                        </table>
                    </div>
                </section>
    </article>

    <footer>Copyright &copy; javaEE group7</footer>
</div>
</body>
</html>