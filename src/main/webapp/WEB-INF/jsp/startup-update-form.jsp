<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Update Project form</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
    <%----%>
</head>
<body>

<c:set var="username">
    <sec:authentication property="principal.username"/>
</c:set>
<div class="flex-container" align="center">
    <header>
        <h3 align="center">Edit a project</h3>
    </header>
    <article>
        <div align="center">
            <form:form method="post" action="/startup/update/">
                <form:input path="user.username" type="text" name="user.username" value="${username}" hidden="true"/>
                <form:input path="address.id" type="number" name="command.address.id" value="${command.address.id}" hidden="true"/>
                <form:input path="id" type="number" name="command.id" value="${command.id}" hidden="true"/>


                <table align="center">
                    <tr align="center">
                        <th>Name of the project</th>
                        <td>
                            <form:input path="name" type="text" name="name" value="${command.name}"/>
                        </td>
                    </tr>
                    <tr align="center">
                        <th>The announcement (brief description)</th>
                        <td>
                            <form:input path="description" type="text" name="description" value="${command.description}"/>
                        </td>
                    </tr>
                    <tr align="center">
                        <th>Funds</th>
                        <td>
                            <form:input path="funds" type="number" name="funds" value="${command.funds}"/>
                        </td>
                    </tr>
                    <tr align="center">
                        <th>Industry</th>
                        <td><form:select path="industry" name="industry">
                            <form:option value="">Industry</form:option>
                            <c:forEach items="${industries}" var="industry">
                                <form:option value="${industry}">${industry}</form:option>
                            </c:forEach>
                        </form:select></td>
                    </tr>
                    <tr align="center">
                        <th>Country</th>
                        <td><form:select path="address.country" name="country">
                            <form:option value="">Country</form:option>
                            <c:forEach items="${countries}" var="country">
                                <form:option value="${country}">${country}</form:option>
                            </c:forEach>
                        </form:select></td>
                    </tr>
                    <tr align="center">
                        <th>City</th>
                        <td>
                            <form:input path="address.city" type="text" name="address.city" value="${command.address.city}"/>
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