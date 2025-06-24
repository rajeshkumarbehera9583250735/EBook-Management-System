// ✅ Final Corrected UserDaoImpl.java - now includes role fetching logic

package com.daoImpl;

import com.dao.UserDao;
import com.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {

    private Connection con;

    public UserDaoImpl(Connection con) {
        this.con = con;
    }

    @Override
    public boolean userRegistration(User u) {
        boolean f = false;
        try {
            String insert = "INSERT INTO users(name, email, phoneNumber, password, role) VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPhoneNumber());
            ps.setString(4, u.getPassword());
            ps.setString(5, "user"); // default role
            int i = ps.executeUpdate();
            if (i == 1) f = true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
        }
        return f;
    }

    @Override
    public User loginUser(String email, String password) {
        User u = null;
        try {
            String login = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(login);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new User();
                u.setUserId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setPhoneNumber(rs.getString("phoneNumber"));
                u.setPassword(rs.getString("password"));
                u.setAddress(rs.getString("address"));
                u.setLandmark(rs.getString("landmark"));
                u.setCity(rs.getString("city"));
                u.setState(rs.getString("state"));
                u.setZipCode(rs.getString("zip"));
                u.setRole(rs.getString("role")); // ✅ Fetch the role here
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public boolean updateProfile(User u) {
        boolean f = false;
        try {
            String update = "UPDATE users SET name=?, email=?, phoneNumber=?, password=? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(update);
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPhoneNumber());
            ps.setString(4, u.getPassword());
            ps.setInt(5, u.getUserId());
            int i = ps.executeUpdate();
            if (i == 1) f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public User getUserByUserId(Integer id) {
        User u = null;
        try {
            String sql = "SELECT * FROM users WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new User();
                u.setUserId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setPhoneNumber(rs.getString("phoneNumber"));
                u.setPassword(rs.getString("password"));
                u.setAddress(rs.getString("address"));
                u.setLandmark(rs.getString("landmark"));
                u.setCity(rs.getString("city"));
                u.setState(rs.getString("state"));
                u.setZipCode(rs.getString("zip"));
                u.setRole(rs.getString("role")); // ✅ Include role
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public boolean updateUserOtherDetail(User u) {
        boolean f = false;
        try {
            String update = "UPDATE users SET address=?, landmark=?, city=?, state=?, zip=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(update);
            ps.setString(1, u.getAddress());
            ps.setString(2, u.getLandmark());
            ps.setString(3, u.getCity());
            ps.setString(4, u.getState());
            ps.setString(5, u.getZipCode());
            ps.setInt(6, u.getUserId());
            int i = ps.executeUpdate();
            if (i == 1) f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public boolean checkEmail(String email) {
        boolean exists = false;
        try {
            String query = "SELECT * FROM users WHERE email=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) exists = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }
}