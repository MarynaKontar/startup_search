<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login form</title>
    <!--<link rel="stylesheet" href="../css/style.css" type="text/css"> -->
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