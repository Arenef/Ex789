package controller;

import data.ProductIO;
import data.UserIO;
import dto.Product;
import dto.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import util.CookieUtil;

import java.io.IOException;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/index.jsp";

        String action = request.getParameter("action");
        if (action == null)
            action = "join";

        if (action.equals("join")) {
            url = "/index.jsp";
        } else if (action.equals("download")) {
            String productCode = request.getParameter("productCode");

            url = "/" + productCode + "_download.jsp";
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/index.jsp";

        String action = request.getParameter("action");
        if (action == null)
            action = "join";

        if (action.equals("join")) {
            url = "/download.jsp";
        } else if (action.equals("checkUser")) {
            url = checkUser(request, response);
        } else if (action.equals("viewCookies")) {
            url = "/view_cookies.jsp";
        } else if (action.equals("deleteCookies")) {
            url = deleteCookies(request, response);
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    private String checkUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productCode = request.getParameter("productCode");
        HttpSession session = request.getSession();
        Product product = ProductIO.getProduct(productCode);
        session.setAttribute("product", product);

        if (product == null) {
            return "/index.jsp";
        }

        User user = (User) session.getAttribute("user");
        String url = "/register.jsp";

        if (user == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userEmail")) {
                        String email = cookie.getValue();
                        if (email != null && !email.trim().isEmpty()) {
                            UserIO userIO = new UserIO();
                            String path = getServletContext().getRealPath("/WEB-INF/EmailList.json");
                            user = userIO.getUserByEmail(email, path);

                            if (user != null) {
                                session.setAttribute("user", user);
                                url = "/" + product.getProductCode() + "_download.jsp";
                            }
                        }
                        break;
                    }
                }
            }
        } else {
            url = "/" + product.getProductCode() + "_download.jsp";
        }
        return url;
    }

    private String deleteCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            CookieUtil cookieUtil = new CookieUtil();
            for (Cookie cookie : cookies) {
                if (!cookie.getName().equals("JSESSIONID")) {
                    cookieUtil.deleteCookie(response, cookie.getName());
                }
            }
        }
        return "/delete_cookies.jsp";
    }
}
