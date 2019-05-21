<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>用户登录</title>
    <link href="../../food/css/login.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all"/>
    <%--<script type="text/javascript" src="../../food/js/jQuery1.7.js"></script>--%>
    <%--<script type="text/javascript" src="../../food/js/jquery-1.8.2.min.js"></script>--%>
</head>

<body>
<div id="tab">
    <ul class="tab_menu">
        <li class="selected">用户登录</li>
        <li>管理登录</li>
        <li>用户注册</li>
    </ul>
    <div class="tab_box">
        <!-- client登录开始 -->
        <div>
            <div class="stu_error_box"></div>
            <form action="/food/client" method="post" name="myform1" onsubmit="return validateForm1()"
                  class="stu_login_error">
                <div id="username1">
                    <label>账&nbsp;&nbsp;&nbsp;号：</label>
                    <input type="text" id="stu_username_hide" name="username" value=""/>
                    <!--ajaxurl="demo/valid.jsp"-->
                </div>
                <div id="password1">
                    <label>密&nbsp;&nbsp;&nbsp;码：</label>
                    <input type="password" id="stu_password_hide" name="password" value=""/>
                </div>
                <div id="remember1">
                    <input type="checkbox" name="remember">
                    <label>记住密码</label>
                </div>
                <div id="login1">
                    <button type="submit">登录</button>
                </div>
            </form>
        </div>
        <!-- client登录结束-->
        <!-- admin登录开始-->
        <div class="hide">
            <div class="tea_error_box"></div>
            <form action="/food/admin" method="post" name="myform2" onsubmit="return validateForm2()"
                  class="tea_login_error">
                <div id="username2">
                    <label>管理号：</label>
                    <input type="text" id="tea_username_hide" name="username" value=""/>
                    <!--ajaxurl="demo/valid.jsp"-->
                </div>
                <div id="password2">
                    <label>密&nbsp;&nbsp;&nbsp;码：</label>
                    <input type="password" id="tea_password_hide" name="password" value=""/>
                </div>
                <div id="remember2">
                    <input type="checkbox" name="remember">
                    <label>记住密码</label>
                </div>
                <div id="login2">
                    <button type="submit">登录</button>
                </div>
            </form>
        </div>
        <!-- 导师登录结束-->
        <!-- 教务登录开始-->
        <div class="hide">
            <div class="sec_error_box"></div>
            <form action="/food/register" method="post" name="myform" onsubmit="return validateForm()"
                  class="sec_login_error">
                <div id="username">
                    <label>账号（id）：</label>
                    <input type="text" id="sec_username_hide" name="username" value=""/>
                    <!--ajaxurl="demo/valid.jsp"-->
                </div>
                <div id="password">
                    <label>密码（>5）:</label>
                    <input type="password" id="sec_password_hide" name="password" value=""/>
                </div>
                <div id="detail">
                    <label>邮箱/手机：</label>
                    <input type="text" name="detail" value=""/>
                </div>
                <div id="remember">
                    <input type="checkbox" name="remember">
                    <label>记住密码</label>
                </div>
                <div id="login">
                    <button type="submit">注册</button>
                </div>
            </form>
        </div>
        <!-- 教务登录结束-->
    </div>
</div>
<div class="bottom">©2018 <a href="javascript:;" target="_blank">关于</a> <span>苏ICP备18027982号</span> <a target="_blank"
                                                                                                       href="http://wpa.qq.com/msgrd?v=3&uin=1315426911&site=qq&menu=yes">联系我们&nbsp;&nbsp;&nbsp;</a>
</div>
<div class="screenbg">
    <ul>
        <li><a href="javascript:;"><img src="../../food/images/0.jpg"></a></li>
        <li><a href="javascript:;"><img src="../../food/images/1.jpg"></a></li>
        <li><a href="javascript:;"><img src="../../food/images/2.jpg"></a></li>
    </ul>
</div>
</body>
<script type="text/javascript" src="../../food/js/jquery1.42.min.js"></script>
<script type="text/javascript" src="../../food/js/jquery.SuperSlide.js"></script>
<!-- <script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script> -->
<script type="text/javascript">
    $(document).ready(function () {
        var $tab_li = $('#tab ul li');
        $tab_li.click(function () {
            $(this).addClass('selected').siblings().removeClass('selected');
            var index = $tab_li.index(this);
            $('div.tab_box > div').eq(index).show().siblings().hide();
        });
    });
</script>

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
<script type="text/javascript">
    function validateForm() {
        var x11 = document.forms["myform"]["username"].value;
        var x22 = document.forms["myform"]["password"].value;
        if (x11 == null || x11 == "输入账号" || x11 == "" || x22 == null || x22 == "" || x22 == "输入密码") {
            alert("请把所有选项填写完整");
            return false;
        }
        return true;
    }

    function validateForm1() {
        var x11 = document.forms["myform1"]["username"].value;
        var x22 = document.forms["myform1"]["password"].value;
        if (x11 == null || x11 == "输入账号" || x11 == "" || x22 == null || x22 == "" || x22 == "输入密码") {
            alert("请把所有选项填写完整");
            return false;
        }
        return true;
    }

    function validateForm2() {
        var x11 = document.forms["myform2"]["username"].value;
        var x22 = document.forms["myform2"]["password"].value;
        if (x11 == null || x11 == "输入账号" || x11 == "" || x22 == null || x22 == "" || x22 == "输入密码") {
            alert("请把所有选项填写完整");
            return false;
        }
        return true;
    }
</script>
</html>
