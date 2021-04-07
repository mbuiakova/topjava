<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/topjava.common.js" defer></script>
<script type="text/javascript" src="resources/js/topjava.meals.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h3><spring:message code="meal.title"/></h3>

    <form method="get" action="meals/filter" id="filterForm">
        <dl>
            <dt><spring:message code="meal.startDate"/>:</dt>
            <dd><input type="date" id="filterStartDate" name="startDate" value="${param.startDate}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="meal.endDate"/>:</dt>
            <dd><input type="date" id="filterEndDate" name="endDate" value="${param.endDate}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="meal.startTime"/>:</dt>
            <dd><input type="time" id="filterStartTime" name="startTime" value="${param.startTime}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="meal.endTime"/>:</dt>
            <dd><input type="time" id="filterEndTime" name="endTime" value="${param.endTime}"></dd>
        </dl>
        <button type="submit" class="btn btn-primary"><spring:message code="meal.filter"/></button>
        <button type="button" class="btn btn-secondary" onclick="resetFilter()"><spring:message code="meal.resetFilter"/></button>
    </form>


    <hr />
<%--    <a href="meals/create"><spring:message code="meal.add"/></a>--%>
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalAddMeal" onclick="$('#modalAddMeal').modal('show')">
        <spring:message code="meal.add"/>
    </button>

    <div class="modal fade" id="modalAddMeal" tabindex="-1" role="dialog" aria-labelledby="addMealLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addMealLabel">Add meal</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="mealData">
                        <label for="dateTime" class="form-label">Date/Time</label>
                        <input type="datetime-local" class="form-control" id="dateTime">
                        <label for="description" class="form-label">Description</label>
                        <input class="form-control" id="description">
                        <label for="calories" class="form-label">Calories</label>
                        <input type="number" class="form-control" id="calories">
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="$('#modalAddMeal').modal('hide')">Close</button>
                    <button type="button" class="btn btn-primary" onclick="saveMeal()">Save changes</button>
                </div>
            </div>
        </div>
    </div>
    <br/>

    <table class="table table-striped" id="mealdatatable">
        <thead>
        <tr>
            <th><spring:message code="meal.dateTime"/></th>
            <th><spring:message code="meal.description"/></th>
            <th><spring:message code="meal.calories"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealTo"/>
            <tr data-mealExcess="${meal.excess}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals/update?id=${meal.id}"><span class="fa fa-pencil"></span></a></td>
<%--                <td><a href="meals/delete?id=${meal.id}"><spring:message code="common.delete"/></a></td>--%>
                <td><a class="delete"><span class="fa fa-remove" onclick="deleteMeal(${meal.id})"></span></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>