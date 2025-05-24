package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/qlsv"; // đổi nếu cần
    private static final String USER = "root";
    private static final String PASSWORD = "hoan123";

    // Hàm static để lấy kết nối
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("❌ Không thể kết nối đến MySQL: " + e.getMessage());
            return null;
        }
    }
}
