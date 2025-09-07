package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.MySQLConnection;
import model.Book;

public class BookDao {

    private Connection connection;

    public BookDao() {
        connection = MySQLConnection.getConnection(); // dùng class MySQLConnection
    }

    // Thêm sách
    public boolean addBook(Book book) {
        String query = "INSERT INTO Book (Title, Author, Price, ImagePath) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setDouble(3, book.getPrice());
            stmt.setString(4, book.getImagePath());

            int rowInserted = stmt.executeUpdate();
            return rowInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật sách
    public boolean updateBook(Book book) {
        String query = "UPDATE Book SET Title = ?, Author = ?, Price = ?, ImagePath = ? WHERE BookId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setDouble(3, book.getPrice());
            stmt.setString(4, book.getImagePath());
            stmt.setInt(5, book.getBookId());

            int rowsUpdate = stmt.executeUpdate();
            return rowsUpdate > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa sách
    public boolean deleteBook(int bookId) {
        String query = "DELETE FROM Book WHERE BookId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookId);
            int rowDeleted = stmt.executeUpdate();
            return rowDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm sách theo ID
    public Book findById(int bookId) {
        Book book = null;
        String query = "SELECT * FROM Book WHERE BookId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    book = new Book(
                        rs.getInt("BookId"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getDouble("Price"),
                        rs.getString("ImagePath")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    // Lấy tất cả sách
    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        String query = "SELECT * FROM Book";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Book book = new Book(
                    rs.getInt("BookId"),
                    rs.getString("Title"),
                    rs.getString("Author"),
                    rs.getDouble("Price"),
                    rs.getString("ImagePath")
                );
                bookList.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}
