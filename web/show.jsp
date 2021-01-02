<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/28
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
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
<html>
<head>
    <title>显示余额 - 家庭账户管理</title>
    <style type="text/css">
        h2{
            position: relative;
            left: 50px;
            top: 30px;
        }
    </style>
</head>
<body>
<%
    Account sessionAccount = (Account)session.getAttribute("SESSION_ACCOUNT");
%>
<h2>账户余额：<%=sessionAccount.getTotalAsset()%></h2>
<h2>理财总投资：<%=sessionAccount.getTotalInvest()%></h2>
</body>
</html>
