package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;

@WebServlet("/clientHome")
public class ClientHomeServlet extends HttpServlet {

	private static List<Book> bookList = new ArrayList<>();
	private static int idCounter = 1;

	@Override
	public void init(){
		bookList.add(new Book(idCounter++, "Truyen Kieu", "Nguyen Du", 340000));
		bookList.add(new Book(idCounter++, "De men phieu luu ky", "To Huu", 340000));
		bookList.add(new Book(idCounter++, "Doremon", "Dau ten", 340000));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		String action = req.getParameter("action");
		String idStr = req.getParameter("id");

		if (action == null)
			action = "list";

		switch (action) {
		case "detail": // trường hợp click "Xem chi tiết"
			int idDetail = Integer.parseInt(idStr);
			Book detailBook = findByid(idDetail);
			req.setAttribute("book", detailBook);
			req.getRequestDispatcher("detail_client.jsp").forward(req, resp);
			break;

		default: // trường hợp mặc định tại trang chủ phía máy khách
			req.setAttribute("bookList", bookList);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			break;
		}
	}

	private Book findByid(int id) {
		for (Book b : bookList) {
			if (b.getBookId() == id)
				return b;
		}

		return null;
	}
}
