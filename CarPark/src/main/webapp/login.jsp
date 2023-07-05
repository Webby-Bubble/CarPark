<%--
  Created by IntelliJ IDEA.
  User: 714780629
  Date: 2022/11/1
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="User" method="post">
        <input type="hidden" name="cmd" value="login">
        <input name="nike"><br/>
        <input name="pass" type="password"><br/>
        <button type="submit">登录</button>
    </form>
</body>
</html>
