package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.NewsDao;
import model.News;

public class NewsServlet extends HttpServlet {
    private NewsDao newsDao;
    private List<News> newsList;

    @Override
    public void init() throws ServletException {
        try {
            newsDao = new NewsDao();
            newsList = new ArrayList<>();
            newsList = newsDao.getAllNews();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize NewsServlet: " + e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String action = req.getParameter("action");
        String idStr = req.getParameter("id");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "create":
                req.getRequestDispatcher("form.jsp").forward(req, resp);
                break;

            case "edit":
                if (idStr != null && !idStr.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idStr);
                        News editNews = newsDao.findById(id);
                        if (editNews != null) {
                            req.setAttribute("news", editNews);
                            req.getRequestDispatcher("form.jsp").forward(req, resp);
                        } else {
                            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "News not found");
                        }
                    } catch (NumberFormatException e) {
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format");
                    }
                } else {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID is required for edit");
                }
                break;

            case "delete":
                if (idStr != null && !idStr.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idStr);
                        newsDao.deleteNews(id);
                        newsList = newsDao.getAllNews();
                        resp.sendRedirect("news");
                    } catch (NumberFormatException e) {
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format");
                    }
                } else {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID is required for delete");
                }
                break;

            case "detail":
                if (idStr != null && !idStr.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idStr);
                        News detailNews = newsDao.findById(id);
                        if (detailNews != null) {
                            req.setAttribute("news", detailNews);
                            req.getRequestDispatcher("detail.jsp").forward(req, resp);
                        } else {
                            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "News not found");
                        }
                    } catch (NumberFormatException e) {
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format");
                    }
                } 
                break;

            default:
                newsList = newsDao.getAllNews();
                req.setAttribute("newsList", newsList);
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
        String content = req.getParameter("content");

        // Input validation
        if (title == null || title.trim().isEmpty() || content == null || content.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Title and content are required");
            return;
        }

        try {
            if (idStr == null || idStr.isEmpty()) {
                // Create new news
                News newNews = new News(0, title, content);
                newsDao.addNews(newNews);
            } else {
                // Update existing news
                int id = Integer.parseInt(idStr);
                News existing = newsDao.findById(id);
                if (existing != null) {
                    existing.setTitle(title);
                    existing.setContent(content);
                    newsDao.updateNew(existing);
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "News not found");
                    return;
                }
            }
            newsList = newsDao.getAllNews();
            resp.sendRedirect("news");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format");
        }
    }
}