<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="headers/menu_header.jsp"%>
<%@include file="footer/locale_footer.jsp"%>
<html>
<head>
    <title>Match-Score</title>
    <link rel="stylesheet" href="css/match-score.css">
</head>
<body>
<c:if test="${not empty requestScope.winner}">
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
        <a href="new-match"> <button type="button"><fmt:message key="match-score.newMatchButton"></fmt:message></button></a>
        <span style="margin: 0 30px;"></span>
        <a href="matches"> <button type="button"><fmt:message key="home.matches"></fmt:message></button></a>
    </div>
</c:if>


<c:if test="${empty requestScope.winner}">
<div class="header">
    <h1><fmt:message key="match-score.title"></fmt:message></h1>
</div>

<table>
    <tr>
        <th>Set 1</th>
        <th>Set 2</th>
        <th>Set 3</th>
        <th>Sets</th>
        <th>Game</th>
        <th>Point</th>
        <th><fmt:message key="match-score.player"></fmt:message></th>
        <th><fmt:message key="match-score.point"></fmt:message></th>
    </tr>
    <tr>
        <td class="score">${match.getScore().getCurrentPlayerScore(0).get(0)}</td>
        <td class="score">${match.getScore().getCurrentPlayerScore(0).get(1)}</td>
        <td class="score">${match.getScore().getCurrentPlayerScore(0).get(2)}</td>
        <td class="score">${match.getScore().getCurrentPlayerScore(0).get(3)}</td>
        <td class="score">${match.getScore().getCurrentPlayerScore(0).get(4)}</td>
        <td class="score">${match.getScore().getCurrentPlayerScore(0).get(5)}</td>
        <td class="player">${match.getPlayer1().getName()}</td>
        <td>
            <div class="buttons">
                <form action="/match-score" method="post">
                    <button type="submit" name="player" value="0"><fmt:message key="match-score.player1"></fmt:message></button>
                </form>
        </div></td>
    </tr>
    <tr>
        <td class="score">${match.getScore().getCurrentPlayerScore(1).get(0)}</td>
        <td class="score">${match.getScore().getCurrentPlayerScore(1).get(1)}</td>
        <td class="score">${match.getScore().getCurrentPlayerScore(1).get(2)}</td>
        <td class="score">${match.getScore().getCurrentPlayerScore(1).get(3)}</td>
        <td class="score">${match.getScore().getCurrentPlayerScore(1).get(4)}</td>
        <td class="score">${match.getScore().getCurrentPlayerScore(1).get(5)}</td>
        <td class="player">${match.getPlayer2().getName()}</td>
        <td>
            <div class="buttons">
             <form action="/match-score" method="post">
                 <button type="submit" name="player" value="1"><fmt:message key="match-score.player2"></fmt:message></button>
             </form>
            </div>
        </td>
    </tr>
</table>
</c:if>

</body>
</html>
