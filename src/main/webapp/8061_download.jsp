<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <html>
        <c:import url="/header.html" />

        <body>
            <h1>Downloads</h1>
            <h2>
                <c:out value="${product.description}" />
            </h2>

            <table>
                <tr>
                    <th>Song title</th>
                    <th>Audio Format</th>
                </tr>
                <tr>
                    <td>You Are a Star</td>
                    <td><a href="/musicStore/sound/${product.productCode}/star.mp3">MP3</a></td>
                </tr>
                <tr>
                    <td>Don't Make No Difference</td>
                    <td><a href="/musicStore/sound/${product.productCode}/no_difference.mp3">MP3</a></td>
                </tr>
            </table>

            <p><a href="download?action=viewCookies">View all cookies</a></p>
            <p><a href="download?action=join">View list of albums</a></p>
        </body>

        </html>