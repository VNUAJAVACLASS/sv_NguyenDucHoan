package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BookDao;
import model.Book;


@WebServlet("/book")
public class BookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private BookDao bookDAO;

    @Override
    public void init() {
        bookDAO = new BookDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        String idStr = req.getParameter("id");

        if (action == null)
            action = "list";

        switch (action) {
            case "create":
                req.getRequestDispatcher("form.jsp").forward(req, resp);
                break;

            case "edit":
                int idEdit = Integer.parseInt(idStr);
                Book editBook = bookDAO.findById(idEdit);
                req.setAttribute("book", editBook);
                req.getRequestDispatcher("form.jsp").forward(req, resp);
                break;

            case "delete":
                int idDelete = Integer.parseInt(idStr);
                bookDAO.deleteBook(idDelete);
                resp.sendRedirect("book");
                break;

            case "detail":
                int idDetail = Integer.parseInt(idStr);
                Book detailBook = bookDAO.findById(idDetail);
                req.setAttribute("book", detailBook);
                req.getRequestDispatcher("detail.jsp").forward(req, resp);
                break;

            default: // list
                List<Book> bookList = bookDAO.getAllBooks();
                req.setAttribute("bookList", bookList);
                req.getRequestDispatcher("list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String idStr = req.getParameter("id");
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String priceStr = req.getParameter("price");
        String imagePath = req.getParameter("imagePath");

        double price = 0.0;
        if (priceStr != null && !priceStr.isEmpty()) {
            try {
                price = Double.parseDouble(priceStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if (idStr == null || idStr.isEmpty()) {
            // Thêm mới
            Book newBook = new Book(title, author, price, imagePath);
            bookDAO.addBook(newBook);
        } else {
            // Cập nhật
            int id = Integer.parseInt(idStr);
            Book updateBook = new Book(id, title, author, price, imagePath);
            bookDAO.updateBook(updateBook);
        }

        resp.sendRedirect("book");
    }

}