<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="headers/menu_header.jsp"%>
<%@include file="footer/locale_footer.jsp"%>

<html>
<head>
    <title>Matches</title>
    <link rel="stylesheet" href="css/matches.css">
</head>

<body>
<div style="text-align: center; font-size: 3vw">
    <h1>Завершенные матчи</h1>
</div>

<div class="search">
<form action="/matches" method="get" enctype="text/plain">
    <label for="search"> Поиск матча по игроку:
        <input type="text" name="filter_by_player_name" id="search" maxlength="30" required placeholder="example:Novak Djokovic">
    </label>
    <button type="submit" style="cursor: pointer">Поиск</button>
   <a href="/matches"><button type="button" style="cursor: pointer">Сбросить</button></a>
</form>
</div>

<table id="matches">
    <tr>
        <th>Матч №</th>
        <th>Игрок №1</th>
        <th>Игрок №2</th>
    </tr>
<c:forEach var="matches" items="${requestScope.matches}">
    <tr>
        <td>${matches.id}</td>
        <td>${matches.player1}</td>
        <td>${matches.player2}</td>
    </tr>
</c:forEach>
    </table>

<div class="page">

    <c:choose>
     <c:when test="${not empty requestScope.filter_by_player_name}">
         <a href="/matches?filter_by_player_name=${requestScope.filter_by_player_name}&page=${requestScope.page==1?1:requestScope.page-1}">
             <img src="/css/picture/left.png" id="left"></a>
     </c:when>
        <c:otherwise>
            <a href="/matches?page=${requestScope.page==1?1:requestScope.page-1}"><img src="/css/picture/left.png" id="left2"></a>
        </c:otherwise>
    </c:choose>

     <code id="txt">${requestScope.page}</code>

  <c:choose>
      <c:when test="${not empty requestScope.filter_by_player_name}">
          <a href="/matches?filter_by_player_name=${requestScope.filter_by_player_name}&page=${requestScope.page < requestScope.lastPage?requestScope.page+1:requestScope.lastPage}">
              <img src="/css/picture/right.png" id="right"></a>
      </c:when>

      <c:otherwise>
        <a href="/matches?page=${requestScope.page < requestScope.lastPage?requestScope.page+1:requestScope.lastPage}"><img src="/css/picture/right.png" id="right2"> </a>
      </c:otherwise>
  </c:choose>
</div>

</body>

</html>
