<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="jp.trans_it.tsubuyaki.model.entity.User" %>

<%
	User user = (User)session.getAttribute("user");
%>

<header>
<%
	if(user != null) {
%>
		<jsp:include page="menu.jsp" />
<%
	}
%>
    <div id="title">
		<h1 id="subtitle">つぶやきシステム</h1>
		<h1 id="systemName">つぶやいた～</h1>
	</div>
<%
	if(user != null) {
%>
		<div id="welcome">
		    <%= user.getName() %> さん。ようこそ。
		</div>
<%
	}
%>
</header>
