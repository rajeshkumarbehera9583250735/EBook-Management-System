// Updated LoginServlet.java
package com.servlet;

import com.connection.DBConnection;
import com.dao.UserDao;
import com.daoImpl.UserDaoImpl;
import com.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            HttpSession session = request.getSession();
            UserDao userDao = new UserDaoImpl(DBConnection.getConnection());

            User u = userDao.loginUser(email, password);
            if (u != null) {
                session.setAttribute("userobj", u);
                if ("admin".equalsIgnoreCase(u.getRole())) {
                    response.sendRedirect("admin/Home.jsp");
                } else {
                    response.sendRedirect("index.jsp");
                }
            } else {
                session.setAttribute("failedMsg", "Invalid Username or Password");
                response.sendRedirect("login.jsp");
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
        return "Login Servlet";
    }
}