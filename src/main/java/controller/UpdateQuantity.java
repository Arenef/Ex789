package controller;

import dto.Cart;
import dto.LineProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/updateQuantity")
public class UpdateQuantity extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/index.jsp";

        String action = request.getParameter("action");
        if (action == null)
            action = "join";
        if (action.equals("join")) {
            url = "/index.jsp";
        } else if (action.equals("update")) {
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String productCode = request.getParameter("productCode");

            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");

            if (cart != null) {
                cart.updateQuantity(productCode, quantity);
            }

            session.setAttribute("cart", cart);
            url = "/cart.jsp";
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
