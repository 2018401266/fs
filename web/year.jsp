<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/30
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>年收支饼图</title>
    <style type="text/css">
        #container-sum {
            background: cornsilk;
            position: absolute;
            left: 70px;
            width: 400px;
        }
        #container-left {
            background: oldlace;
            position: absolute;
            left: 470px;
            width: 400px;
        }
        #container-right {
            background: whitesmoke;
            position: absolute;
            left: 870px;
            width: 400px;
        }
        input {
            outline: none;
        }
    </style>

</head>

<body style="height: 100%; margin: 0">
<script type="text/javascript" src="js/echarts.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts@4/dist/echarts.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-gl@1/dist/echarts-gl.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-stat@1/dist/ecStat.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts@4/dist/extension/dataTool.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts@4/map/js/china.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts@4/map/js/world.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts@4/dist/extension/bmap.min.js"></script>
<input type="image" src="${pageContext.request.contextPath}/img/OIP.jpg" width="40px" onclick="window.location.href='index.jsp'" value="返回">
<div id="container-sum" style="height: 100%"></div>
<script type="text/javascript">
    var dom = document.getElementById("container-sum");
    var myChart = echarts.init(dom);
    var app = {};

    option = null;
    option = {
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        legend: {
            orient: 'vertical',
            left: 10,
            data: [
                <c:forEach items="${monthList}" var="charts">
                ["${charts.type}"],
                </c:forEach>
            ]
        },
        series: [
            {
                name: '年收支',
                type: 'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: [
                    <c:forEach items="${monthList}" var="charts">
                    {value: "${charts.chartsValue}", name: "${charts.type}"},
                    </c:forEach>
                ]
            }
        ]
    };
    ;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }

</script>

<div id="container-left" style="height: 100%"></div>
<script type="text/javascript">
    var dom = document.getElementById("container-left");
    var myChart = echarts.init(dom);
    var app = {};

    option = null;
    option = {
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        legend: {
            orient: 'vertical',
            left: 10,
            data: [
                <c:forEach items="${monthIncomeList}" var="charts">
                ["${charts.type}"],
                </c:forEach>
            ]
        },
        series: [
            {
                name: '年收入',
                type: 'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: [
                    <c:forEach items="${monthIncomeList}" var="charts">
                    {value: "${charts.chartsValue}", name: "${charts.type}"},
                    </c:forEach>
                ]
            }
        ]
    };
    ;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }

</script>
<div id="container-right" style="height: 100%"></div>
<script type="text/javascript">
    var dom2 = document.getElementById("container-right");
    var myChart2 = echarts.init(dom2);
    var app = {};

    option = null;
    option = {
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        legend: {
            orient: 'vertical',
            left: 10,
            data: [
                <c:forEach items="${monthExpenseList}" var="charts">
                ["${charts.type}"],
                </c:forEach>
            ]
        },
        series: [
            {
                name: '年支出',
                type: 'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: [
                    <c:forEach items="${monthExpenseList}" var="charts">
                    {value: "${charts.chartsValue}", name: "${charts.type}"},
                    </c:forEach>
                ]
            }
        ]
    };
    ;
    if (option && typeof option === "object") {
        myChart2.setOption(option, true);
    }
</script>
</body>
</html>
