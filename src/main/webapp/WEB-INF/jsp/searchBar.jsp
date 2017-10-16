<div class="row">
    <div class="column"></div>
    <div class="column" align="right">
        <%--<th>Search</th>--%>
        <br>
        <form method="get" action="${pageContext.request.contextPath}/">
            <th>Projects by industry</th>
            <select name="projectIndustry">
                <option value="">Industry</option>
                <c:forEach items="${industries}" var="industry">
                    <option value="${industry}">${industry.label}</option>
                </c:forEach>
            </select>
            <br>
            <th>Projects by country</th>
            <select name="projectCountry">
                <option value="">Country</option>
                <c:forEach items="${countries}" var="country">
                    <option value="${country}">${country.label}</option>
                </c:forEach>
            </select>
            <br>
            <th>Interests by industry</th>
            <select name="interestIndustry">
                <option value="">Industry</option>
                <c:forEach items="${industries}" var="industry">
                    <option value="${industry}">${industry.label}</option>
                </c:forEach>
            </select>
            <br>
            <th>Interests by country</th>
            <select name="interestCountry">
                <option value="">Country</option>
                <c:forEach items="${countries}" var="country">
                    <option value="${country}">${country.label}</option>
                </c:forEach>
            </select>
            <br>
            <input type="submit" value="Search">
        </form>
    </div>
</div>