<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>家庭成员列表</title>
</head>

<body>
<c:if test="${not empty requestScope.pageBean.list}">
    <table border="1px solid" bordercolor="cadetblue" cellspacing="0" align="center" cellpadding="2px">
        <thead>
        <td>成员账户</td>
        <td>成员姓名</td>
        <td>家庭关系</td>
        <td>联系电话</td>
        <td>操作</td>
        </thead>
        <c:forEach items="${requestScope.pageBean.list}" var="member">
            <div align="center">
                <tbody>
                <tr>
                    <td>${member.memberId}</td>
                    <td>${member.memberName}</td>
                    <td>${member.memberRelation}</td>
                    <td>${member.memberPhone}</td>
                    <td><input type="button" onclick="mem_prompt()" value="删除成员信息"></td>
                </tr>
                </tbody>
            </div>
        </c:forEach>
    </table>
</c:if>
<script type="text/javascript">
    function mem_prompt()
    {
        var name=prompt("请输入您要删除的成员编号","")
        if (name!=null && name!="")
        {
            window.location.href="MemDelServlet?memberId="+name;
        }
    }
</script>
</body>
</html>
