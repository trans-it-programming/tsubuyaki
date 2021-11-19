package jp.trans_it.tsubuyaki.servlet;

import java.sql.Connection;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.trans_it.tsubuyaki.model.Pair;
import jp.trans_it.tsubuyaki.model.dao.MessageDAO;
import jp.trans_it.tsubuyaki.model.entity.User;

@WebServlet("/top")
public class TopServlet extends JspServlet {

	public TopServlet() {
		super(true);
	}

	@Override
	protected String view(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws Exception {
		String jsp = null;

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		MessageDAO dao = new MessageDAO(connection);
		List<Pair> pairs = dao.findVisible(user.getId());

		request.setAttribute("pairs", pairs);

		jsp = "WEB-INF/jsp/top.jsp";
		return jsp;
	}
}
