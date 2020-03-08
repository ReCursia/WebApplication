<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="ErrorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Список песен</title>
    <link rel="stylesheet" type="text/css" href="styles/style.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.1/build/pure-min.css"
          integrity="sha384-oAOxQR6DkCoMliIh8yFnu25d7Eq/PHS21PClpwjOTeU2jRSq11vu66rf90/cZr47" crossorigin="anonymous">
</head>
<body>
<% request.setCharacterEncoding("UTF-8");
    String artist = request.getParameter("artist");
    if (artist == null)
        throw new IllegalArgumentException("Artist name expected");
%>
<h1> Песни <%=artist%>
</h1>
<table class="pure-table">
    <tr>
        <th><b>Название</b></th>
        <th><b>Альбом</b></th>
        <th><b>Год выпуска</b></th>
        <th><b>Артист</b></th>
        <th><b>Жанр</b></th>
        <th><b>Слушать</b></th>
    </tr>
    <%@include file="ListData.jsp" %>
</table>
</body>
</html>