<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <%--<meta charset="gbk">--%>
    <title>正大食品(宿迁)有限公司档案管理</title>
    <link href="../../food/css/page.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<nav class="navbar">
    <div class="navbar-content-1">
        <a href="#">&nbsp;&nbsp;&nbsp;你好 </a>
    </div>
    <%--<div class="container navbar-2">--%>
    <%--<a href="">请在右侧上传您的文件</a>--%>
    <%--</div>--%>
    <div class="navbar-content-2">
        <a href="/food/login">&nbsp;&nbsp;退出&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
    </div>
</nav>
<div class="container">
    <h1><img src="../../food/images/logo.jpg" width="30" height="30">&nbsp;正大食品（宿迁）有限公司</h1>
    <h2>供应商档案管理</h2>
    <h2>档案文件下载：</h2>
    <h4></h4>
    <div class="news-list">
        <%--<div class="news-list-left">--%>
        <div class="news-list-item" style="display:block;">
            <table>
                <tr style="height: 35px">
                    <td id="td1" bgcolor="aqua" onclick="hide(1)" onmouseover="this.style.cursor='hand'">文件管理</td>
                    <td id="td2" bgcolor="blue" onclick="hide(2)" onmouseover="this.style.cursor='hand'"><font
                            color="yellow">账号管理</font></td>
                </tr>
            </table>
            <div class="author-time">
                <span>当前</span> 时间为 <span><span id="time1" style="color: blueviolet;"></span></span>
            </div>
            <h3>已有信息为</h3>
            <div class="news-des" id="new-des" style="display: block">
                <table id="table" border="1px" cellspacing="0px">
                    <tr>
                        <td bgcolor="#66ffcc">&nbsp&nbsp名称&nbsp&nbsp</td>
                        <td>&nbsp&nbsp日期&nbsp&nbsp</td>
                        <td>&nbsp&nbsp上传者&nbsp&nbsp</td>
                        <td bgcolor="#5f9ea0">种类</td>
                        <td width="50" bgcolor="#ff3399"><a href="#" target="_blank">查看</a></td>
                        <td width="50" bgcolor="#ff3399"><a href="#" target="_blank">下载</a></td>
                    </tr>
                </table>
                <%--<h3 class="news-title">test1adga &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href=" " target="_blank">下载</a></h3>--%>
            </div>
            <div class="news-des" id="new-des2" style="display:none">
                <table id="table2" border="1px" cellspacing="0px">
                    <tr>
                        <td bgcolor="#66ffcc">&nbsp&nbsp账户&nbsp&nbsp</td>
                        <td bgcolor="#5f9ea0">&nbsp&nbsp密码&nbsp&nbsp</td>
                        <td bgcolor="#ff3399">详细信息</td>
                        <td bgcolor="red">修改</td>
                    </tr>
                </table>
                <form action="/food/updateuser" id="changeform" method="post" style="display: none">
                    <li>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：<a id="usernametext"></a></li>
                    <input type="hidden" id="username" name="username" value=""/>
                    <li>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="text" id="password"
                                                                                  name="password" value=""></li>
                    <li>邮箱/手机：<input type="text" id="detail" name="detail" value=""></li>
                    <li>
                        <button type="submit">确定</button>&nbsp;&nbsp;&nbsp;<button type="reset">取消</button>
                    </li>
                </form>
                <%--<h3 class="news-title">test1adga &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href=" " target="_blank">下载</a></h3>--%>
            </div>
        </div>

        <footer class="copyright">
            Copyright 2018 All rights reserved.
            <div class="col-xs-12">如有使用疑问</div>
            <a target="_blank"
               href="http://wpa.qq.com/msgrd?v=3&uin=1315426911&site=qq&menu=yes">联系我们&nbsp;&nbsp;&nbsp;</a>
            <div><a href=http://www.miitbeian.gov.cn/>苏ICP备18027982号</a></div>
        </footer>
    </div>
</div>
<div class="screenbg">
    <ul>
        <li><a href="javascript:;"><img src="../../food/images/0.jpg"></a></li>
        <li><a href="javascript:;"><img src="../../food/images/1.jpg"></a></li>
        <li><a href="javascript:;"><img src="../../food/images/2.jpg"></a></li>
    </ul>
</div>
</body>
<%--<script type="text/javascript" src="../../food/js/jQuery1.7.js"></script>--%>
<%--<script type="text/javascript" src="../../food/js/jquery-1.8.2.min.js"></script>--%>
<script type="text/javascript" src="../../food/js/jquery1.42.min.js"></script>
<script type="text/javascript" src="../../food/js/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="../../food/js/jquery.cookie.js"></script>
<script type="text/javascript">
    var index = 0;
    var number = 0;
    $(function () {
        $(".news-list-left").css("width", "779px");
        $(".screenbg ul li").each(function () {
            $(this).css("opacity", "0");
        });
        $(".screenbg ul li:first").css("opacity", "1");
        index = 0;
        var t;
        var li = $(".screenbg ul li");
        number = li.size();

        function change(index) {
            li.css("visibility", "visible");
            li.eq(index).siblings().animate({opacity: 0}, 3000);
            li.eq(index).animate({opacity: 1}, 3000);
        }

        function show() {
            index = index + 1;
            if (index <= number - 1) {
                change(index);
            } else {
                index = 0;
                change(index);
            }
        }

        t = setInterval(show, 8000);
        //根据窗口宽度生成图片宽度
        var width = $(window).width();
        $(".screenbg ul img").css("width", width + "px");
    });
</script>
<script>
    var aid = $.cookie('userid');
    $(function () {
        //Ajax调用处理
        $("#sapid").attr("value", aid);
        $(".navbar-content-1 a").html("&nbsp;&nbsp;&nbsp;你好&nbsp;&nbsp;&nbsp;" + aid);
        //两个ajax填充两个dic
        $.ajax({
            url: '/food/getallpath',
            type: 'GET', //GET
            async: true, //或false,是否异步
            data: {},
            timeout: 5000,    //超时时间
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (data) {
                console.log(data);
                for (var i = 0; i < data.length; i++) {
                    $("#table").append("<tr>  <td bgcolor=\"#66ffcc\">" + data[i].name + " &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>" +
                        " <td>&nbsp&nbsp" + data[i].time + "&nbsp&nbsp</td>" + "<td>&nbsp&nbsp" + data[i].sapid + "&nbsp&nbsp</td>" +
                        "<td bgcolor=\"#5f9ea0\">" + data[i].type + "</td>" +
                        "<td width=\"50\" bgcolor=\"#ff3399\"><a href=\"/food" + data[i].path + "\" target=\"_blank\">查看</a></td>" +
                        " <td bgcolor=\"#ffcc00\"><a href=\"/food/download?filename=" + data[i].name + "&path=" + data[i].path + "\"" +
                        " target=\"_blank\">" + "<font color='blue'> " + "下载" + "</font>" + "</a></td></tr>")
                }
            }
        });
        $.ajax({
            url: '/food/getalluser',
            type: 'GET', //GET
            async: true, //或false,是否异步
            data: {},
            timeout: 5000,    //超时时间
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#table2").append(" <tr> " +
                        " <td bgcolor=\"#66ffcc\">&nbsp&nbsp" + data[i].sapid + "&nbsp&nbsp</td>\n" +
                        " <td bgcolor=\"#5f9ea0\">&nbsp&nbsp" + data[i].password + "&nbsp&nbsp</td>\n" +
                        "  <td  bgcolor=\"#ff3399\">" + data[i].detail + "</td>\n" +
                        "<td  bgcolor=\"red\" onmouseover=\"this.style.cursor='hand'\" " +
                        "onclick=\"changeVa(" + data[i].sapid + ",'" + data[i].password + "','" + data[i].detail + "' )\">修改</td>" +
                        "   </tr>")
                }
            }
        })
    });

    //先填充值进去
    function changeVa(a, b, c) {
        $("#table2").css("display", "none");
        $("#changeform").css("display", "block");
        $("#usernametext").html(a);
        $("#username").val(a);
        $("#password").val(b);
        $("#detail").val(c);
    }

    function hide(index) {
        if (index == 1) {
            $("#new-des2").css("display", "none");
            $("#new-des").css("display", "block");
        }
        else if (index == 2) {
            $("#new-des").css("display", "none");
            $("#new-des2").css("display", "block");
        }
    }

    function mytime() {
        var a = new Date();
        var b = a.toLocaleTimeString();
        var c = a.toLocaleDateString();
        document.getElementById("time1").innerHTML = c + "&nbsp" + b;
    }

    // setInterval(function () {change()},3000);
    setInterval(function () {
        mytime()
    }, 1000);
</script>
</html>

