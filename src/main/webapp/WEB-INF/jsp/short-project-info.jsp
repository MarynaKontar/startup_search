<div class="timeline-item">

    <div class="timeline-item-header">
        <div class="img">
            <div class="avatar">
                <img src="${pageContext.request.contextPath}/${project.user.personalPageFotoLink}"
                     style="width:auto;height:42px;">
            </div>
        </div>
        <div class="labels">
            <div class="date">${project.lastChange}</div>
            <div class="name">Startup</div>
        </div>
    </div>
    <a href="${pageContext.request.contextPath}/startup/${project.id}">${project.name}</a>
    <br>

    <div class=class="flex-container">
        <div class="item-img ">
            <div class="item-img-content in-cover">
                <img src="${pageContext.request.contextPath}/${project.photo}"
                     style="width:512px;height:auto;">
            </div>
        </div>
    </div>

    <div class="item-desc">
        <div class="item-desc-box">
            <div class="desc">${project.description} <br>
                <div class="location">${project.address.country.label}, ${project.address.city}</div>
                <div><a href="${pageContext.request.contextPath}/startup/${project.id}">Learn more</a><br></div>
            </div>
        </div>
    </div>

    <div class="timeline-cell-footer">
        <div class="counter">
            <c:if test="${isOwner || isAdmin}">
                <a href="${pageContext.request.contextPath}/startup/${project.id}/edit">Edit</a>
                <a href="${pageContext.request.contextPath}/startup/${project.user.id}/${project.id}/delete">Delete</a>
            </c:if>
        </div>
    </div>
</div>
<br>
<br>
<br>