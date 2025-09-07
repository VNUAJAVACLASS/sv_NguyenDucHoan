package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/qlbook"; // đổi nếu cần
    private static final String USER = "root";
    private static final String PASSWORD = "hoan123";

    // Hàm static để lấy kết nối
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy MySQL JDBC Driver: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.err.println("Không thể kết nối đến MySQL: " + e.getMessage());
            return null;
        }

    }
}
