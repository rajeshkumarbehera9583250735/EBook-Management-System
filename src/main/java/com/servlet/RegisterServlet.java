package com.servlet;

import com.connection.DBConnection;
import com.dao.UserDao;
import com.daoImpl.UserDaoImpl;
import com.model.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String number = request.getParameter("number");
            String password = request.getParameter("password");
            String checkBox = request.getParameter("checkBox");

            User u = new User();
            u.setEmail(email);
            u.setPassword(password);
            u.setName(name);
            u.setPhoneNumber(number);

            HttpSession session = request.getSession();

            if (checkBox != null) {
                UserDao userDao = new UserDaoImpl(DBConnection.getConnection());

                boolean f2 = userDao.checkEmail(email); // Check if email already exists

                if (!f2) { // If email does NOT exist, proceed with registration
                    boolean f = userDao.userRegistration(u);
                    if (f) {
                        session.setAttribute("succMsg", "Registration Successfully");
                        response.sendRedirect("register.jsp");
                    } else {
                        session.setAttribute("failedMsg", "Something went wrong on server");
                        response.sendRedirect("register.jsp");
                    }
                } else {
                    session.setAttribute("failedMsg", "User Already Exist Try Another Email");
                    response.sendRedirect("register.jsp");
                }

            } else {
                session.setAttribute("failedMsg", "Please check term and condition");
                response.sendRedirect("register.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles user registration";
    }
}
