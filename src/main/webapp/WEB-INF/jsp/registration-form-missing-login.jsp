<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Registration form</title>
    <%@include file="head.jsp" %>
</head>
<body>
<div class="flex-container" align="center">
    <%@include file="navbar.jsp" %>

    <article>
        <h3 align="center">Registration form</h3>
        <h3 align="center"  style="color: darkred">This login already exists</h3>
        <%@include file="registration-form-part.jsp" %>
    </article>
    <footer>Copyright &copy; javaEE group7</footer>
</div>
</body>
</html>