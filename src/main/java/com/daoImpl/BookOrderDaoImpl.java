package com.daoImpl;

import com.dao.BookOrderDao;
import com.model.BookOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BookOrderDaoImpl implements BookOrderDao {

    private Connection con;

    public BookOrderDaoImpl(Connection con) {
        this.con = con;
    }

    @Override
    public boolean saveOrder(List<BookOrder> bList) {
        boolean f = false;
        try {
            String insertOrder = "INSERT INTO book_order(order_id, user_name, email, address, book_name, author, price, payment, number, order_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(insertOrder);

            for (BookOrder b : bList) {
                // If order time is null, set current timestamp
                if (b.getOrderTime() == null) {
                    b.setOrderTime(new Timestamp(System.currentTimeMillis()));
                }

                ps.setString(1, b.getOrderId());
                ps.setString(2, b.getUserName());
                ps.setString(3, b.getEmail());
                ps.setString(4, b.getFullAddress());
                ps.setString(5, b.getBookName());
                ps.setString(6, b.getAuthorName());
                ps.setString(7, b.getPrice());
                ps.setString(8, b.getPaymentType());
                ps.setString(9, b.getPhoneNumber());
                ps.setTimestamp(10, b.getOrderTime());

                ps.addBatch();
            }

            ps.executeBatch();
            con.commit();
            con.setAutoCommit(true);
            f = true;

        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public List<BookOrder> getAllBookByUser(String email) {
        List<BookOrder> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM book_order WHERE email = ? ORDER BY order_id DESC";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ResultSet set = ps.executeQuery();

            while (set.next()) {
                BookOrder b = new BookOrder();
                b.setId(set.getInt("id"));
                b.setOrderId(set.getString("order_id"));
                b.setUserName(set.getString("user_name"));
                b.setEmail(set.getString("email"));
                b.setFullAddress(set.getString("address"));
                b.setBookName(set.getString("book_name"));
                b.setAuthorName(set.getString("author"));
                b.setPrice(set.getString("price"));
                b.setPaymentType(set.getString("payment"));
                b.setPhoneNumber(set.getString("number"));
                b.setOrderTime(set.getTimestamp("order_time"));
                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<BookOrder> getAllBookByAdmin() {
        List<BookOrder> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM book_order ORDER BY order_id DESC";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet set = ps.executeQuery();

            while (set.next()) {
                BookOrder b = new BookOrder();
                b.setId(set.getInt("id"));
                b.setOrderId(set.getString("order_id"));
                b.setUserName(set.getString("user_name"));
                b.setEmail(set.getString("email"));
                b.setFullAddress(set.getString("address"));
                b.setBookName(set.getString("book_name"));
                b.setAuthorName(set.getString("author"));
                b.setPrice(set.getString("price"));
                b.setPaymentType(set.getString("payment"));
                b.setPhoneNumber(set.getString("number"));
                b.setOrderTime(set.getTimestamp("order_time"));
                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public BookOrder getBookByOrderId(String orderId) {
        BookOrder b = null;
        try {
            String query = "SELECT * FROM book_order WHERE order_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, orderId);
            ResultSet set = ps.executeQuery();

            if (set.next()) {
                b = new BookOrder();
                b.setId(set.getInt("id"));
                b.setOrderId(set.getString("order_id"));
                b.setUserName(set.getString("user_name"));
                b.setEmail(set.getString("email"));
                b.setFullAddress(set.getString("address"));
                b.setBookName(set.getString("book_name"));
                b.setAuthorName(set.getString("author"));
                b.setPrice(set.getString("price"));
                b.setPaymentType(set.getString("payment"));
                b.setPhoneNumber(set.getString("number"));
                b.setOrderTime(set.getTimestamp("order_time"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
}
