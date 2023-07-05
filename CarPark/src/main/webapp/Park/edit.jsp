<%--
  Created by IntelliJ IDEA.
  User: 714780629
  Date: 2022/11/3
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html >
<html>
<head>
  <meta charset="UTF-8">

  <link rel="stylesheet" href="../libs/css/edit.css">
  <script type="text/javascript" src="../libs/js/jquery.min.js"></script>
</head>
<body>
  <form action="index" method="post">
    <c:if test="${info==null}">
      <input type="hidden" name="cmd" value="insert">
    </c:if>
    <c:if test="${info!=null}">
      <input type="hidden" name="cmd" value="update">
      <input type="hidden" name="id" value="${info.id}">
    </c:if>
    <div class="inputview">
      <span class="inputspan">
	  <label class="inputtext">车位号:</label>
	  <input class="inputinput" name="code" value="${info.code}" />
	  </span>

    </div>
    <div class="inputbtpanel ">
      <button class="seachbt1" type="submit">保存</button>
    </div>
  </form>
</body>
</html>
