package jp.trans_it.tsubuyaki.servlet;

import java.sql.Connection;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.trans_it.tsubuyaki.model.Const;
import jp.trans_it.tsubuyaki.model.Pair;
import jp.trans_it.tsubuyaki.model.dao.MessageDAO;
import jp.trans_it.tsubuyaki.model.dao.UserDAO;
import jp.trans_it.tsubuyaki.model.entity.User;

@WebServlet("/login")
public class LoginServlet extends JspServlet {
	
	@Override
	protected String view(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws Exception {
		String jsp = null;
		String error = "";
		User user = null;

		UserDAO userDao = new UserDAO(connection);

		String email = request.getParameter("email");
		if(email == null || email.isEmpty()) {
			error = "メールアドレスを入力してください。";
		}

		String password = request.getParameter("password");
		if(password == null || password.isEmpty()) {
			error = error + "パスワードを入力してください。";
		}

		if(error.isEmpty()) {
			user = userDao.login(email, password);
		}

		if(user == null) {
			if(error.isEmpty()) {
				error = "ユーザー名もしくはパスワードが違います。";
			}
			request.setAttribute("error", error);
			jsp = "/WEB-INF/jsp/login.jsp";
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute(Const.LOGIN_USER_KEY, user);
			
			MessageDAO messageDao = new MessageDAO(connection);
			List<Pair> pairs = messageDao.findVisible(user.getId());
			request.setAttribute("pairs", pairs);

			jsp = "/WEB-INF/jsp/top.jsp";
		}

		return jsp;		
	}

}
