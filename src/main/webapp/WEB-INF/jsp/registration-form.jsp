<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Registration form</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
</head>
<body>
<div class="flex-container" align="center">
    <header>
        <h3 align="center">Registration form</h3>
    </header>
    <article>
        <div align="center">
            <form method="post" action="${pageContext.request.contextPath}/registration/" >
                <table align="center">
                    <tr align="center">
                        <th>Login</th>
                        <td>
                            <input autofocus type="text" name="username"  placeholder="Login" required>
                        </td>
                    </tr>
                    <tr align="center">
                        <th>Password</th>
                        <td>
                            <input type="password" name="password" placeholder="Password" required>
                        </td>
                    </tr>
                    <tr align="center">
                        <th>email</th>
                        <td>
                            <input type="email" name="contact.email" placeholder="Email" required>
                        </td>
                    </tr>
                    <tr></tr>
                    <tr align="center">
                        <td>
                            <input type="submit" value="Register">
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