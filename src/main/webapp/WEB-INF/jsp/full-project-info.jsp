<div class="timeline-item">
    <div class="timeline-item-header">
        <c:if test="${!(isOwner)}">
            <div class="img">
                <div class="avatar">
                    <img src="${pageContext.request.contextPath}/${interest.user.personalPageFotoLink}"
                         style="width:auto;height:42px;">
                </div>
            </div>
        </c:if>
        <div class="labels">
            <div class="date">${project.lastChange}</div>
            <div class="name">Startup</div>
        </div>
    </div>

    <div>${project.name}</div>

    <div>
        <c:if test="${!(isOwner)}">
            <tr>
                <td class="tb1" style="width:30%">
                    <a href="${pageContext.request.contextPath}/user/personalAccount/${project.user.id}">
                        To startup's owner page</a>
                </td>
            </tr>
        </c:if>
    </div>
    <br>
    <div class=class="flex-container">
        <div class="item-img ">
            <div class="item-img-content in-cover">
                <img src="${pageContext.request.contextPath}/${project.user.profileFotoLink}"
                     style="width:512px;height:auto;">
            </div>
        </div>
    </div>

    <div class="item-desc">
        <div class="item-desc-box">
            <div class="desc">${project.description}</div>
            <br>
            <div class="location">${project.address.country.label}, ${project.address.city} ${project.address.region} </div>
            <br>
            <div>Total: ${project.funds}</div>
            <br>
            <div>Minimum Investment: ${project.minInvestment}</div>
            <br>
            <div>Industry: ${project.industry.label}</div>
            <br>
            <div>Idea: ${project.businessPlan.idea}</div>
            <br>
            <div>Current state: ${project.businessPlan.currentState}</div>
            <br>
            <div>Market: ${project.businessPlan.market}</div>
            <br>
        </div>
    </div>

    <div class="timeline-cell-footer">
        <div class="counter">
            <c:if test="${isOwner || isAdmin}">
                <a href="${pageContext.request.contextPath}/startup/${project.id}/edit">Edit</a>
                <a href="${pageContext.request.contextPath}/startup/${project.user.id}/${project.id}/delete">Delete</a>
            </c:if>
            <br>
            <br>
        </div>
    </div>
</div>