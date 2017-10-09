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
            <a href="${pageContext.request.contextPath}/user/personalAccount//${user_id}"> Account | </a>
            <a href="${pageContext.request.contextPath}/logout"> Logout </a>
            </sec:authorize>

        </div>
    </div>
</header>