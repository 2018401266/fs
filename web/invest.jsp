<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/31
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>投资管理</title>
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
            font-size:15px;
            margin-bottom: 10px;
        }
        .right iframe{
            border: gainsboro solid 1px;
            border-radius: 20px;
        }
    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/InvestServlet?method=add" method="post">

    <div class="resume_left">
        <h4>添加投资信息</h4>
        <div class="all_resume">
            <div class="table_style">
                <table width="300" border="0" cellpadding="3" cellspacing="1">
                    <tr>
                        <th width="110" align="right">理财产品编号：</th>
                        <td><input class="tn-textbox" type="text" name="productId"></td>
                    </tr>
                </table>
                <table width="300" border="0" cellpadding="3" cellspacing="1">
                    <tr>
                        <th width="110" align="right">投资金额：</th>
                        <td><input class="tn-textbox" type="text" name="investValue"></td>
                    </tr>
                </table>
                <table width="300" border="0" cellpadding="3" cellspacing="1">
                    <tr>
                        <th width="110" align="right">投资日期：</th>
                        <td ><input class="tn-textbox" type="date" name="budgetDate"></td>
                    </tr>
                </table>
                <div style="margin-left:100px;margin-top: 20px">
                    <input width="200px" type="submit" class="save" value="添加投资">
                </div>
            </div>
        </div>
    </div>
</form>
<div class="right">
    <a href="${pageContext.request.contextPath}/ProductServlet?method=show" target="show">查询理财产品列表</a>
    <br>
    <iframe src="" name="show" width="400px" height="200px"></iframe>
    <br>
    <a href="${pageContext.request.contextPath}/InvestServlet?method=show" target="showi">查询投资列表</a>
    <br>
    <iframe src="" name="showi" width="400px" height="150px"></iframe>
</div>

</body>
</html>
