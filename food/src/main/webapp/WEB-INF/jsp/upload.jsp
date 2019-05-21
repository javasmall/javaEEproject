<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <%--<meta charset="gbk">--%>
    <title>正大食品（宿迁）有限公司</title>
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
    <h2>档案文件上传：</h2>
    <h4></h4>
    <div class="news-list">
        <div class="news-list-left">
            <div class="news-list-item">
                <div class="author-time">
                    <span>当前</span> 时间为 <span><span id="time1" style="color: blueviolet;"></span></span>
                </div>
                <h3>您的历史上传为</h3>
                <div class="news-des" id="new-des">
                    <table id="table" border="0">
                        <tr>
                            <td bgcolor="aqua">&nbsp&nbsp名称&nbsp&nbsp</td>
                            <td>&nbsp&nbsp日期&nbsp&nbsp</td>
                            <td bgcolor="#5f9ea0">种类&nbsp&nbsp</td>
                            <td bgcolor="#dc143c">&nbsp&nbsp查看</td>
                            <td bgcolor="#ff8c00">删除</td>
                        </tr>
                    </table>
                    <%--<h3 class="news-title">test1adga &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href=" " target="_blank">下载</a></h3>--%>
                </div>
            </div>

        </div>

        <div class="news-list-right">
            <div class="about">
                <h4>文件上传</h4>
                <%--<div class="about-des">--%>
                <%--<p>请选择对应文件</p>--%>
                <%--</div>--%>
                <form method="post" action="onfile" name="myform" onsubmit="return judel()"
                      enctype="multipart/form-data">
                    <input class="btn" type="file" name="file"/>
                    <br><br>
                    <select class="select" id="typeselect" name="type" placeholder="">
                        <option value="资质">资质</option>
                        <option value="外检" selected>外检</option>
                        <option value="规格书">规格书</option>
                        <option value="认证">认证</option>
                        <option value="其他">其他</option>
                    </select>

                    <input type="hidden" id="sapid" name="sapid" value=""/>
                    <input class="button" type="submit" value="上传"/>
                </form>
            </div>
        </div>
        <footer class="copyright">
            Copyright 2018 All rights reserved.
            <div class="col-xs-12">本站总访问量：1000</div>
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
        $.ajax({
            url: '/food/getpathbyid',
            type: 'GET', //GET
            async: true,    //或false,是否异步
            data: {
                id: aid
            },
            timeout: 5000,    //超时时间
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (data) {
                console.log(data);
                for (var i = 0; i < data.length; i++) {
                    // $("#new-des").append("<h3 class=\"news-title\">"+data[i].name+" &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" +
                    //     "<a href=\""+data[i].path+" \" target=\"_blank\">下载</a></h3>");
                    $("#table").append("<tr>  " +
                        "<td bgcolor=\"aqua\">" + data[i].name + " &nbsp&nbsp&nbsp&nbsp&nbsp</td>" +
                        " <td>&nbsp&nbsp" + data[i].time + "&nbsp&nbsp</td>" +
                        "<td bgcolor=\"#5f9ea0\">" + data[i].type + "</td>" +
                        " <td bgcolor=\"#ffcc00\"><a href=\"/food" + data[i].path + "\" target=\"_blank\">&nbsp&nbsp查看&nbsp&nbsp</a></td>" +
                        "<td bgcolor=\"red\"><a href=\"/food/delete?filename=" + data[i].name + "&id=" + data[i].sapid + "&path=" + data[i].path + "\">删除</a></td>" +
                        " </tr>")
                }
            }
        })
    });

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

    function judel() {
        var x = document.forms["myform"]["file"].value;
        if (x == null || x == "") {
            alert("需要选择文件！。");
            return false;
        }
        return true;
    }
</script>
</html>

