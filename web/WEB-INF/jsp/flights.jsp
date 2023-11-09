<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 08.11.2023
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Список перелетов</h1>
    <ul>
        <c:forEach var="flight" items="${requestScope.flights}">
            <li>
                <a href="${pageContext.request.contextPath}/tickets?flightId=${flight.id}">${flight.description}</a>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
