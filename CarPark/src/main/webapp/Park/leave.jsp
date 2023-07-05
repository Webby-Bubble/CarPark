<%--
  Created by IntelliJ IDEA.
  User: 714780629
  Date: 2022/11/3
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%--<base href="../">--%>
    <meta charset="UTF-8">
    <link href="../libs/layui/css/layui.css" rel="stylesheet">
    <script type="text/javascript" src="../libs/jquery/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="../libs/layui/layui.all.js"></script>
    <script type="text/javascript" src="../libs/js/util.js"></script>
    <script type="text/javascript" src="../libs/js/mylayer.js"></script>
    <link href="../css/css.css" rel="stylesheet">
    <style type="text/css">
        .layui-table{
            margin-left: 30px !important;
        }
    </style>
</head>
<body>
<script type="text/javascript">

</script>
<form action="index">
    <input type="hidden" name="cmd" value="leave">
    <input class="inputinput" name="carcode" value="${carcode}">
    <button class="layui-btn" type="submit">车辆离开</button>
    <label class="inputtext" style="color: red;font-weight: bold;">${error}</label>
</form>

</div>
<div class="layui-form layui-border-box layui-table-view" >
    <div class="layui-table-box">
        <div class="layui-table-body layui-table-main" >
            <table  class="layui-table">
                <thead>
                <tr><td>车牌号</td><td>离开时间</td><td>用时</td><td>金额</td></tr>
                <tbody>
                    <c:forEach items="${list}" var="row">
                    <%--${row.nike}<br/>&lt;%&ndash;从row变量获取getNike返回值&ndash;%&gt;--%>
                        <tr>
                            <td>${row.carcode}</td>
                            <td>${row.date}</td>
                            <td>${row.timestr}</td>
                            <td>${row.amount}</td>
                        </tr>
                    </c:forEach>
            </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
