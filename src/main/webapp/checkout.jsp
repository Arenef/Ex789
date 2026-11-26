<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: baokh
  Date: 11/19/2026
  Time: 5:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css">
</head>
<body>
<h1>Your invoice</h1>
<table>
    <tr>
        <th>Description</th>
        <th>Quantity</th>
        <th>Amount</th>
    </tr>
    <c:forEach var="lineProduct" items="${sessionScope.cart.getCart()}">
        <tr>
                <td><c:out value="${lineProduct.product.description}"/></td>
                <td><c:out value="${lineProduct.quantity}"/></td>
                <td>
                    $<fmt:formatNumber value="${lineProduct.calculateAmount()}" pattern="0.00"/>
                </td>
        </tr>
    </c:forEach>
    <tr>
        <th>Total</th>
        <td></td>
        <td>
            $<fmt:formatNumber value="${sessionScope.cart.checkout()}" pattern="0.00"/>
        </td>
    </tr>
</table>
<br>
<form action="loadProducts" method="get">
    <input type="submit" name="continueShopping" value="Continue Shopping">
</form>
</body>
</html>
