<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="/jsp/ErrorPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> Список книг</title>
</head>
<body>
<% request.setCharacterEncoding("UTF-8");
    String name = request.getParameter("name");
    if (name == null)
        throw new IllegalArgumentException("Name expected");
%>
<h1> Список книг читателя <%=name%></h1>
<%@include file="/jsp/ListData.jsp"%>
</body>
</html>