<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Registration form</title>
     <!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css"> -->
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
        <h3 align="center">Edit account</h3>
    </header>
    <article>
        <div align="center">
            <form method="post" action="/user/personalAccount/${user.username}/update/" >
                <table align="center">
                    <tr align="center">

                    <tr align="center">
                        <th>Name</th>
                        <td>
                            <input autofocus type="text" name="firstName" value="${user.firstName}" placeholder="first name">
                        </td>
                    </tr>
                    <tr align="center">
                        <th>Last name</th>
                        <td>
                            <input autofocus type="text" name="lastName" value="${user.lastName}" placeholder="last name">
                        </td>
                    </tr>
                    <tr align="center">
                        <th></th>
                        <td>
                            <input type="password" name="password" value="${user.password}" placeholder="Password" hidden>
                        </td>
                    </tr>

                    <tr align="center">
                        <th>Email</th>
                        <td>
                            <input type="text" name="contact.email"  value="${user.contact.email}" placeholder="Email" >
                        </td>
                    </tr>

                    <tr align="center">
                        <th>Phone number</th>
                        <td>
                            <input type="text" name="contact.phoneNumber" value="${user.contact.phoneNumber}" placeholder="phone number">
                        </td>
                    </tr>

                    <tr align="center">
                        <th>Country</th>
                        <td><select name="address.country">
                            <option value="${user.contact.country}">Country</option>
                            <c:forEach items="${countries}" var="country">
                                <option value="${user.contact.country}">${country}</option>
                            </c:forEach>
                        </select></td>
                    </tr>

                    <tr align="center">
                        <th>City</th>
                        <td>
                            <input type="text" name="contact.city" value="${user.contact.city}" placeholder="city">
                        </td>
                    </tr>

                    <tr align="center">
                        <th>Your video message</th>
                        <td>
                            <input type="text" name="youtubeLink" value="${user.youtubeLink}" placeholder="link to youtube">
                        </td>
                    </tr>

                    <tr align="center">
                        <th>About me</th>
                        <td>
                            <input type="text" name="aboutMe" value="${user.aboutMe}" placeholder="">
                        </td>
                    </tr>

                    <tr align="center">
                        <th>Skills</th>
                        <td>
                            <input type="text" name="skills" value="${user.skills}" placeholder="skills">
                        </td>
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