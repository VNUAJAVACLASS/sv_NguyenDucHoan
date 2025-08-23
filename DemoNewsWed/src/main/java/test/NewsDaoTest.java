
package test;

import DAO.NewsDao;
import model.News;

import java.util.List;
import java.util.Scanner;

public class NewsDaoTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NewsDao newsDao = null;

        try {
            newsDao = new NewsDao();
            System.out.println("Kết nối cơ sở dữ liệu thành công!");

            int choice;
            do {
                System.out.println("\n=== Quản lý Tin tức ===");
                System.out.println("1. Thêm tin tức mới");
                System.out.println("2. Xem danh sách tin tức");
                System.out.println("3. Tìm tin tức theo ID");
                System.out.println("4. Cập nhật tin tức");
                System.out.println("5. Xóa tin tức");
                System.out.println("0. Thoát");
                System.out.print("Nhập lựa chọn của bạn (0-5): ");

                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập số từ 0 đến 5.");
                    choice = -1;
                    continue;
                }

                switch (choice) {
                    case 1:
                        System.out.println("\n=== Thêm tin tức mới ===");
                        System.out.print("Nhập tiêu đề: ");
                        String title = scanner.nextLine();
                        System.out.print("Nhập nội dung: ");
                        String content = scanner.nextLine();

                        if (title.trim().isEmpty() || content.trim().isEmpty()) {
                            System.out.println("Tiêu đề và nội dung không được để trống!");
                        } else {
                            News newNews = new News(title, content);
                            boolean added = newsDao.addNews(newNews);
                            System.out.println("Thêm tin tức: " + (added ? "Thành công" : "Thất bại"));
                        }
                        break;

                    case 2:
                        System.out.println("\n=== Danh sách tin tức ===");
                        List<News> newsList = newsDao.getAllNews();
                        if (newsList.isEmpty()) {
                            System.out.println("Không có tin tức nào trong cơ sở dữ liệu.");
                        } else {
                            for (News news : newsList) {
                                System.out.println("ID: " + news.getId() + ", Title: " + news.getTitle() + ", Content: " + news.getContent());
                            }
                        }
                        break;

                    case 3:
                        System.out.println("\n=== Tìm tin tức theo ID ===");
                        System.out.print("Nhập ID tin tức: ");
                        try {
                            int id = Integer.parseInt(scanner.nextLine());
                            News foundNews = newsDao.findById(id);
                            if (foundNews != null) {
                                System.out.println("Tìm thấy tin tức: ID: " + foundNews.getId() + ", Title: " + foundNews.getTitle() + ", Content: " + foundNews.getContent());
                            } else {
                                System.out.println("Không tìm thấy tin tức với ID: " + id);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("ID không hợp lệ! Vui lòng nhập số nguyên.");
                        }
                        break;

                    case 4:
                        System.out.println("\n=== Cập nhật tin tức ===");
                        System.out.print("Nhập ID tin tức cần cập nhật: ");
                        try {
                            int id = Integer.parseInt(scanner.nextLine());
                            News foundNews = newsDao.findById(id);
                            if (foundNews != null) {
                                System.out.print("Nhập tiêu đề mới: ");
                                String newTitle = scanner.nextLine();
                                System.out.print("Nhập nội dung mới: ");
                                String newContent = scanner.nextLine();

                                if (newTitle.trim().isEmpty() || newContent.trim().isEmpty()) {
                                    System.out.println("Tiêu đề và nội dung không được để trống!");
                                } else {
                                    foundNews.setTitle(newTitle);
                                    foundNews.setContent(newContent);
                                    boolean updated = newsDao.updateNew(foundNews);
                                    System.out.println("Cập nhật tin tức: " + (updated ? "Thành công" : "Thất bại"));
                                }
                            } else {
                                System.out.println("Không tìm thấy tin tức với ID: " + id);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("ID không hợp lệ! Vui lòng nhập số nguyên.");
                        }
                        break;

                    case 5:
                        System.out.println("\n=== Xóa tin tức ===");
                        System.out.print("Nhập ID tin tức cần xóa: ");
                        try {
                            int id = Integer.parseInt(scanner.nextLine());
                            boolean deleted = newsDao.deleteNews(id);
                            System.out.println("Xóa tin tức: " + (deleted ? "Thành công" : "Thất bại"));
                        } catch (NumberFormatException e) {
                            System.out.println("ID không hợp lệ! Vui lòng nhập số nguyên.");
                        }
                        break;

                    case 0:
                        System.out.println("Đang thoát chương trình...");
                        break;

                    default:
                        System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn từ 0 đến 5.");
                        break;
                }
            } while (choice != 0);

        } catch (Exception e) {
            System.err.println("Lỗi khi kiểm tra NewsDao: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
