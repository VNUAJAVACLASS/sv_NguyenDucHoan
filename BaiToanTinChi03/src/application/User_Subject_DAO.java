package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class User_Subject_DAO {
	private Connection connection;

	public User_Subject_DAO() {
		try {
			String URL = "jdbc:ucanaccess://lib/QLSV (1).accdb";
			connection = DriverManager.getConnection(URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean addUserSubject(User_Subject userSubject) {
		String query = "INSERT INTO User_Subject (UserID, SubjectID,DiemMH1,DiemMH2,DiemMH3,DiemMH4,DiemMH5) VALUES(?,?,?,?,?,?,?)";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, userSubject.getuserID());
			stmt.setInt(2, userSubject.getsubjectID());
			stmt.setFloat(3, userSubject.getDiemHe1());
			stmt.setFloat(4, userSubject.getDiemHe2());
			stmt.setFloat(5, userSubject.getDiemHe3());
			stmt.setFloat(6, userSubject.getDiemHe4());
			stmt.setFloat(7, userSubject.getDiemHe5());

			int rowInserted = stmt.executeUpdate();
			return rowInserted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateUserSubject(User_Subject userSubject) {
		String query = "UPDATE User_Subject SET UserID =?,SubjectID=?,DiemMH1=?,DiemMH2=?,DiemMH3=?,DiemMH4=?,DiemMH5=? WHERE UserSubject_ID=?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, userSubject.getuserID());
			stmt.setInt(2, userSubject.getsubjectID());
			stmt.setFloat(3, userSubject.getDiemHe1());
			stmt.setFloat(4, userSubject.getDiemHe2());
			stmt.setFloat(5, userSubject.getDiemHe3());
			stmt.setFloat(6, userSubject.getDiemHe4());
			stmt.setFloat(7, userSubject.getDiemHe5());
			stmt.setInt(8, userSubject.getUserSubjectID());

			int rowsUpdate = stmt.executeUpdate();
			return rowsUpdate > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteUserSubject(int userSubjectId) {
		String query = "DELETE FROM User_Subject WHERE UserSubject_ID =?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, userSubjectId);
			int rowDeleted = stmt.executeUpdate();
			return rowDeleted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void showMenu() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("\n==== MENU QUẢN LÝ ĐIỂM MÔN HỌC ====");
		System.out.println("1. Nhập điểm môn học cho sinh viên");
		System.out.println("2. Sửa điểm môn học");
		System.out.println("3. Xoá điểm môn học");

		System.out.print("Chọn: ");
		int choice = scanner.nextInt();

		switch (choice) {
		case 1:
			System.out.print("Nhập mã sinh viên (UserID): ");
			int userID = scanner.nextInt();
			System.out.print("Nhập mã môn học (SubjectID): ");
			int subjectID = scanner.nextInt();
			System.out.print("Nhập điểm lần 1: ");
			float d1 = scanner.nextFloat();
			System.out.print("Nhập điểm lần 2: ");
			float d2 = scanner.nextFloat();
			System.out.print("Nhập điểm giữa kỳ: ");
			float d3 = scanner.nextFloat();
			System.out.print("Nhập điểm cuối kỳ: ");
			float d4 = scanner.nextFloat();
			System.out.print("Nhập điểm tổng kết: ");
			float d5 = scanner.nextFloat();

			User_Subject us = new User_Subject(0, userID, subjectID, d1, d2, d3, d4, d5);
			if (addUserSubject(us)) {
				System.out.println("✔ Nhập điểm thành công.");
			} else {
				System.out.println("✘ Nhập điểm thất bại.");
			}
			break;

		case 2:
			System.out.print("Nhập mã điểm cần sửa (UserSubject_ID): ");
			int usid = scanner.nextInt();
			System.out.print("Nhập lại UserID: ");
			int newUserId = scanner.nextInt();
			System.out.print("Nhập lại SubjectID: ");
			int newSubjectId = scanner.nextInt();
			System.out.print("Nhập điểm lần 1 mới: ");
			float dd1 = scanner.nextFloat();
			System.out.print("Nhập điểm lần 2 mới: ");
			float dd2 = scanner.nextFloat();
			System.out.print("Nhập điểm giữa kỳ mới: ");
			float dd3 = scanner.nextFloat();
			System.out.print("Nhập điểm cuối kỳ mới: ");
			float dd4 = scanner.nextFloat();
			System.out.print("Nhập điểm tổng kết mới: ");
			float dd5 = scanner.nextFloat();

			User_Subject updateUS = new User_Subject(usid, newUserId, newSubjectId, dd1, dd2, dd3, dd4, dd5);
			if (updateUserSubject(updateUS)) {
				System.out.println("Cập nhật điểm thành công.");
			} else {
				System.out.println("Cập nhật điểm thất bại.");
			}
			break;

		case 3:
			System.out.print("Nhập mã điểm cần xoá (UserSubject_ID): ");
			int deleteId = scanner.nextInt();
			if (deleteUserSubject(deleteId)) {
				System.out.println("Xoá điểm thành công.");
			} else {
				System.out.println("Xoá điểm thất bại.");
			}
			break;

		default:
			System.out.println("Lựa chọn không hợp lệ.");

		}
	}

}