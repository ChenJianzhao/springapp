<%--
  Created by IntelliJ IDEA.
  User: jzchen
  Date: 2017/2/8 0008
  Time: 下午 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传用户头像</title>
</head>
<body>
    <form method="post" enctype="multipart/form-data" action="/user/upload">
        <input type="text" name="name" />
        <input type="file" name="file" />
        <input type="submit" />
    </form>
</body>
</html>
