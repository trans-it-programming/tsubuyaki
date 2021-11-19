package jp.trans_it.tsubuyaki.servlet;

import java.sql.Connection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends JspServlet {

	public LogoutServlet() {
		super(true);
	}

	@Override
	protected String view(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("user");

		String jsp = "/WEB-INF/jsp/login.jsp";
		return jsp;
	}

}
