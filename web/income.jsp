<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/28
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加收支记录</title>
    <link rel="stylesheet" href="css/normal.css">
    <style type="text/css">
        .right{
            position: absolute;
            left: 400px;
            top:40px;
        }
        .right a{
            color: cadetblue;
            font-weight: bold;
            font-size:25px;
            margin-bottom: 10px;
        }
        .right iframe{
            border: gainsboro solid 1px;
            border-radius: 20px;
        }
    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/BudgetServlet?method=add" method="post">
    <div class="resume_left">
        <h4>添加收支记录</h4>
        <div class="all_resume">
            <input class="tn-textbox" type="hidden" name="budgetId" value="${budget.budgetId}">
            <div class="table_style">
                <table width="300" border="0" cellpadding="3" cellspacing="1">
                    <tr>
                        <th width="110" align="right">成员账户：</th>
                        <td ><input class="tn-textbox" type="text" name="memberId"
                                                     value="${budget.memberId}"></td>
                    </tr>
                </table>
                <table width="300" border="0" cellpadding="3" cellspacing="1">
                    <tr>
                        <th width="110" align="right">类型：</th>
                        <td><input class="tn-textbox" type="text" name="type" value="${budget.type}">
                        </td>
                    </tr>
                </table>
                <table width="300" border="0" cellpadding="3" cellspacing="1">
                    <tr>
                        <th width="110" align="right">金额：</th>
                        <td><input class="tn-textbox" type="text" name="budgetValue"
                                                     value="${budget.value}"></td>
                    </tr>
                </table>
                <table width="300" border="0" cellpadding="3" cellspacing="1">
                    <tr>
                        <th width="110" align="right">日期：</th>
                        <td>
                            <input type="date" class="tn-textbox" name="budgetDate" value="<fmt:formatDate value="${budget.budgetDate}"
                            pattern="yyyy-MM-dd"/> "></td>
                    </tr>
                </table>
                <table width="300" border="0" cellpadding="3" cellspacing="1">
                    <tr>
                        <th width="110" align="right">状态：</th>
                        <td>
                                <input type="radio" name="state" value="收入" checked>收入
                                <input type="radio" name="state" value="支出" >支出
                        </td>
                    </tr>
                </table>
                <div style="margin-left:100px;;margin-top: 30px">
                    <input type="submit" class="save" value="保存">
                    <input type="reset" class="cancel" value="取消">
                </div>
            </div>
        </div>
    </div>
</form>
<div class="right">
    <a href="${pageContext.request.contextPath}/BudgetServlet?method=show" target="show">查询家庭收支列表</a>
    <iframe src="" align="center" name="show" width="500px" height="360px"></iframe>
</div>

</div>
</body>
</html>

