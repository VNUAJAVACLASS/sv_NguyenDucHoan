package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Book;

@WebServlet("/adminHome")
public class AdminHomeServlet extends HttpServlet {
	private List<Book> listBook = new ArrayList<>();
	private static int idCounter = 1;

	@Override
	public void init() {
		listBook.add(new Book(idCounter++, "Truyen Kieu", "Nguyen Du", 340000));
		listBook.add(new Book(idCounter++, "De men phieu luu ky", "To Huu", 340000));
		listBook.add(new Book(idCounter++, "Doremon", "Dau ten", 340000));

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html, charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		String id = req.getParameter("id");
		
		HttpSession session = req.getSession(false);
		String user = (session == null) ? null : (String) session.getAttribute("username");
		if (user == null) {
			resp.sendRedirect("login"); // quay về trang login nếu chưa đăng nhập
			return;
		}

		if (action == null)
			action = "list";

		switch (action) {
		case "create":
			req.getRequestDispatcher("form.jsp").forward(req, resp);
			break;

		case "edit":
			int idEdit = Integer.parseInt(id);
			Book editBook = findById(idEdit);

			req.setAttribute("book", editBook);
			req.setAttribute("isEdit", true);
			req.getRequestDispatcher("form.jsp").forward(req, resp);
			break;
		case "detail":
			int idDetail = Integer.parseInt(id);
			Book detailBook = findById(idDetail);

			req.setAttribute("book", detailBook);
			req.getRequestDispatcher("detail.jsp").forward(req, resp);

			break;
		case "delete":
			int idDelete = Integer.parseInt(id);
			listBook.removeIf(n -> n.getBookId() == idDelete);
			resp.sendRedirect("books");
			break;
		default:
			req.setAttribute("listbook", listBook);
			req.getRequestDispatcher("list.jsp").forward(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		// ==== Kiểm tra session đăng nhập ====
		HttpSession session = req.getSession(false);
		String user = (session == null) ? null : (String) session.getAttribute("username");
		if (user == null) {
			resp.sendRedirect("login");
			return;
		}
		
		// ====================================
		// Lấy các trường từ form được submit tới ở trang form.jsp
		String idStr = req.getParameter("id");
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String price = req.getParameter("price");

		// Nếu ko có id, ứng với tình huống tạo mới tin tức
		if (idStr == null || idStr.isEmpty()) {
			// Tạo tin tức mới với id mới
			float price1 = Float.parseFloat(price);
			Book newBook = new Book(idCounter++, title, author, price1);
			listBook.add(newBook);
		} else {
			// Cập nhật tin tức với id tương ứng
			float price1 = Float.parseFloat(price);
			int id = Integer.parseInt(idStr);
			Book existing = findById(id);
			if (existing != null) {
				existing.setTitle(title);
				existing.setAuthor(author);
				existing.setPrice(price1);
			}

		}
		// Khi chuyển hướng đến servlet "news", phương thức doGet được gọi
		// sẽ ghi newsList vào request và chuyển tiếp tới trang list.jsp
		resp.sendRedirect("books");
	}

	private Book findById(int id) {
		for (Book b : listBook) {
			if (b.getBookId() == id)
				return b;
		}
		return null;
	}
}