<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: baokh
  Date: 11/22/2026
  Time: 7:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:import url="/header.html"/>
<body>
<h1>Download Registration</h1>
<p>To register for our downloads, enter your name and email address below. Then, click on the Submit button</p>
<form action="register" method="post">
    <input type="hidden" name="action" value="register">
    <label>Email:</label>
    <input type="text" name="email">
    <br>
    <label>First Name: </label>
    <input type="text" name="firstName">
    <br>
    <label>Last Name: </label>
    <input type="text" name="lastName">
    <br>
    <input type="submit" name="register" value="Register">
</form>
</body>
</html>
