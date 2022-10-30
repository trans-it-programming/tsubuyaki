package jp.trans_it.tsubuyaki.servlet;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.trans_it.tsubuyaki.model.Const;
import jp.trans_it.tsubuyaki.model.entity.User;

public abstract class AuthenticationServlet extends JspServlet {
	@Override
	protected String view(
			HttpServletRequest request, HttpServletResponse response, Connection connection
	) throws Exception {
		String jsp = null;
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(Const.LOGIN_USER_KEY);
		
		if(user == null) {
			jsp = "WEB-INF/jsp/login.jsp";
		}
		else {
			jsp = view(request, response, connection, user);
		}
		
		return jsp;
	}

	
	protected abstract String view(
		HttpServletRequest request,
		HttpServletResponse response,
		Connection connection,
		User user
	) throws Exception;
}
