package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SubjectDAO {
	private Connection connection;

	public SubjectDAO() {
		try {
			String URL = "jdbc:ucanaccess://lib/QLSV (1).accdb";
			connection = DriverManager.getConnection(URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean addSubject(Subject subject) {
		String query = "INSERT INTO Subject (Subjectname, Credit, Heso1, Heso2, Heso3, Heso4, Heso5) VALUES(?,?,?,?,?,?,?)";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, subject.getsubjectName());
			stmt.setInt(2, subject.getcredit());
			stmt.setInt(3, subject.getheSo1());
			stmt.setInt(4, subject.getheSo2());
			stmt.setInt(5, subject.getheSo3());
			stmt.setInt(6, subject.getheSo4());
			stmt.setInt(7, subject.getheSo5());

			int rowInserted = stmt.executeUpdate();
			return rowInserted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateSubject(Subject subject) {
		String query = "UPDATE Subject SET Subjectname = ?, Credit = ?, Heso1 = ?, Heso2 = ?, Heso3 = ?, Heso4 = ?, Heso5 = ?  WHERE SubjectID = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, subject.getsubjectName());
			stmt.setInt(2, subject.getcredit());
			stmt.setInt(3, subject.getheSo1());
			stmt.setInt(4, subject.getheSo2());
			stmt.setInt(5, subject.getheSo3());
			stmt.setInt(6, subject.getheSo4());
			stmt.setInt(7, subject.getheSo5());

			int rowsUpdate = stmt.executeUpdate();
			return rowsUpdate > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteSubject(int subjectId) {
		String query = "DELETE FROM Subject WHERE SubjectID = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, subjectId);
			int rowDeleted = stmt.executeUpdate();
			return rowDeleted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public void showMenu() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("\n==== MENU QUẢN LÝ MÔN HỌC ====");
		System.out.println("1. Thêm môn học");
		System.out.println("2. Sửa môn học");
		System.out.println("3. Xoá môn học");
		System.out.print("Chọn: ");
		int choice = scanner.nextInt();
		scanner.nextLine();

		switch (choice) {
		case 1:
			System.out.print("Nhập tên môn học: ");
			String name = scanner.nextLine();
			System.out.print("Nhập số tín chỉ: ");
			int credit = scanner.nextInt();
			System.out.print("Nhập hệ số 1 đến 5 (cách nhau bởi khoảng trắng): ");
			int h1 = scanner.nextInt(), h2 = scanner.nextInt(), h3 = scanner.nextInt(), h4 = scanner.nextInt(),
					h5 = scanner.nextInt();

			Subject subject = new Subject(0, name, credit, h1, h2, h3, h4, h5);
			if (addSubject(subject)) {
				System.out.println("Thêm môn học thành công.");
			} else {
				System.out.println(" Thêm môn học thất bại.");
			}
			break;

		case 2:
			System.out.print("Nhập ID môn học cần sửa: ");
			int id = scanner.nextInt();
			scanner.nextLine();
			System.out.print("Nhập tên môn học mới: ");
			String newName = scanner.nextLine();
			System.out.print("Nhập số tín chỉ mới: ");
			int newCredit = scanner.nextInt();
			System.out.print("Nhập hệ số mới (1-5): ");
			int nh1 = scanner.nextInt(), nh2 = scanner.nextInt(), nh3 = scanner.nextInt(), nh4 = scanner.nextInt(),
					nh5 = scanner.nextInt();

			Subject newSubject = new Subject(id, newName, newCredit, nh1, nh2, nh3, nh4, nh5);
			if (updateSubject(newSubject)) {
				System.out.println("Cập nhật môn học thành công.");
			} else {
				System.out.println("Cập nhật môn học thất bại.");
			}
			break;

		case 3:
			System.out.print("Nhập ID môn học cần xoá: ");
			int deleteId = scanner.nextInt();
			if (deleteSubject(deleteId)) {
				System.out.println("Xoá môn học thành công.");
			} else {
				System.out.println("Xoá môn học thất bại.");
			}
			break;

		default:
			System.out.println("Lựa chọn không hợp lệ.");

		}
	}
}
