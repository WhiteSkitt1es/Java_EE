<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 08.11.2023
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/registration" method="post">
      <label for="nameId">Name:
        <input type="text" name="name" id="nameId">
      </label><br>
      <label for="birthdayId">Birthday:
        <input type="date" name="birthday" id="birthdayId">
      </label><br>
      <label for="emailId">Email:
        <input type="text" name="email" id="emailId">
      </label><br>
      <label for="birthdayId">Password:
        <input type="password" name="password" id="passwordId">
      </label><br>
      <label for="role">Role
        <select name="role" id="role">
          <c:forEach var="role" items="${requestScope.roles}">
            <option name="role" value="${role}">${role}</option>
          </c:forEach>
        </select>
      </label><br>
      <c:forEach var="gender" items="${requestScope.genders}">
        <input type="radio" name="gender" value="${gender}">${gender}
        <br>
      </c:forEach>
      <button type="submit">Send</button>
        <c:if test="${not empty requestScope.errors}">
          <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
              <span>${error.message}</span>
            </c:forEach>
          </div>
        </c:if>
    </form>
</body>
</html>
