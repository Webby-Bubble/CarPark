<%--
  Created by IntelliJ IDEA.
  User: 714780629
  Date: 2022/11/1
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${sessionScope.ds==null}">
<sql:setDataSource driver="org.sqlite.JDBC" url="jdbc:sqlite://C://Users//71478//Documents//park.s3db" var="ds"
scope="session"/>
</c:if>
<c:if test="${param.cmd=='login'}">

</c:if>
<%
  /*当作servlet使用*/
%>
