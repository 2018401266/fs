<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/28
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" deferredSyntaxAllowedAsLiteral="true"%>
<html>
<head>
    <title>理财产品添加</title>
    <link rel="stylesheet" href="css/normal.css">
</head>
<body>

<form action="${pageContext.request.contextPath}/ProductServlet?method=add" method="post">
    <h4>理财产品添加</h4>
        <div class="all_resume">
            <table width="300" border="0" cellpadding="3" cellspacing="1">
                <tr>
                    <th width="110" align="right">产品编号：</th>
                    <td><input class="tn-textbox" type="text" name="productId"></td>
                </tr>
            </table>
            <div class="table_style">
                <table width="300" border="0" cellpadding="3" cellspacing="1">
                    <tr>
                        <th width="110" align="right">产品名称：</th>
                        <td><input class="tn-textbox" type="text" name="productName"></td>
                    </tr>
                </table>
                <div class="he"></div>
                <table width="300" border="0" cellpadding="3" cellspacing="1">
                    <tr>
                        <th width="110" align="right">产品收益率：</th>
                        <td><input class="tn-textbox" type="text" name="productYield"></td>
                    </tr>
                </table>
                <div style="margin-left:100px;;margin-top: 20px">
                    <input type="submit" class="save" value="添加">
                    <input name="" type="reset" class="cancel" value="取消">
                </div>
            </div>
        </div>
</form>
</body>
</html>
