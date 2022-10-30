package jp.trans_it.tsubuyaki.servlet;

import java.sql.Connection;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.trans_it.tsubuyaki.model.Pair;
import jp.trans_it.tsubuyaki.model.dao.MessageDAO;
import jp.trans_it.tsubuyaki.model.entity.Message;
import jp.trans_it.tsubuyaki.model.entity.User;
import jp.trans_it.tsubuyaki.util.StringUtil;

@WebServlet("/message")
public class MessageServlet extends AuthenticationServlet {

	@Override
	protected String view(
			HttpServletRequest request,
			HttpServletResponse response,
			Connection connection,
			User user
		) throws Exception {
		MessageDAO dao = new MessageDAO(connection);

		String operation = request.getParameter("operation");

		String jsp = null;
		if(operation.equals("edit")) {
			jsp = this.editMessage(request, user, dao);
		}
		else if(operation.equals("delete")) {
			jsp = this.deleteMessage(request, user, dao);
		}

		return jsp;
	}

	private String editMessage(HttpServletRequest request, User user, MessageDAO dao)
			throws Exception {
		String id = request.getParameter("id");
		Message message = dao.findById(Integer.parseInt(id));
		String text = StringUtil.unescapeHtml(message.getText());

		String error = null;
		if(message.getUserId() != user.getId()) {
			error = "編集の権限がありません。";
		}

		String jsp = null;
		if(error == null) {
			request.setAttribute("message", message);
			request.setAttribute("text", text);
			jsp = "/WEB-INF/jsp/editMessage.jsp";
		}
		else {
			request.setAttribute("error", error);
			List<Pair> pairs = dao.findVisible(user.getId());
			request.setAttribute("pairs", pairs);

			jsp = "/WEB-INF/jsp/top.jsp";
		}

		return jsp;
	}

	private String deleteMessage(HttpServletRequest request, User user, MessageDAO dao)
			throws Exception {
		String id = request.getParameter("id");
		Message message = dao.findById(Integer.parseInt(id));

		if(message.getUserId() == user.getId() || user.isAdmin()) {
			dao.delete(message.getId());
		}
		else {
			String error = "削除する権限がありません。";
			request.setAttribute("error", error);
		}

		List<Pair> pairs = dao.findVisible(user.getId());
		request.setAttribute("pairs", pairs);

		String jsp = "/WEB-INF/jsp/top.jsp";
		return jsp;
	}
}