package com.servlet;

import com.connection.DBConnection;
import com.dao.CartDao;
import com.daoImpl.CartDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/remove_book")
public class RemoveBookServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Integer bid = Integer.parseInt(request.getParameter("bid"));
            Integer uid = Integer.parseInt(request.getParameter("uid"));
            Integer id = Integer.parseInt(request.getParameter("id")); // Replacing cid

            System.out.println("Remove request params -> bid: " + bid + ", uid: " + uid + ", id: " + id);

            CartDao cartDao = new CartDaoImpl(DBConnection.getConnection());
            boolean success = cartDao.deleteBook(bid, uid, id);

            HttpSession session = request.getSession();

            if (success) {
                session.setAttribute("succMsg", "Book removed from cart successfully.");
            } else {
                session.setAttribute("failedMsg", "Failed to remove book from cart.");
            }

            response.sendRedirect("checkout.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("failedMsg", "Something went wrong!");
            response.sendRedirect("checkout.jsp");
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
        return "Handles removal of a book from the cart";
    }
}
