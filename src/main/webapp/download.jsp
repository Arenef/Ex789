<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%-- Created by IntelliJ IDEA. User: baokh Date: 11/22/2026 Time: 4:34 PM To change this template use File |
        Settings | File Templates. --%>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <html>
            <c:import url="/header.html" />

            <body>
                <p>User Email: ${cookie.userEmail.value}</p>
                <h1> List of albums</h1>
                <a href="download?action=checkUser&amp;productCode=8061" target="_blank">
                    86 (the band) - True Life Songs and Pictures
                </a><br>
                <a href="download?action=checkUser&amp;productCode=pf01" target="_blank">
                    Paddlefoot - the first CD
                </a><br>
                <a href="download?action=checkUser&amp;productCode=pf02" target="_blank">
                    Paddlefoot - The second CD
                </a><br>
                <a href="download?action=checkUser&amp;productCode=jr01" target="_blank">
                    Joe Rut - Genuine Wood Grained Finish
                </a><br>
                <form action="" method="post">
                    <input type="submit" value="Return">
                </form>
            </body>

            </html>