<div class="timeline-item">
    <div class="timeline-item-header">
        <div class="img">
            <div class="avatar">
                <img src="${pageContext.request.contextPath}/${interest.user.personalPageFotoLink}"
                     style="width:auto;height:42px;">
            </div>
        </div>
        <div class="labels">
            <div class="date">${interest.lastChange}</div>
            <div class="name">Investment</div>
        </div>
    </div>
    <a href="${pageContext.request.contextPath}/interest/${interest.id}">${interest.name}</a>
    <br>

    <div class="item-desc">
        <div class="item-desc-box">
            <div class="desc">${interest.description} <br></div>
            <div class="location">${interest.country.label}</div>
            <div>Industry: ${interest.industry.label}</div>
            <div><a href="${pageContext.request.contextPath}/interest/${interest.id}">Learn more</a><br></div>
        </div>
    </div>

    <div class="timeline-cell-footer">
        <div class="counter">
            <c:if test="${isOwner || isAdmin}">
                <a href="${pageContext.request.contextPath}/interest/${interest.id}/edit">Edit</a><br>
                <a href="${pageContext.request.contextPath}/interest/${interest.user.id}/${interest.id}/delete">Delete</a>
            </c:if>
        </div>
    </div>
</div>
<br>
<br>