<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="/error.jsp"%>
<%@ page import="com.bean.Account"%>
<%
    // 获得请求的绝对地址
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>家庭理财系统</title>
    <!-- 设置网页的基链接地址 -->
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="css/top.css">
</head>
<body>
<div class="con">
<h1 class="head">家庭理财系统</h1>
<div class="head_user">
    <%
        Account sessionAccount = (Account)session.getAttribute("SESSION_ACCOUNT");
        if (session.getAttribute("SESSION_ACCOUNT") == null) {
    %>
    <a href="login.jsp" target="_parent"><span class="type1">登录</span></a>
    <a href="register.jsp" target="_parent"><span class="type2">注册</span></a>
    <%
    } else {
    %>
    <p class="id">账号<%=sessionAccount.getAccountId()%>，您好！</p>
    <a class="out" href="LogoutServlet">退出</a>
    <%
        }
    %>
</div>
</div>
</body>
</html>