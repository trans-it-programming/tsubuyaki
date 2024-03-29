<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="jp.trans_it.tsubuyaki.model.Const" %>
<%@ page import="jp.trans_it.tsubuyaki.model.Pair" %>
<%@ page import="jp.trans_it.tsubuyaki.model.entity.User" %>
<%@ page import="jp.trans_it.tsubuyaki.model.entity.Message" %>

<%
	List<Pair> pairs = (List<Pair>)request.getAttribute("pairs");
	User loginUser = (User)session.getAttribute(Const.LOGIN_USER_KEY);
	String error = (String)request.getAttribute("error");
%>

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="head.jsp" />
		<title>つぶやき</title>
	</head>
	<body>
		<jsp:include page="header.jsp" />

		<main>
			<div>
				<a href="javascript:toggleNewMessage()">
					<span id="new_message_icon" class="icon far fa-plus-square"></span>
				</a>
			</div>
			<div id="new_message_area">
				<form id="new_message_form" method="post" action="postMessage"
				      enctype="multipart/form-data">
					<div>
						<textarea id="message_text" name="text"></textarea>
					</div>
					<div>
						<input type="radio" name="is_public" value="true" checked>公開
						<input type="radio" name="is_public" value="false">非公開
					</div>
					<div>
						<div id="image_file_name"></div>
						<input id="message_image_file" type="file" name="image_file">
						<a href="javascript:removeImage()">
							<span class="icon red  fas fa-times-circle"></span>
						</a>
					</div>
					<div>
						<input id="submit" type="submit" name="submit" value="登録">
					</div>
					<input type="hidden" name="delete_image_flag" value="false">
				</form>
				<form id="message_operation_form" method="post" action="message">
					<input id="id_input" type="hidden" name="id" value="">
					<input id="operation_input" type="hidden" name="operation" value="">
				</form>
			</div>
<%
	if(error != null) {
%>
			<div id="error"><%= error %></div>
<%
	}
%>

<%
	for(Pair pair : pairs) {
		User user = pair.getUser();
		Message message = pair.getMessage();
		String fileName = message.getImageFileName();
%>
			<div class="tsubuyaki">
				<div class="tsubuyaki_header">
					<h5>
						<%= user.getName() %>
					</h5>
					<h5>
						<%= message.getCreatedAt() %>
					</h5>
				</div>
				<p>
					<%= message.getText() %>
				</p>
<%
			if(fileName != null && !fileName.isEmpty()) {
%>
				<img src="image?id=<%= message.getId() %>">
<%
			}
%>

				<div class="tsubuyaki_icons">
<%
	    if(user.getId() == loginUser.getId()) {
%>
					<a href="javascript:editMessage(<%= message.getId() %>)">
						<span class="icon fas fa-pencil-alt"></span>
					</a>
<%
	    }
		if(user.getId() == loginUser.getId() || loginUser.isAdmin()) {
%>
					<a href="javascript:deleteMessage(<%= message.getId() %>)">
						<span class="icon far fa-trash-alt"></span>
					</a>
<%
	    }
%>
				</div>
			</div>
<%
	}
%>
		</main>
		<jsp:include page="footer.jsp" />
	</body>
</html>