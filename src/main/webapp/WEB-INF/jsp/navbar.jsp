<header class="navbar navbar-expand navbar-dark flex-column flex-md-row bd-navbar">
    <div class="container">
        <div class="navbar-nav-scroll">
            <a class="nav-link" href="${pageContext.request.contextPath}/main">
                <div class="logo">
                    <img src="${pageContext.request.contextPath}/logotype.jpg">
                </div>
                <div class="brand">Startup</div>
            </a>
        </div>
        <div class="menu">

            <sec:authorize access="hasRole('ADMIN')">
                <c:set var="isAdmin" value="true"/>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <c:set var="user_id">
                    <sec:authentication property="principal.id"/>
                </c:set>
            </sec:authorize>

            <sec:authorize access="isAnonymous()">
                <a href="${pageContext.request.contextPath}/login"> Login | </a>
                <a href="${pageContext.request.contextPath}/registration/"> Registration </a>
            </sec:authorize>


            <sec:authorize access="isAuthenticated()">
                <a href="${pageContext.request.contextPath}/main"> Home | </a>
                <a href="${pageContext.request.contextPath}/user/personalAccount/${user_id}"> Account | </a>
                <a href="${pageContext.request.contextPath}/logout"> Logout </a>
            </sec:authorize>

        </div>
    </div>
</header>

<nav class="nav">
    <ul>
        <%--<li>--%>
        <%--<form action="${pageContext.request.contextPath}/search/projectsByIndustry/" method="get">--%>
        <%--<select path="industry" name="industry">--%>
        <%--<option value="">Industry</option>--%>
        <%--<c:forEach items="${industries}" var="industry">--%>
        <%--<option value="${industry}">${industry.label}</option>--%>
        <%--</c:forEach>--%>
        <%--</select>--%>
        <%--<br><button type="submit">Search projects by industry</button>--%>
        <%--</form>--%>
        <%--<br></li>--%>
        <sec:authorize access="isAuthenticated()">
            <br>
            <li><a href="${pageContext.request.contextPath}/main">Home</a><br></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/user/personalAccount/${user_id}">Account </a><br></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/startup/create">Add project</a><br></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/interest/create/">Add interest</a></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/logout">Logout</a><br></li>
            <br>
            <c:if test="${isAdmin}">
                <li><a href="${pageContext.request.contextPath}/user/users">All users</a><br></li>
                <br>
            </c:if>
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
            <li><a href="${pageContext.request.contextPath}/login">Login</a><br></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/registration/">Registration</a><br></li>
            <br>
            <li></li>
        </sec:authorize>
    </ul>
</nav>