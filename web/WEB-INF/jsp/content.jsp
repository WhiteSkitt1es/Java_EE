<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 08.11.2023
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@ include file="header.jsp"%>
    <div>
        <span>Content. Русский</span>
        <p>Size: ${requestScope.flights.size()}</p>
        <p>Id 1: ${requestScope.flights.get(0).id}</p>
        <p>Id 2: ${requestScope.flights[1].id}</p>
        <p>Id 3: ${sessionScope.flightsMap[1]}</p>
        <p>JSession id: ${cookie["JSESSIONID"]}, unique identifier</p>
        <p>Header: ${header["Cookie"]}</p>
        <p>Parameter id: ${param.id}</p>
        <p>Parameter test: ${param.test}</p>
        <p>Empty list: ${not empty requestScope.flights}</p>
    </div>
    <%@ include file="footer.jsp"%>
</body>
</html>
