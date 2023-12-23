<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="headers/menu_header.jsp" %>
<%@include file="footer/locale_footer.jsp" %>
<html>
<head>
    <title>MatchEnd</title>
    <link rel="stylesheet" href="css/match-score.css">
</head>
<body>
<div class="header" id="header1">
    <h1><fmt:message key="match-score.playerWon"></fmt:message> ${requestScope.winner}</h1>
</div>

<table>
    <tr>
        <th><fmt:message key="match-score.player"></fmt:message></th>
        <th>Sets</th>
    </tr>
    <tr>
        <td>${requestScope.player1}</td>
        <td>${requestScope.playerScore1}</td>
    </tr>
    <tr>
        <td>${requestScope.player2}</td>
        <td>${requestScope.playerScore2}</td>
    </tr>
</table>

<div class="endButton">
    <a href="new-match">
        <button type="button"><fmt:message key="match-score.newMatchButton"></fmt:message></button>
    </a>
    <span style="margin: 0 30px;"></span>
    <a href="matches">
        <button type="button"><fmt:message key="home.matches"></fmt:message></button>
    </a>
</div>
</body>
</html>
