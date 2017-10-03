<header class="navbar navbar-expand navbar-dark flex-column flex-md-row bd-navbar">
    <div class="container">
        <div class="navbar-nav-scroll">
            <a class="nav-link" href="/">
                <div class="logo">
                    <img src="${pageContext.request.contextPath}/logo.jpg">
                </div>
                <div class="brand">Startup</div>
            </a>
        </div>
        <div class="menu">
            <a  href="${pageContext.request.contextPath}/login" role="button">Login</a>
            <a  href="${pageContext.request.contextPath}/registration" role="button">Registration</a>
            <%--<li class="nav-item"><a href="${pageContext.request.contextPath}/startups">Show all startups</a></li>--%>
        </div>
    </div>
</header>