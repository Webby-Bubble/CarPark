<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="pojo.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 714780629
  Date: 2022/11/1
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<sql:setDataSource driver="org.sqlite.JDBC" url="jdbc:sqlite://C://Users//71478//Documents//park.s3db" var="ds"/>
<sql:query var="data" dataSource="${ds}">
    select * from user;
</sql:query>
<table>
    <tr><td>名称</td></tr>
    <c:forEach items="${data.rows}" var="row">
        <tr><td>${row.nike}</td></tr>
    </c:forEach>>
    <%--<%
        List<User> list = (List<User>)request.getAttribute("list");
        for(User u:list){
            %>
            <tr><td><%=u.getNike()%></td></tr>
            <%
        }
    %>--%>
</table>

<%--<%
    for(int i=0;i<100;i++){
        out.write("a"+i+"<br/>");
    }
%>--%>
</body>
</html>
