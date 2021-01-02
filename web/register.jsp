<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/28
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册 - 家庭理财系统</title>
    <link rel="stylesheet" href="css/loginTap.css">
    <script src="js/jquery.js"></script>
    <script>
        function validate() {
            var accountId = document.getElementById("accountId").value;
            var accountPwd = document.getElementById("accountPwd").value;

//验证密码合法性
            if(password == ''){
                alert("密码不能为空！");
                return false;
            }else if(password.length<6 || password.length>12){
                alert("密码长度在6-12个字符之间！");
                return false;
            }

            return true;
        }
    </script>
</head>

<body>
<form id="registerForm" action="${pageContext.request.contextPath}/RegisterServlet" method="post" onsubmit="return validate()">

    <div class="content">
        <div class="content1">
            <div class="page_name">注册</div>
            <div class="login_l">
                <div class="span1">
                    <label class="tn-form-label">账号：</label>
                    <input id="accountId" class="tn-textbox" type="text" name="accountId">
                </div>
                <div class="span2">
                    <label class="tn-form-label">密码：</label>
                    <input id="accountPwd" class="tn-textbox"  type="password" name="accountPwd">
                </div>
                <div class="tn-form-row-button">
                    <div class="span3">
                        <input name="" type="submit" class="tn-button-text" value="立即注册">
                    </div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</form>

</body>
</html>