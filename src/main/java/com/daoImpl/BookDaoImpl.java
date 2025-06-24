package com.daoImpl;

import com.dao.BookDao;
import com.model.Books;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    private Connection con;

    public BookDaoImpl(Connection con) {
        this.con = con;
    }

    @Override
    public boolean insertBook(Books book) {
        boolean f = false;
        try {
            String insert = "INSERT INTO books(bName, author, price, bookCategory, status, photo, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getPrice());
            ps.setString(4, book.getBookCategory());
            ps.setString(5, book.getStatus());
            ps.setString(6, book.getPhotoName());
            ps.setString(7, book.getEmail());
            f = ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    private Books extractBookFromResultSet(ResultSet set) throws Exception {
        Books b = new Books();
        b.setBookId(set.getInt("id"));
        b.setBookName(set.getString("bName"));
        b.setAuthor(set.getString("author"));
        b.setBookCategory(set.getString("bookCategory"));
        b.setStatus(set.getString("status"));
        b.setPhotoName(set.getString("photo"));
        b.setPrice(set.getString("price"));
        b.setEmail(set.getString("email"));
        return b;
    }

    @Override
    public List<Books> getAllBooks() {
        List<Books> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM books";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                list.add(extractBookFromResultSet(set));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Books getBookById(Integer id) {
        Books book = null;
        try {
            String query = "SELECT * FROM books WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet set = ps.executeQuery();
            if (set.next()) {
                book = extractBookFromResultSet(set);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public boolean updateBook(Books book) {
        boolean f = false;
        try {
            String query = "UPDATE books SET bName=?, status=?, price=?, author=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getStatus());
            ps.setString(3, book.getPrice());
            ps.setString(4, book.getAuthor());
            ps.setInt(5, book.getBookId());
            f = ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public boolean deleteBook(Integer id) {
        boolean f = false;
        try {
            String query = "DELETE FROM books WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            f = ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    private List<Books> getBooksByCategory(String category, boolean all) {
        List<Books> list = new ArrayList<>();
        try {
            String query = all ?
                "SELECT * FROM books WHERE bookCategory = ? AND status = ? ORDER BY id DESC" :
                "SELECT * FROM books WHERE bookCategory = ? AND status = ? ORDER BY id DESC LIMIT 4";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, category);
            ps.setString(2, "Active");
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                list.add(extractBookFromResultSet(set));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Books> getNewBooks() {
        return getBooksByCategory("New", false);
    }

    @Override
    public List<Books> getOldBooks() {
        return getBooksByCategory("Old", false);
    }

    @Override
    public List<Books> getRecentBooks() {
        List<Books> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM books WHERE status = ? ORDER BY id DESC LIMIT 4";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "Active");
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                list.add(extractBookFromResultSet(set));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Books> getAllRecentBook() {
        return getBooksByCategory("", true);
    }

    @Override
    public List<Books> getAllNewBook() {
        return getBooksByCategory("New", true);
    }

    @Override
    public List<Books> getAllOldBook() {
        return getBooksByCategory("Old", true);
    }

    @Override
    public List<Books> getUserAllOldBooks(String email, String category) {
        List<Books> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM books WHERE email = ? AND bookCategory = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, category);
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                list.add(extractBookFromResultSet(set));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean deleteUseOldBook(String email, String category, Integer bookId) {
        boolean f = false;
        try {
            String query = "DELETE FROM books WHERE email = ? AND bookCategory = ? AND id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, category);
            ps.setInt(3, bookId);
            f = ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public List<Books> getBookBySearch(String keyword) {
        List<Books> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM books WHERE (bName ILIKE ? OR author ILIKE ? OR bookCategory ILIKE ?) AND status = ?";
            PreparedStatement ps = con.prepareStatement(query);
            String likePattern = "%" + keyword + "%";
            ps.setString(1, likePattern);
            ps.setString(2, likePattern);
            ps.setString(3, likePattern);
            ps.setString(4, "Active");
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                list.add(extractBookFromResultSet(set));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
