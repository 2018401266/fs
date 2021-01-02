<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/30
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>收支记录表</title>
    <link rel="stylesheet" href="css/tb.css">
    <style type="text/css">
        *{
            font-family: "微软雅黑 Light";
            text-align: center;
        }
        .foot a{
            color: cadetblue;
        }
    </style>
</head>
<body>
<c:if test="${not empty requestScope.bpageBean.list}">
    <table border="1px solid cadetblue" cellpadding="0" cellspacing="0">
        <thead>
        <td>收支记录编号</td>
        <td>成员账户</td>
        <td>类型</td>
        <td>金额</td>
        <td>日期</td>
        <td>状态</td>
        </thead>
        <c:forEach items="${requestScope.bpageBean.list}" var="budget">
            <div align="center">

                <tbody>
                <tr>
                    <td>${budget.budgetId}</td>
                    <td>${budget.memberId}</td>
                    <td>${budget.type}</td>
                    <td>${budget.value}</td>
                    <td><fmt:formatDate value="${budget.budgetDate}" pattern="yyyy-MM-dd"/></td>
                    <td>${budget.state}</td>
                </tr>
                </tbody>

            </div>
        </c:forEach>
    </table>
</c:if>
<div class="foot">
    <a href="javascript:beforePage('${bpageBean.beforePage}')">上一页</a>
    共${bpageBean.totalPages}页 共${bpageBean.totalRows}行
    第${bpageBean.currentPage}页
    <a href="javascript:afterPage('${bpageBean.afterPage}')">下一页</a>
</div>
<script>
    function beforePage(page) {

        window.location = "${pageContext.request.contextPath}/BudgetServlet?currentPage=" + page + "&pageSize=${pageSize}";

    }

    function afterPage(page) {

        window.location = "${pageContext.request.contextPath}/BudgetServlet?currentPage=" + page + "&pageSize=${pageSize}";

    }
</script>
</body>
</html>