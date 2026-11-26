package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import data.UserIO;
import dto.User;
import dto.Survey;

@WebServlet("/emailList")
public class EmailListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/emailList.jsp";

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "join"; // default action
        }

        if (action.equals("join")) {
            url = "/emailList.jsp";
        } else if (action.equals("add")) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter(("lastName"));
            String email = request.getParameter("email");
            String referralSource = request.getParameter("referral_source");
            String announcements = request.getParameter("announcements");
            boolean wantAnnouncements = "yes".equals(announcements);
            String offer = request.getParameter("offer");
            boolean wantOffer = "yes".equals(offer);
            String contactVia = request.getParameter("contactVia");

            User user = new User(email, firstName, lastName);
            Survey survey = new Survey(referralSource, wantAnnouncements, wantOffer, contactVia);

            UserIO userIO = new UserIO();
            String path = getServletContext().getRealPath("/WEB-INF/EmailList.json");
            userIO.add(user, path);

            HttpSession session = request.getSession();
            @SuppressWarnings("unchecked")
            ArrayList<User> users = (ArrayList<User>) session.getAttribute("users");
            if (users == null) {
                users = new ArrayList<>(userIO.getUsers(path));
            } else {
                users.add(user);
            }
            session.setAttribute("users", users);
            session.setAttribute("user", user);

            Date currentDate = new Date();
            request.setAttribute("currentDate", currentDate);
            request.setAttribute("user", user);
            request.setAttribute("survey", survey); // lưu vào body của request
            url = "/thanks.jsp";
        }

        getServletContext()
                .getRequestDispatcher(url) // sử dụng cái này để dữ liệu của request vẫn tồn tại
                .forward(request, response);
        // Nếu dùng response.sendRedirect(url) sẽ kết thúc request
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}