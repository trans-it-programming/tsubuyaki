package jp.trans_it.tsubuyaki.servlet;

import java.sql.Connection;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.trans_it.tsubuyaki.model.Pair;
import jp.trans_it.tsubuyaki.model.dao.MessageDAO;
import jp.trans_it.tsubuyaki.model.entity.User;

@WebServlet("/top")
public class TopServlet extends AuthenticationServlet {

	@Override
	protected String view(
			HttpServletRequest request,
			HttpServletResponse response,
			Connection connection,
			User user
	) throws Exception {	
		MessageDAO dao = new MessageDAO(connection);
		List<Pair> pairs = dao.findVisible(user.getId());
		System.out.println(pairs);

		request.setAttribute("pairs", pairs);	

		String jsp = "/WEB-INF/jsp/top.jsp";
		return jsp;
	}
}
