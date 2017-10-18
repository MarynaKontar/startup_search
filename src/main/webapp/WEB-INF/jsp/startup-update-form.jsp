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
<div class="flex-container" align="center">
    <c:set var="userId" value="${command.user.id}"/>
    <%@include file="header.jsp" %>
    <%@include file="navbar.jsp" %>


    <article class="article">
        <h3 align="center">Edit a project</h3>
        <c:if test="${isOwner || isAdmin}">
        <form method="post" enctype="multipart/form-data"
              action="${pageContext.request.contextPath}/storage/${command.id}/saveProjectPhoto">
            <table align="center">
                <tr>
                    <td>Project photo:</td>
                    <td><input type="file" id="file_upload" name="file" accept="image/*,image/jpeg" size="128"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" onClick="return validate()" value="LOAD A PROJECT PHOTO"/></td>
                </tr>
            </table>
        </form>
        <br>

        <%@include file="validatePhotoSize.jsp" %>

        <div align="center">
            <form:form method="post" action="${pageContext.request.contextPath}/startup/${command.id}/update/">
                <form:input path="user.id" type="number" name="user.id" value="${command.user.id}" hidden="true"/>

                <c:if test="${command.address.id!=0 || command.address.id!=null}">
                <form:input path="address.id" type="number" name="command.address.id" value="${command.address.id}" hidden="true"/>
                </c:if>
                <c:if test="${command.businessPlan.id!=0 || command.businessPlan.id!=null}">
                <form:input path="businessPlan.id" type="number" name="command.businessPlan.id" value="${command.businessPlan.id}" hidden="true"/>
                </c:if>
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
                            <form:input path="funds" type="number" name="funds" min="1000" max="1000000000" step="100"
                                        value="${command.funds}"/>
                        </td>
                    </tr>
                    <tr align="center">
                        <th>Minimal investment</th>
                        <td>
                            <form:input path="minInvestment" type="number" name="funds" min="1000" max="1000000000" step="100"
                                        value="${command.minInvestment}"/>
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
                            <form:input path="address.city" type="text" name="address.city" value="${command.address.city}"/>
                        </td>
                    </tr>
                    <tr align="center">
                        <th>Region</th>
                        <td>
                            <form:input path="address.region" type="text" name="address.region" value="${command.address.region}"/>
                        </td>
                    </tr>
                    <tr align="center">
                        <th>Idea</th>
                        <td>
                            <form:input path="businessPlan.idea" type="text" name="businessPlan.idea" value="${command.businessPlan.idea}"/>
                        </td>
                    </tr>
                    <tr align="center">
                        <th>Current State</th>
                        <td>
                            <form:input path="businessPlan.currentState" type="text" name="businessPlan.currentState" value="${command.businessPlan.currentState}"/>
                        </td>
                    </tr>
                    <tr align="center">
                        <th>Market</th>
                        <td>
                            <form:input path="businessPlan.market" type="text" name="businessPlan.market" value="${command.businessPlan.market}"/>
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
        </c:if>
    </article>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>