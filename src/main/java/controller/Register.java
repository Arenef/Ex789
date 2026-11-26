package controller;

import data.UserIO;
import dto.Product;
import dto.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/index.jsp";
        String action = request.getParameter("action");

        if (action == null)
            action = "join";

        if (action.equals("join")) {
            url = "/index.jsp";
        } else if (action.equals("register")) {
            HttpSession session = request.getSession();
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");

            User user = new User(email, firstName, lastName);
            session.setAttribute("user", user);

            Product product = (Product) session.getAttribute("product");
            if (product == null) {
                url = "/index.jsp";
            } else {
                UserIO userIO = new UserIO();
                ServletContext sc = getServletContext();
                String path = sc.getRealPath("/WEB-INF/EmailList.json");
                userIO.add(user, path);

                Cookie c = new Cookie("userEmail", email);
                c.setMaxAge(60 * 60 * 24 * 365 * 3);
                c.setPath("/");
                response.addCookie(c);

                url = "/" + product.getProductCode() + "_download.jsp";
            }
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

}
