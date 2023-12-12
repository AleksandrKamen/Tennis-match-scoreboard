<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <h1>Матч завершен - победил ${requestScope.winner}</h1>
    </div>

<table>
    <tr>
        <th>Игрок</th>
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

    <div class="matches">
         <a href="matches"><button type="button">Завершенные матчи</button> </a>
    </div>

</c:if>


<c:if test="${empty requestScope.winner}">
<div class="header">
    <h1>Текущий матч</h1>
</div>

<table>
    <tr>
        <th>Set 1</th>
        <th>Set 2</th>
        <th>Set 3</th>
        <th>Sets</th>
        <th>Game</th>
        <th>Point</th>
        <th>Игрок</th>
        <th>Увеличить счет</th>
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
                    <button type="submit" name="player" value="0">Игрок 1</button>
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
                 <button type="submit" name="player" value="1">Игрок 2</button>
             </form>
            </div>
        </td>
    </tr>
</table>
</c:if>

</body>
</html>
