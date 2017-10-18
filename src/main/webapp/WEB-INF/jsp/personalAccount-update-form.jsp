<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Registration form</title>
    <%@include file="head.jsp" %>
</head>
<body>
<div class="flex-container" align="center">
    <c:set var="userId" value="${user.id}"/>
    <%@include file="header.jsp" %>
    <%@include file="navbar.jsp" %>

    <article class="article">
        <h3 align="center">Edit account</h3>

        <c:if test="${isOwner || isAdmin}">
            <form method="post" enctype="multipart/form-data"
                  action="${pageContext.request.contextPath}/storage/${user.id}/saveProfilePhoto">
                <table align="center">
                    <tr>
                        <td>Profile photo:</td>
                        <td><input type="file" id="file_upload" name="file" accept="image/*,image/jpeg"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" onClick="return validate()" value="LOAD A PROFILE PHOTO"/></td>
                    </tr>
                </table>
            </form>
            <br>
            <form method="post" enctype="multipart/form-data"
                  action="${pageContext.request.contextPath}/storage/${user.id}/savePersonalPagePhoto">
                <table align="center">
                    <tr>
                        <td>Personal page photo:</td>
                        <td><input type="file" id="file_upload1" name="file" accept="image/*,image/jpeg"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" onClick="return validate()" value="LOAD A PERSONAL PAGE PHOTO"/></td>
                    </tr>
                </table>
            </form>
            <h4>Message : ${message}</h4>
            <br>

            <%@include file="validatePhotoSize.jsp" %>

            <div align="center">
                <form method="post" action="${pageContext.request.contextPath}/user/personalAccount/${user.id}/update/">

                    <table align="center">
                        <tr align="center">
                        <tr>
                            <td><input type="number" name="user.id" value="${user.id}" hidden="true"/></td>
                        </tr>
                        <tr align="center">
                            <th>Login</th>
                            <td>
                                <input autofocus type="text" name="username" value="${user.username}"
                                       placeholder="login"
                                       required>
                            </td>
                        </tr>
                        <tr align="center">
                            <th>Name</th>
                            <td>
                                <input autofocus type="text" name="firstName" value="${user.firstName}"
                                       placeholder="first name">
                            </td>
                        </tr>
                        <tr align="center">
                            <th>Last name</th>
                            <td>
                                <input autofocus type="text" name="lastName" value="${user.lastName}"
                                       placeholder="last name">
                            </td>
                        </tr>
                        <tr align="center">
                            <th>Password</th>
                            <td>
                                <input type="password" name="password" value="${user.password}" placeholder="Password"
                                       required>
                            </td>
                        </tr>

                        <tr align="center">
                            <th>Email</th>
                            <td>
                                <input type="email" name="contact.email" value="${user.contact.email}"
                                       placeholder="Email"
                                       required>
                            </td>
                        </tr>

                        <tr align="center">
                            <th>Phone number</th>
                            <td>
                                <input type="tel" name="contact.phoneNumber" value="${user.contact.phoneNumber}"
                                       placeholder="phone number">
                            </td>
                        </tr>

                        <tr align="center">
                            <th>Country</th>
                            <td><select name="contact.country">
                                <option value="${user.contact.country}">Country</option>
                                <c:forEach items="${countries}" var="country">
                                    <option value="${country}">${country.label}</option>
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
                                <input type="text" name="youtubeLink" value="${user.youtubeLink}"
                                       placeholder="link to youtube">
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
        </c:if>
    </article>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>