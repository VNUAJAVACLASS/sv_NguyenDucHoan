package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDAO {
	private MySQLConnection mysqlconnection;
	private Connection connection;

	public UserDAO() {
		connection = mysqlconnection.getConnection();
	}

	
	//Lay tat ca danh sach nguoi dung tu co so du lieu
	public List<User> getAllUser() {
		List<User> userList = new ArrayList<>();
		
        if (connection == null) {
            System.err.println("Không có kết nối cơ sở dữ liệu.");
            return userList;
        }
        
		String query = "SELECT* FROM User";

		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {

				int userID = rs.getInt("UserID");
				String fullName = rs.getString("Fullname");
				boolean gender = rs.getBoolean("Gender");
				String address = rs.getString("Address");
				String Password = rs.getString("Password");
				String userType = rs.getString("Type");

				User user = new User(userID, fullName, gender, address, Password, userType);
				userList.add(user);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return userList;
	}

	//Tra ve 1 nguoi dung khi nhap ma nguoi dung
	public User getUserById(int userId) {
		User user = null;
		String query = "SELECT * FROM User WHERE UserID = ?";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String fullName = rs.getString("Fullname");
				boolean gender = rs.getBoolean("Gender");
				String address = rs.getString("Address");
				String Password = rs.getString("Password");
				String userType = rs.getString("Type");

				user = new User(userId, fullName, gender, address, Password, userType);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return user;
	}

	//them nguoi dung
	public Boolean addUser(User user) {
		String query = "INSERT INTO users (UserID,Fullname,Gender,Address,Password,Type) VALUES (?,?,?,?,?,?)";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, user.getUserID());
			stmt.setString(2, user.getfullName());
			stmt.setBoolean(3, user.getgender());
			stmt.setString(4, user.getAddress());
			stmt.setString(5, user.getPassWord());
			stmt.setString(6, user.getUsertype());

			int rowsIntserted = stmt.executeUpdate();
			return rowsIntserted > 0;

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return false;
	}

	//Sua nguoi dung
	public Boolean updateUser(User user) {
		String query = "UPDATE users SET Fullname = ?, Gender = ?, Address = ?, Password = ?, Type = ? WHERE UserID = ?)";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, user.getUserID());
			stmt.setString(2, user.getfullName());
			stmt.setBoolean(3, user.getgender());
			stmt.setString(4, user.getAddress());
			stmt.setString(5, user.getPassWord());
			stmt.setString(6, user.getUsertype());

			int rowsIntserted = stmt.executeUpdate();
			return rowsIntserted > 0;

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return false;
	}

	//Xoa nguoi dung
	public Boolean deleteUser(int UserID) {
		String query = "DELETE users UserID = ?)";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, UserID);

			int rowsDeleted = stmt.executeUpdate();
			return rowsDeleted > 0;

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return false;
	}

	public void showMenu() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("\n====== MENU ======");
		System.out.println("1. Thêm người dùng");
		System.out.println("2. Sửa người dùng");
		System.out.println("3. Xoá người dùng");
		System.out.println("4. Hiển thị tất cả người dùng");
		System.out.print("Chọn: ");
		int choice = scanner.nextInt();
		scanner.nextLine(); // Đọc ký tự newline

		switch (choice) {
		case 1:
			System.out.println("Nhap id:");
			int id = scanner.nextInt(); scanner.nextLine();
			System.out.println("Nhập họ tên:");
			String fullName = scanner.nextLine();

			System.out.println("Nhập giới tính (true = nam, false = nữ):");
			boolean gender = scanner.nextBoolean();
			scanner.nextLine();

			System.out.println("Nhập địa chỉ:");
			String address = scanner.nextLine();

			System.out.println("Nhập mật khẩu:");
			String password = scanner.nextLine();

			System.out.println("Nhập loại người dùng:");
			String userType = scanner.nextLine();

			User newUser = new User(id, fullName, gender, address, password, userType);
			if (addUser(newUser)) {
				System.out.println("Thêm người dùng thành công.");
			} else {
				System.out.println("Thêm người dùng thất bại.");
			}
			break;

		case 2:
			System.out.print("Nhập ID người dùng cần sửa: ");
			int idUpdate = scanner.nextInt();
			scanner.nextLine();

			User userToUpdate = getUserById(idUpdate);
			if (userToUpdate == null) {
				System.out.println("✘ Không tìm thấy người dùng.");
				break;
			}

			System.out.println("Nhập họ tên mới:");
			userToUpdate.setfullName(scanner.nextLine());

			System.out.println("Nhập giới tính mới (true = nam, false = nữ):");
			userToUpdate.setgender(scanner.nextBoolean());
			scanner.nextLine();

			System.out.println("Nhập địa chỉ mới:");
			userToUpdate.setAddress(scanner.nextLine());

			System.out.println("Nhập mật khẩu mới:");
			userToUpdate.setPassWord(scanner.nextLine());

			System.out.println("Nhập loại người dùng mới:");
			userToUpdate.setUsertype(scanner.nextLine());

			if (updateUser(userToUpdate)) {
				System.out.println("Cập nhật người dùng thành công.");
			} else {
				System.out.println("Cập nhật người dùng thất bại.");
			}
			break;

		case 3:
			System.out.print("Nhập ID người dùng cần xoá: ");
			int idDelete = scanner.nextInt();
			scanner.nextLine();

			if (deleteUser(idDelete)) {
				System.out.println("Xoá người dùng thành công.");
			} else {
				System.out.println("Xoá người dùng thất bại.");
			}
			break;

		case 4:
			List<User> users = getAllUser();
			System.out.println("\nDanh sách người dùng:");
			for (User user : users) {
				System.out.println(user.toString());
			}
			break;

		default:
			System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");

		}
	}

}