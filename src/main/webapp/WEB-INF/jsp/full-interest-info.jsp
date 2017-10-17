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
            <div class="date">${interest.lastChange}</div>
            <div class="name">Interest</div>
        </div>

        <div>${interest.name}</div>

        <div>
            <c:if test="${!(isOwner)}">
                <tr>
                    <td class="tb1" style="width:30%">
                        <a href="${pageContext.request.contextPath}/user/personalAccount/${interest.user.id}">
                            To interest's owner page</a>
                    </td>
                </tr>
            </c:if>
        </div>
        <div class="item-desc">
            <div class="item-desc-box">
                <div class="desc">${interest.description} <br></div>
                <div class="location">${interest.country.label} </div>
                <div>Industry: ${interest.industry.label} </div>
                <div>Funds: ${interest.budget}</div>
            </div>
        </div>
        <div class="timeline-cell-footer">
            <div class="counter">
                <c:if test="${isOwner || isAdmin}">
                    <a href="${pageContext.request.contextPath}/interest/${interest.id}/edit">Edit</a><br>
                    <a href="${pageContext.request.contextPath}/interest/${interest.user.id}/${interest.id}/delete">Delete</a>
                </c:if>
                <br>
                <br>
            </div>
        </div>
    </div>