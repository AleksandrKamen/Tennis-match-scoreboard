<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="headers/menu_header.jsp"%>
<%@include file="footer/locale_footer.jsp"%>

<html>
<head>
    <title>newMatch</title>
    <link rel="stylesheet"  href="css/newMatch.css" type="text/css">
</head>
<body>
<div id="menu">
    <h1>Создать новый матч</h1>
<form action="/new-match" method="post" enctype="application/x-www-form-urlencoded">
    <label for="playerName1">Имя первого игрока</label>
    <input type="text" id="playerName1" name="playerName1" maxlength="30" required placeholder="example:Novak Djokovic">
    <label for="playerName1">Имя второго игрока</label>
    <input type="text" id="playerName2" name="playerName2" maxlength="30" required placeholder="example:Rafael Nadal">

    <div class="button">
    <button type="submit">Начать</button>
    </div>

</form>
</div>

<%--<c:if test="${not empty requestScope.errors}">--%>
<%--    <div class="error">--%>
<%--        <c:forEach var="error" items="${requestScope.errors}">--%>
<%--            <samp>${error.message}</samp>--%>
<%--        </c:forEach>--%>
<%--    </div>--%>
<%--</c:if>--%>


</body>
</html>
