package application;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		UserDAO userDAO = new UserDAO();
		SubjectDAO subjectDAO = new SubjectDAO();
		User_Subject_DAO userSubjectDAO = new User_Subject_DAO();

		int choice;
		do {
			System.out.println("\n======= MENU CHÍNH =======");
			System.out.println("1. Quản lý người dùng");
			System.out.println("2. Quản lý môn học");
			System.out.println("3. Quản lý điểm môn học");
			System.out.println("0. Thoát");
			System.out.print("Chọn: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				userDAO.showMenu(); // Gọi menu người dùng
				break;

			case 2:
				subjectDAO.showMenu(); // Gọi menu môn học
				break;

			case 3:
				userSubjectDAO.showMenu(); // Gọi menu điểm môn học
				break;

			case 0:
				System.out.println("Thoát chương trình."); break;

			default:
				System.out.println("Lựa chọn không hợp lệ.");
			}
		} while (choice != 0);
	}
}
