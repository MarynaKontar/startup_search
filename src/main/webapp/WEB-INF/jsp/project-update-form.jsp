<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Update Project form</title>
    <%@include file="head.jsp" %>
</head>
<body>

<c:set var="user_id">
    <sec:authentication property="principal.id"/>
</c:set>
<div class="flex-container" align="center">
    <header>
        <h3 align="center">Edit a project</h3>
    </header>
    <article>
        <div align="center">
            <form:form method="post" action="${pageContext.request.contextPath}/startup/${startup.id}/update/">
                <form:input path="user.username" type="text" name="user.username" value="${user_id}" hidden="true"/>
                <form:input path="address.id" type="number" name="command.address.id" value="${startup.address.id}" hidden="true"/>



                <form:input path="id" type="number" name="startup.id" value="${startup.id}" hidden="true"/>


                <table align="center">
                    <tr align="center">
                        <th>Name of the project</th>
                        <td>
                            <form:input path="name" type="text" name="name" value="${startup.name}"/>
                        </td>
                    </tr>
                    <tr align="center">
                        <th>The announcement (brief description)</th>
                        <td>
                            <form:input path="description" type="text" name="description" value="${startup.description}"/>
                        </td>
                    </tr>
                    <tr align="center">
                        <th>Funds</th>
                        <td>
                            <form:input path="funds" type="number" name="funds" value="${startup.funds}"/>
                        </td>
                    </tr>
                    <tr align="center">
                        <th>Industry</th>
                        <td><form:select path="industry" name="industry">
                            <form:option value="">Industry</form:option>
                            <c:forEach items="${industries}" var="industry">
                                <form:option value="${industry}">${industry.label}</form:option>
                            </c:forEach>
                        </form:select></td>
                    </tr>
                    <tr align="center">
                        <th>Country</th>
                        <td><form:select path="address.country" name="country">
                            <form:option value="">Country</form:option>
                            <c:forEach items="${countries}" var="country">
                                <form:option value="${country}">${country.label}</form:option>
                            </c:forEach>
                        </form:select></td>
                    </tr>
                    <tr align="center">
                        <th>City</th>
                        <td>
                            <form:input path="address.city" type="text" name="address.city" value="${startup.address.city}"/>
                        </td>
                    </tr>








                    <tr></tr>
                    <tr align="center">
                        <td>
                            <input type="submit" value="Save">
                        </td>
                    </tr>
                </table>
            </form:form>
        </div>
    </article>
    <footer>Copyright &copy; javaEE group7</footer>
</div>
</body>
</html>