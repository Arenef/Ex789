package controller;

import dto.Cart;
import dto.LineProduct;
import dto.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sound.sampled.Line;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addToCart")
public class AddToCart extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/index.jsp";
        String action = request.getParameter("action");

        if (action == null) {
            action = "join";
        } else if (action.equals("add")) {
            String productCode = request.getParameter("productCode");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            HttpSession session = request.getSession();
            Product product = new Product(productCode, description, price);
            LineProduct lineProduct = new LineProduct(product, 1);
            Cart cart = (Cart) session.getAttribute("cart");

            if (cart == null) {
                cart = new Cart();
            }
            cart.addProduct(lineProduct);

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
