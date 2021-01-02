<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/29
  Time: 8:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- 引入各种CSS样式表 -->
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/index.css">
    <script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <title>主页 - 家庭理财系统</title>
</head>

<body>
<iframe src="top.jsp" width="100%" height="100px" scrolling="no" frameborder="0"></iframe>
<!-- 左侧菜单选项========================================= -->
<div class="container-fluid">
    <div class="row-fluie">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="menu">
                <!-- 一级菜单 -->
                <li>
                    <a href="#accountMeun" class="firstMenu" data-toggle="collapse">
                        家庭账户管理 <span class="sr-only"></span></a>
                </li>
                <!-- 二级菜单 -->
                <ul id="accountMeun" class="menu-second">
                    <li>
                        <a href="#" onclick="showAtRight('show.jsp')">显示余额</a>
                    </li>
                    <li>
                        <a href="#" onclick="showAtRight('alterpwd.jsp')">修改登录密码</a>
                    </li>
                </ul>
                <!-- 一级菜单 -->
                <li>
                    <a href="#memberMeun" class="firstMenu" data-toggle="collapse">
                        家庭成员管理 <span class="sr-only"></span></a>
                </li>
                <!-- 二级菜单 -->
                <ul id="memberMeun" class="menu-second">
                    <li>
                        <a href="#" onclick="showAtRight('memberAdd.jsp')">家庭成员添加</a>
                    </li>
                    <li>
                        <a href="#" onclick="showAtRight('memberalter.jsp')">家庭成员修改</a>
                    </li>
                </ul>
                <!-- 一级菜单 -->
                <li>
                    <a href="#budgetMeun" class="firstMenu" data-toggle="collapse">
                        收支管理 <span class="sr-only"></span></a>
                </li>
                <!-- 二级菜单 -->
                <ul id="budgetMeun" class="menu-second">
                    <li>
                        <a href="###" onclick="showAtRight('income.jsp')">添加收支记录</a>
                    </li>
                </ul>
                <!-- 一级菜单 -->
                <li>
                    <a href="#productMeun" class="firstMenu" data-toggle="collapse">
                        投资产品管理 <span class="sr-only"></span></a>
                </li>
                <!-- 二级菜单 -->
                <ul id="productMeun" class="menu-second">
                    <li>
                        <a href="#" onclick="showAtRight('productAdd.jsp')">添加理财产品</a>
                    </li>
                    <li>
                        <a href="#" onclick="showAtRight('invest.jsp')">投资添加</a>
                    </li>
                </ul>
                <!-- 一级菜单 -->
                <li>
                    <a href="#sumMeun" class="firstMenu" data-toggle="collapse">
                        收支统计 <span class="sr-only"></span></a>
                </li>
                <!-- 二级菜单 -->
                <ul id="sumMeun" class="menu-second">
                    <li>
                        <a href="###" onclick="month_prompt()">月统计</a>
                    </li>
                    <li>
                        <a href="###" onclick="disp_prompt()">年统计</a>
                    </li>
                </ul>
            </ul>
        </div>
    </div>
</div>
<!-- 右侧内容展示==================================================  -->
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <!-- 载入左侧菜单指向的jsp（或html等）页面内容 -->
    <div id="content">

    </div>
</div>
<script type="text/javascript">
    function disp_prompt() {
        var name = prompt("请输入您要查询的年份", "")
        if(name != null && name != "") {
            window.location.href = "ConsusServlet?year=" + name;
        }
    }

    function month_prompt() {
        var name1 = prompt("请输入您要查询的年份和月份", "")
        if(name1 != null && name1 != "") {
            window.location.href = "ConsusServlet?month=" + name1;
        }
    }

    /*
     * 利用div实现左边点击右边显示的效果（以id="content"的div进行内容展示）
     * 注意：
     *  ①：js获取网页的地址，是根据当前网页来相对获取的，不会识别根目录；
     *  ②：如果右边加载的内容显示页里面有css，必须放在主页（即例中的index.jsp）才起作用
     *  （如果单纯的两个页面之间include，子页面的css和js在子页面是可以执行的。 主页面也可以调用子页面的js。但这时要考虑页面中js和渲染的先后顺序 ）
     */
    function showAtRight(url) {
        var xmlHttp;
        if(window.XMLHttpRequest) {
            // code for IE7+, Firefox, Chrome, Opera, Safari
            xmlHttp = new XMLHttpRequest(); //创建 XMLHttpRequest对象
        } else {
            // code for IE6, IE5
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlHttp.onreadystatechange = function() {
            //onreadystatechange — 当readystate变化时调用后面的方法
            if(xmlHttp.readyState == 4) {
                //xmlHttp.readyState == 4  ——  finished downloading response
                if(xmlHttp.status == 200) {
                    //xmlHttp.status == 200    ——  服务器反馈正常
                    document.getElementById("content").innerHTML = xmlHttp.responseText; //重设页面中id="content"的div里的内容
                    executeScript(xmlHttp.responseText); //执行从服务器返回的页面内容里包含的JavaScript函数
                }
                //错误状态处理
                else if(xmlHttp.status == 404) {
                    alert("出错了☹  （错误代码：404 Not Found），……！");
                    /* 对404的处理 */
                    return;
                } else if(xmlHttp.status == 403) {
                    alert("出错了☹  （错误代码：403 Forbidden），……");
                    /* 对403的处理 */
                    return;
                } else {
                    alert("出错了☹  （错误代码：" + request.status + "），……");
                    /* 对出现了其他错误代码所示错误的处理  */
                    return;
                }
            }
        }
        //把请求发送到服务器上的指定文件（url指向的文件）进行处理
        xmlHttp.open("GET", url, true); //true表示异步处理
        xmlHttp.send();
    }
</script>
</body>

</html>