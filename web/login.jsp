<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/27
  Time: 8:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>登录 - 家庭理财系统</title>
    <link rel="stylesheet" href="css/loginTap.css">
</head>
<body>
<%
    String accountId = "";
    String accountPwd = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("COOKIE_ACCOUNTID".equals(cookie.getName())) {
                accountId = com.utils.CookieEncryptTool.decodeBase64(cookie.getValue());
            }
            if ("COOKIE_ACCOUNTPWD".equals(cookie.getName())) {
                accountPwd = com.utils.CookieEncryptTool.decodeBase64(cookie.getValue());
            }
        }
    }
%>
<!-- login content -->
<div class="content">
        <!--登录表单开始-->
        <!--登录-->
        <form action="LoginServlet" method="post">
            <div class="content1">
                <div class="page_name">登录</div>
                <div class="login_content">
                    <div class="login_l">
                        <div class="span1">
                            <br>
                            <label class="tn-form-label">账号：</label>
                            <input  class="tn-textbox" type="text" name="accountId" value="<%=accountId%>">
                        </div>
                        <div class="span2">
                            <label class="tn-form-label">密码：</label>
                            <input class="tn-textbox"  type="password" name="accountPwd" value="<%=accountPwd%>">
                        </div>
                        <div class="tn-form-row-button">
                            <div class="span3">
                                <input name="" type="submit" class="tn-button-text" value="登   录">
                                <span class="it-register-text">

          </span> </div>
                        </div>
                        <div class="clear"></div>
                    </div>
                    <div class="login_r">
                        <p align="center"> <b>还没有帐号？</b><a href="register.jsp">注册</a></p>
                        <div class="clear"></div>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
        </form>
</div>

</body>
</html>
