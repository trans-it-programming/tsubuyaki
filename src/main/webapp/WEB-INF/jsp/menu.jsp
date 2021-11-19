<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="jp.trans_it.tsubuyaki.model.entity.User" %>

<%
	User user = (User)session.getAttribute("user");
%>


<div id="menu">
		<a class="menuItem" href="top">ホーム</a>
<%
	if(user.isAdmin()) {
%>
		<a class="menuItem" href="user">ユーザー管理</a>
<%
	}
%>
		<a class="menuItem" href="logout">ログアウト</a>
</div>
