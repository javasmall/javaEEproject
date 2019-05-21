<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    文件上传test
</head>
<body>
<form method="post" action="onfile" name="myform" enctype="multipart/form-data">
    选择一个文件：
    <input type="file" name="file"/>
    <br>
    <br>
    <input type="hidden" name="sapid" value="1622120">
    <input type="hidden" name="testname" value="test1">
    <input type="submit" value="上传"/>

</form>
</body>

</html>
