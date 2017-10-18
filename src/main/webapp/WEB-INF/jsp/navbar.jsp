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
            <li><a href="${pageContext.request.contextPath}/">Home</a><br></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/startup/create">Add project</a><br></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/interest/create/">Add interest</a></li>
            <br>
            <%--<li><a href="${pageContext.request.contextPath}/user/personalAccount/${user_id}">Account </a><br></li>--%>
            <%--<br>--%>
            <c:if test="${isOwner}">
                <li><a href="${pageContext.request.contextPath}/user/personalAccount/${userId}/edit">Edit
                    profile</a></li>
                <br>
                <li><a href="${pageContext.request.contextPath}/user/personalAccount/${user_id}/${userId}/delete">Delete
                    profile</a></li>
                <br>
            </c:if>
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