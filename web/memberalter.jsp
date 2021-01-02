<%@ page import="com.bean.Member" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/28
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成员信息</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/MemberServlet?method=show" method="post">

    <div class="resume_left">
        <br>
        <div class="all_resume">
            <div class="he"></div>
            <div style="margin-left:100px;">
                <input name="save" type="submit" formtarget="show" class="save" value="显示成员列表">

            </div>
        </div>

    </div>
    <iframe src="" name="show" width="500px" height="300px"></iframe>
    </div>
</form>
<form action="${pageContext.request.contextPath}/MemberServlet?method=update" method="post">

    <div class="resume_left">
        <div class="resume_title">
            <div style="float:left">修改基本信息</div>
        </div>
        <br>
        <div class="all_resume">
            <div class="table_style">
                <table width="300" border="0" cellpadding="3" cellspacing="1" bgcolor="#EEEEEE">
                    <tr>
                        <th width="110" align="right" bgcolor="#F8F8F8">成员账号：</th>
                        <td bgcolor="#F8F8F8"><input type="text" name="memberId" value="${member.memberId}"></td>
                    </tr>
                </table>
                <table width="300" border="0" cellpadding="3" cellspacing="1" bgcolor="#EEEEEE">
                    <tr>
                        <th width="110" align="right" bgcolor="#F8F8F8">姓名：</th>
                        <td bgcolor="#F8F8F8"><input type="text" name="memberName" value="${member.memberName}"></td>
                    </tr>
                </table>
                <table width="300" border="0" cellpadding="3" cellspacing="1" bgcolor="#EEEEEE">
                    <tr>
                        <th width="110" align="right" bgcolor="#F8F8F8">家庭关系：</th>
                        <td bgcolor="#F8F8F8"><input type="text" name="memberRelation" value="${member.memberRelation}"></td>
                    </tr>
                </table>
                <div class="he"></div>
                <table width="300" border="0" cellpadding="3" cellspacing="1" bgcolor="#EEEEEE">
                    <tr>
                        <th width="110" align="right" bgcolor="#F8F8F8">手机：</th>
                        <td bgcolor="#F8F8F8"><input type="text" name="memberPhone" value="${member.memberPhone}"></td>
                    </tr>
                </table>

                <div class="he"></div>
                <div style="margin-left:100px;">
                    <input name="save" type="submit" class="saveum" value="修改成员信息">

                </div>
            </div>

        </div>
    </div>
</form>

</body>
</html>
