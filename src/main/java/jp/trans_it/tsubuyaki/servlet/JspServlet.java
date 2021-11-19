package jp.trans_it.tsubuyaki.servlet;

import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.trans_it.tsubuyaki.model.entity.User;

public abstract class JspServlet extends DbServlet {
	private boolean checkLogin;

	public JspServlet(boolean checkLogin) {
		this.checkLogin = checkLogin;
	}

	@Override
	protected void action(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws Exception {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");

		String jsp = "/WEB-INF/jsp/login.jsp";
		if(!this.checkLogin || user != null) {
			jsp = this.view(request, response,  connection);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected abstract String view(
			HttpServletRequest request,
			HttpServletResponse response,
			Connection connection
	) throws Exception;
}
