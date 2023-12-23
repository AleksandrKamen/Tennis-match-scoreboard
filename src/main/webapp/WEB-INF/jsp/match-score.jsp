<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="headers/menu_header.jsp" %>
<%@include file="footer/locale_footer.jsp" %>
<html>
<head>
    <title>Match-Score</title>
    <link rel="stylesheet" href="css/match-score.css">
</head>
<body>

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
                <form action="match-score?uuid=${match.getUuid()}" method="post">
                    <button type="submit" id="b1" onclick="disableButton(this, b2)"><fmt:message
                            key="match-score.player1"></fmt:message></button>
                    <input type="hidden" name="player" value="0">
                </form>
            </div>
        </td>
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
                <form action="match-score?uuid=${match.getUuid()}" method="post">
                    <button type="submit" id="b2" onclick="disableButton(this, b1)"><fmt:message
                            key="match-score.player2"></fmt:message></button>
                    <input type="hidden" name="player" value="1">
                </form>
            </div>
        </td>
    </tr>
</table>
</body>
</html>
<script>
    function disableButton(button, button2) {
        button.innerHTML = 'Sending...';
        button.form.submit();
        button.disabled = true;
        button2.disabled = true;
    }
</script>
