<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/28
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改登录密码 - 家庭账户管理</title>
    <link rel="stylesheet" href="css/normal.css">
</head>
<body>
<div class="he"></div>
<form action="${pageContext.request.contextPath}/AccountServlet?method=update" method="post">
    <h4>修改登录密码</h4>
    <table width="350px" border="0px" cellpadding="3" cellspacing="1" bgcolor="#EEEEEE">
        <tr>
            <th width="300px" align="right" bgcolor="white">请输入新密码：</th>
            <td bgcolor="white"><input class="tn-textbox" type="password" name="newPwd"></td>
        </tr>
        <tr>
            <th width="300px" align="right" bgcolor="white">请再次输入新密码：</th>
            <td bgcolor="white"><input class="tn-textbox" type="password" name="newPwd2"></td>
        </tr>
    </table>
    <div style="margin-left:150px;margin-top: 20px">
        <input type="submit" class="save" value="保存">
        <input type="reset" class="cancel" value="取消">
    </div>
</form>
</body>
</html>
