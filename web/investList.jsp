<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/1/1
  Time: 8:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="css/tb.css">
</head>
<body>
<c:if test="${not empty requestScope.ipageBean.list}">
    <table border="1px solid" bordercolor="cadetblue" cellspacing="0" cellpadding="2px">
        <thead>
        <td>产品编号</td>
        <td>投资金额</td>
        <td>操作</td>
        </thead>
        <c:forEach items="${requestScope.ipageBean.list}" var="invest">
            <div>
                <tbody>
                <tr>
                    <td>${invest.productId}</td>
                    <td>${invest.investValue}</td>
                    <td><a href="${pageContext.request.contextPath}/InvestServlet?method=del&productId=${invest.productId}&investValue=${invest.investValue}">删除</a></td>
                </tr>
                </tbody>
            </div>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
