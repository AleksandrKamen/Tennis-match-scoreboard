<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="headers/menu_header.jsp"%>
<%@include file="footer/locale_footer.jsp"%>

<html>
<head>
    <title>newMatch</title>
    <link rel="stylesheet"  href="css/newMatch.css" type="text/css">
</head>
<body>
<div id="menu">
    <h1 style="color: white; font-size: 3vw"><fmt:message key="newMatch.title"></fmt:message></h1>
<form action="new-match" method="post" enctype="application/x-www-form-urlencoded">
    <label for="playerName1"><fmt:message key="newMatch.player1Name"></fmt:message></label>
    <input type="text" id="playerName1" name="playerName1" maxlength="30" required placeholder="<fmt:message key="matches.example"></fmt:message>Novak Djokovic">
    <label for="playerName1"><fmt:message key="newMatch.player2Name"></fmt:message></label>
    <input type="text" id="playerName2" name="playerName2" maxlength="30" required placeholder="<fmt:message key="matches.example"></fmt:message>Rafael Nadal">

    <div class="button">
    <button type="submit" style="font-weight: bold; width: 10vw; height: 2vw; cursor: pointer"><fmt:message key="newMatch.start"></fmt:message></button>
    </div>
</form>

<c:if test="${not empty requestScope.errors}">
    <div style="color: red">
        <c:forEach var="error" items="${requestScope.errors}">
            <samp>${error.message}</samp>
            <br>
        </c:forEach>
    </div>
</c:if>
</div>

</body>
</html>
