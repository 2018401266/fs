<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/31
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>理财产品列表</title>
    <link rel="stylesheet" href="css/tb.css">
</head>
<body>
<c:if test="${not empty requestScope.pageBean.list}">
    <table border="1px solid" bordercolor="cadetblue" cellspacing="0" cellpadding="2px">
        <thead>
        <td>产品编号</td>
        <td>产品名称</td>
        <td>收益率</td>
        </thead>
        <c:forEach items="${requestScope.pageBean.list}" var="product">
            <div>
                <tbody>
                <tr>
                    <td>${product.productId}</td>
                    <td>${product.productName}</td>
                    <td>${product.productYield}</td>
                </tr>
                </tbody>
            </div>
        </c:forEach>
    </table>
</c:if>
</body>
</html>