<%@ page import="data.ProductIO, dto.Product, java.util.List" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8">
                <title>CD List</title>
                <link rel="stylesheet" href="styles/main.css" type="text/css">
            </head>

            <body>
                <h1>CD list</h1>

                <% List<Product> products = ProductIO.getProducts();
                    request.setAttribute("products", products);
                    %>

                    <table>
                        <tr>
                            <th>Description</th>
                            <th>Price</th>
                            <th></th>
                        </tr>
                        <c:forEach var="product" items="${products}">
                            <tr>
                                <td>${product.description}</td>
                                <td>$${product.price}</td>
                                <td>
                                    <form action="addToCart" method="post">
                                        <input type="hidden" name="action" value="add">
                                        <input type="hidden" name="productCode" value="${product.productCode}">
                                        <input type="hidden" name="description" value="${product.description}">
                                        <input type="hidden" name="price" value="${product.price}">
                                        <input type="submit" value="Add To Cart">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <form action="download" method="get">
                        <input type="submit" value="Go to Downloads (Album List)">
                    </form>
                    <p><a href="download?action=viewCookies">View all cookies</a></p>
            </body>

            </html>