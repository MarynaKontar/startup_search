<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Interest create form</title>
    <%@include file="head.jsp" %>
</head>
<body>
<div class="flex-container" align="center">
    <%@include file="navbar.jsp" %>
    <article>
        <h3 align="center">Adding an interest</h3>
        <div align="center">
            <form method="post" action="${pageContext.request.contextPath}/interest/create/" >
                <input type="text" name="user.id" value="${user_id}" hidden>
                <table align="center">
                    <tr align="center">
                        <th>Name of the interest</th>
                        <td>
                            <input autofocus type="text" name="name" placeholder="your name of interest" required="required">
                        </td>
                    </tr>
                    <tr align="center">
                        <th>The announcement (brief description)</th>
                        <td>
                            <input type="text" name="description" placeholder="" required>
                        </td>
                    </tr>
                    <tr align="center">
                        <th>Funds</th>
                        <td>
                            <input type="number" name="budget" min="1000" max="1000000000" step="100" placeholder="budget that you want to invest" required>
                        </td>
                    </tr>
                    <tr align="center">
                        <th>Industry</th>
                        <td><select name="industry">
                            <option value="">Industry</option>
                            <c:forEach items="${industries}" var="industry">
                                <option value="${industry}">${industry.label}</option>
                            </c:forEach>
                        </select></td>
                    </tr>
                    <tr align="center">
                        <th>Country</th>
                        <td><select name="country">
                            <option value="">Country</option>
                            <c:forEach items="${countries}" var="country">
                                <option value="${country}">${country.label}</option>
                            </c:forEach>
                        </select></td>
                    </tr>

                    <tr></tr>
                    <tr align="center">
                        <td>
                            <input type="submit" value="Save">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </article>
    <footer>Copyright &copy; javaEE group7</footer>
</div>
</body>
</html>