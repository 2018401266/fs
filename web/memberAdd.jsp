<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/28
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" deferredSyntaxAllowedAsLiteral="true"%>
<html>
<head>
    <title>家庭成员添加</title>
    <link rel="stylesheet" href="css/normal.css">
</head>
<body>

<form action="${pageContext.request.contextPath}/MemberServlet?method=add" method="post">
    <h4>家庭成员添加</h4>
        <div class="all_resume">
            <div class="he"></div>
            <table width="300" border="0" cellpadding="3" cellspacing="1" >
                <tr>
                    <th width="110" align="right">成员账户：</th>
                    <td ><input class="tn-textbox" type="text" name="memberId"></td>
                </tr>
            </table>
            <div class="table_style">
                <div class="he"></div>
                <table width="300" border="0" cellpadding="3" cellspacing="1">
                    <tr>
                        <th width="110" align="right" >姓名：</th>
                        <td ><input class="tn-textbox" type="text" name="memberName"></td>
                    </tr>
                </table>
                <div class="he"></div>
                <table width="300" border="0" cellpadding="3" cellspacing="1">
                    <tr>
                        <th width="110" align="right">家庭关系：</th>
                        <td><input class="tn-textbox" type="text" name="memberRelation"></td>
                    </tr>
                </table>
                <div class="he"></div>
                <table width="300" border="0" cellpadding="3" cellspacing="1">
                    <tr>
                        <th width="110" align="right">联系电话：</th>
                        <td><input class="tn-textbox" type="text" name="memberPhone"></td>
                    </tr>
                </table>

                <div style="margin-left:100px;margin-top: 50px">
                    <input type="submit" class="save" value="保存">
                    <input type="reset" class="cancel" value="取消">
                </div>
            </div>
        </div>
    </div>
</form>
</div>
</body>
</html>
