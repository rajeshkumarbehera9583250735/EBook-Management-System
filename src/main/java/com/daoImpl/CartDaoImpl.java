package com.daoImpl;

import com.connection.DBConnection;
import com.dao.CartDao;
import com.model.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {

    private Connection conn;

    public CartDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean addCart(Cart cart) {
        boolean f = false;
        try {
            String sql = "INSERT INTO cart (bid, uid, bookname, author, price, totalprice) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cart.getBid());
            ps.setInt(2, cart.getUid());
            ps.setString(3, cart.getBookName());
            ps.setString(4, cart.getAuthor());
            ps.setDouble(5, cart.getPrice());
            ps.setDouble(6, cart.getTotalPrice());

            int i = ps.executeUpdate();
            f = (i == 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public List<Cart> getBookByUserId(Integer id) {
        List<Cart> list = new ArrayList<>();
        Cart c;
        double totalPrice = 0;

        try {
            String sql = "SELECT * FROM cart WHERE uid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                c = new Cart();
                c.setId(rs.getInt("id")); // Using 'id' instead of 'cid'
                c.setBid(rs.getInt("bid"));
                c.setUid(rs.getInt("uid"));
                c.setBookName(rs.getString("bookname"));
                c.setAuthor(rs.getString("author"));
                c.setPrice(rs.getDouble("price"));
                totalPrice += rs.getDouble("price");
                c.setTotalPrice(totalPrice);
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean deleteBook(Integer bid, Integer uid, Integer id) {
        boolean f = false;
        try {
            String sql = "DELETE FROM cart WHERE bid = ? AND uid = ? AND id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bid);
            ps.setInt(2, uid);
            ps.setInt(3, id);

            int i = ps.executeUpdate();
            f = (i == 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }
}
