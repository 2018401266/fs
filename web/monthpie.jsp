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
    <title>月收支饼图</title>
    <style>
        #container-sum {
            background: cornsilk;
            position: absolute;
            left: 0px;
            width: 450px;
        }
        #container-left {
            background: oldlace;
            position: absolute;
            left: 450px;
            width: 450px;
        }
        #container-right {
            background: whitesmoke;
            position: absolute;
            left: 900px;
            width: 450px;
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
        title: {
            text: '月收支',
            subtext: '',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            left: 'center',
            top: 'bottom',
            data: [
                <c:forEach items="${monthList}" var="charts">
                ["${charts.type}"],
                </c:forEach>
            ]
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        series: [
            {
                name: '月收支',
                type: 'pie',
                radius: [40, 100],
                center: ['60%', '30%'],
                roseType: 'area',
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
    var dom1 = document.getElementById("container-left");
    var myChart1 = echarts.init(dom1);
    var app = {};

    option = null;
    option = {
        title: {
            text: '月收入',
            subtext: '',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            left: 'center',
            top: 'bottom',
            data: [
                <c:forEach items="${monthIncomeList}" var="charts">
                ["${charts.type}"],
                </c:forEach>
            ]
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        series: [
            {
                name: '月收入',
                type: 'pie',
                radius: [40, 100],
                center: ['60%', '30%'],
                roseType: 'area',
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
        myChart1.setOption(option, true);
    }

</script>
<div id="container-right" style="height: 100%"></div>
<script type="text/javascript">
    var dom2 = document.getElementById("container-right");
    var myChart2 = echarts.init(dom2);
    var app = {};

    option = null;
    option = {
        title: {
            text: '月支出',
            subtext: '',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            left: 'center',
            top: 'bottom',
            data: [
                <c:forEach items="${monthExpenseList}" var="charts">
                ["${charts.type}"],
                </c:forEach>
            ]
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        series: [
            {
                name: '月支出',
                type: 'pie',
                radius: [40, 100],
                center: ['60%', '30%'],
                roseType: 'area',
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
