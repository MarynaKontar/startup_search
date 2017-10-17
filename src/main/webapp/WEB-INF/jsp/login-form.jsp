<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login form</title>
    <%@include file="head.jsp" %>
</head>

<body>
<div class="flex-container" align="center">
    <%@include file="header.jsp" %>
    <article>
        <h3 align="center">Login form</h3>
        <div align="center">
            <form action="${pageContext.request.contextPath}/login" method="post">
                <table align="center">
                    <tr>
                        <th>Login</th>
                        <td>
                            <input autofocus type="text" name="username" placeholder="Login" required>
                        </td>
                    </tr>
                    <tr>
                        <th>Password</th>
                        <td>
                            <input type="password" name="password" placeholder="Password" required>
                        </td>
                    </tr>
                    <tr>
                        <td align="center">
                            <button type="submit">Login</button>
                        </td>
                    </tr>
                    <br>
                </table>
            </form>
        </div>
        <br>
        <br>
        <div align="center">
            <form action="${pageContext.request.contextPath}/registration/" method="get">
                <input type="submit" value="Registration">
            </form>
        </div>
        <br>
        <div>
            <form action="${pageContext.request.contextPath}/" method="get">
                <input type="submit" value="Cancel">
            </form>
        </div>
        <br>
    </article>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>