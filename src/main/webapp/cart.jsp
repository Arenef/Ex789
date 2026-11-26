<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: baokh
  Date: 11/15/2026
  Time: 4:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your cart</title>
    <link rel = "stylesheet" href = "styles/main.css" type = "text/css">
</head>
<body>
<h1> Your cart</h1>
<table>
    <tr>
        <th>Quantity</th>
        <th>Description</th>
        <th>Price</th>
        <th>Amount</th>
        <th></th>
    </tr>
    <c:forEach var="lineProduct" items="${sessionScope.cart.getCart()}">
        <tr>
            <td>
                <form action="updateQuantity" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="productCode" value="${lineProduct.product.productCode}">
                    <input type="text" name="quantity" value="${lineProduct.quantity}" required style="width: 35px">
                    <input type="submit" name="update" value="Update">
                </form>
            </td>
            <td>${lineProduct.product.description}</td>
            <td>$${lineProduct.product.price}</td>
            <td>
                $<fmt:formatNumber value="${lineProduct.calculateAmount()}" pattern="0.00"/>
            </td>
            <td>
                <a href="<c:url value='removeProduct'>
                            <c:param name='action' value='remove'/>
                            <c:param name='productCode' value='${lineProduct.product.productCode}'/>
                         </c:url>">Remove</a>
            </td>
        </tr>
    </c:forEach>


</table>
<p><b>To change the quantity </b>, enter the new quantity and click on the Update button</p>
<form action="index.jsp" method="get">
    <input type="submit" name="continueShopping" value="Continue Shopping">
</form>
<form action="checkoutCart" method="post">
    <input type="hidden" name="action" value="checkout">
    <input type="submit" name="checkOut" value="Checkout">
</form>
</body>
</html>