<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login form</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>

<body>
<div class="flex-container" align="center">
    <header>
        <h3 align="center">Login form</h3>
    </header>
    <article>
        <div align="center">
            <form action="login" method="post">
                <table align="center">
                    <tr>
                        <th>Login</th>
                        <td>
                            <input autofocus type="text" name="username" placeholder="Login">
                        </td>
                    </tr>
                    <tr>
                        <th>Password</th>
                        <td>
                            <input type="password" name="password" placeholder="Password">
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
            <form action="${pageContext.request.contextPath}/registration" method="get">
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
    <footer>Copyright &copy; javaEE group7</footer>
</div>
</body>
</html>