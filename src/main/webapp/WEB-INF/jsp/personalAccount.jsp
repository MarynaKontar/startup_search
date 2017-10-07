<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>personal account</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
</head>
<body>

<c:set var="user_id">
    <sec:authentication property="principal.id"/>
</c:set>

<c:set var="isOwner" value="${user.id == user_id}"/>

<c:set var="isAdmin" value="false"/>

<sec:authorize access="hasRole('ADMIN')">
    <c:set var="isAdmin" value="true"/>
</sec:authorize>

<div class="flex-container">
    <header>
        <c:if test="${!isOwner}">
            <h3>Startup</h3>
        </c:if>

        <c:if test="${isOwner || isAdmin}">
            <h3>Personal account</h3>
            <a href=" /user/personalAccount/${user.id}/edit">Edit profile</a>
        </c:if>
    </header>

    <aside>
        <a href="${user.youtubeLink}" target="_blank" style="">link to youtube</a>
        <%--<iframe width="420" height="315"--%>
                <%--src="${user.youtubeLink}">--%>
        <%--</iframe>--%>
        <p>
            <c:choose>
            <c:when test="${empty(user.lastName) || empty(user.firstName)}">
        <h2>${user.firstName} ${user.lastName}</h2>
        </c:when>
        <c:otherwise>
            <h2>${user.username}</h2>
        </c:otherwise>
        </c:choose>
        </p>

        <adress>
            <p>${user.contact.country} ${user.contact.city}</p>
            <p>${user.contact.phoneNumber}</p>
            <p>${user.contact.email}</p>
        </adress>
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
                <li><a href="${pageContext.request.contextPath} /user/personalAccount/${user.id}/edit">Edit
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
                                <br>
                                <tr>
                                    <td class="tb2" style="width:50%"><a
                                            href="${pageContext.request.contextPath}/startup/${project.id}">Learn
                                        more</a>
                                    </td>
                                </tr>
                                <br>
                                <tr>
                                    <td>
                                        <c:if test="${isOwner || isAdmin}">
                                            <a href="${pageContext.request.contextPath}/startup/${project.id}/edit">Edit</a>
                                            <br>
                                            <a href="${pageContext.request.contextPath}/startup/${user.id}/${project.id}/delete">Delete</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </div>
                        </table>
                    </div>
                </section>
            </c:forEach>
        </section>
        <br>
        <br>
        <br>
        <section>
            <h3>Interests</h3>
            <c:forEach var="interest" items="${user.interests}">
                <section>
                    <div class="first"
                         style="float: left; width:27%; margin:0.5%; box-shadow: 10px 10px 5px grey; background-color: #f1f1f1">
                        <table>
                            <div class="second" style="height:80px">
                                <h2 align="center">Interest ${interest.name}</h2>
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
                                    <td class="tb1" style="width:30%">Budget:</td>
                                    <td class="tb1" style="width:60%">${interest.budget}</td>
                                </tr>
                                <br>
                                <tr>
                                    <td class="tb2" style="width:50%"><a
                                            href="${pageContext.request.contextPath}/interest/${interest.id}">Learn
                                        more</a>
                                    </td>
                                </tr>
                                <br>
                                <tr>
                                    <td>
                                        <c:if test="${isOwner || isAdmin}">
                                            <a href="${pageContext.request.contextPath}/interest/${interest.id}/edit">Edit</a><br>
                                            <br>
                                            <a href="${pageContext.request.contextPath}/interest/${user.id}/${interest.id}/delete">Delete</a>
                                        </c:if>
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
