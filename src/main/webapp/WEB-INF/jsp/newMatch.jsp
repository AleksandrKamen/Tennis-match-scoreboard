<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="headers/menu_header.jsp"%>
<%@include file="footer/locale_footer.jsp"%>

<html>
<head>
    <title>newMatch</title>
    <link rel="stylesheet"  href="css/newMatch.css" type="text/css">
</head>
<body>
<div id="menu">
    <h1 style="color: white; font-size: 3vw">Создать новый матч</h1>
<form action="/new-match" method="post" enctype="application/x-www-form-urlencoded">
    <label for="playerName1">Имя первого игрока</label>
    <input type="text" id="playerName1" name="playerName1" maxlength="30" required placeholder="example:Novak Djokovic">
    <label for="playerName1">Имя второго игрока</label>
    <input type="text" id="playerName2" name="playerName2" maxlength="30" required placeholder="example:Rafael Nadal">

    <div class="button">
    <button type="submit" style="font-weight: bold; width: 10vw; height: 2vw">Начать</button>
    </div>
</form>

<c:if test="${not empty requestScope.errors}">
    <div style="color: red">
        <c:forEach var="error" items="${requestScope.errors}">
            <samp>${error.message}</samp>
        </c:forEach>
    </div>
</c:if>
</div>

</body>
</html>
