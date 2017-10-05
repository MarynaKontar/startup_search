<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Interest create form</title>
    <!-- <link rel="stylesheet" href="styles.css"> -->
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

<c:set var="user_id">
    <sec:authentication property="principal.id"/>
</c:set>
<div class="flex-container" align="center">
    <header>
        <h3 align="center">Adding an interest</h3>
    </header>
    <article>
        <div align="center">
            <form method="post" action="${pageContext.request.contextPath}/interest/create/" >
                <input type="text" name="user.id" value="${user_id}" hidden>
                <table align="center">
                    <tr align="center">
                        <th>Name of the interest</th>
                        <td>
                            <input autofocus type="text" name="name" placeholder="your name of interest">
                        </td>
                    </tr>
                    <tr align="center">
                        <th>The announcement (brief description)</th>
                        <td>
                            <input type="text" name="description" placeholder="">
                        </td>
                    </tr>
                    <tr align="center">
                        <th>Funds</th>
                        <td>
                            <input type="number" name="budget" min="1000" max="1000000000" step="100" placeholder="budget that you want to invest">
                        </td>
                    </tr>
                    <tr align="center">
                        <th>Industry</th>
                        <td><select name="industry">
                            <option value="">Industry</option>
                            <c:forEach items="${industries}" var="industry">
                                <option value="${industry}">${industry}</option>
                            </c:forEach>
                        </select></td>
                    </tr>
                    <tr align="center">
                        <th>Country</th>
                        <td><select name="country">
                            <option value="">Country</option>
                            <c:forEach items="${countries}" var="country">
                                <option value="${country}">${country}</option>
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