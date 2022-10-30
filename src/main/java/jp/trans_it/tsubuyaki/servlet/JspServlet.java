package jp.trans_it.tsubuyaki.servlet;

import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class JspServlet extends DbServlet {
	@Override
	protected void action(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws Exception {
		request.setCharacterEncoding("UTF-8");

		String jsp = this.view(request, response, connection);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}
	
	
	protected abstract String view(
			HttpServletRequest request,
			HttpServletResponse response,
			Connection connection
	) throws Exception;
}
