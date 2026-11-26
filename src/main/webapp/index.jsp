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

            <table>
                <tr>
                    <th>Description</th>
                    <th>Price</th>
                    <th></th>
                </tr>
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td>
                            <c:out value="${product.description}" />
                        </td>
                        <td>$
                            <c:out value="${product.price}" />
                        </td>
                        <td>
                            <form action="addToCart" method="post">
                                <input type="hidden" name="action" value="add">
                                <input type="hidden" name="productCode" value="<c:out value='${product.productCode}'/>">
                                <input type="hidden" name="description" value="<c:out value='${product.description}'/>">
                                <input type="hidden" name="price" value="<c:out value='${product.price}'/>">
                                <input type="submit" value="Add To Cart">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <form action="download" method="get">
                <input type="submit" value="Go to Album List">
            </form>
            <p><a href="download?action=viewCookies">View all cookies</a></p>
            <p><a href="emailList.jsp">Survey</a></p>
        </body>

        </html>