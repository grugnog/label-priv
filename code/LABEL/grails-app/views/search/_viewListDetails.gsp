<div id="${id}" class="col-sm-12">
    <div class="col-sm-3">
        <p><strong><g:message code="${code}" /></strong></p>
    </div>
    <div class="col-sm-9">
        <div class="controls">
            <g:if test="${valueList}">
                <g:if test="${valueList.size() > 1}">
                <ul>
                    <g:each in="${valueList}" var="itr">
                        <li>${itr}</li>
                    </g:each>
                </ul>
                </g:if>
                <g:else>
                    <p>${valueList?.first() ?: valueList}</p>
                </g:else>
            </g:if>
            <g:else>
                <g:message code="no.details" />
            </g:else>
        </div>
    </div>
</div>