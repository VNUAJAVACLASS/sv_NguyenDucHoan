package controller;

import java.io.IOException;
import java.net.http.HttpClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final String USERNAME = "admin";
	private static final String PASSWORD = "123";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Cookie[] cookies = req.getCookies();
		String rememberedUser = null;

		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("rememberedUser")) {
					rememberedUser = c.getValue();
					break;
				}
			}
		}

		req.setAttribute("rememberedUser", rememberedUser);
		req.getRequestDispatcher("login").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);

		req.setCharacterEncoding("UTF-8");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");

		if (USERNAME.equals(username) && PASSWORD.equals(password)) {
			// dang nhap thanh cong

			HttpSession session = req.getSession();
			session.setAttribute("username", username);

			if ("on".equals(remember)) {
				Cookie cookie = new Cookie("rememberedUser", username);
				cookie.setMaxAge(60 * 60 * 24 * 7);
				resp.addCookie(cookie);
			} else {
				Cookie cookie = new Cookie("rememberedUser", "");
				cookie.setMaxAge(0);
				resp.addCookie(cookie);
			}

			resp.sendRedirect("adminHome");
		}

		else {
			req.setAttribute("error", "Sai ten dang nhap hoac mat khau.");
			req.getRequestDispatcher("login").forward(req, resp);
		}
		
	}

}
