<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="footer/locale_footer.jsp"%>
<!DOCTYPE html>
<html lang="en">

<html>

<head>
    <title>Tennis-match-scoreboard</title>
    <link rel="stylesheet" href="css/home.css">
</head>
<body>


<div class="menu">
    <h1 style="color: white; font-size: 5vw"><fmt:message key="home.title"></fmt:message> </h1>
    <main>
        <button class="btn" id="newMatch"><a href="new-match"> <fmt:message key="home.newMatch"></fmt:message></a></button>
        <button class="btn" id="matches"><a href="matches?page=1"><fmt:message key="home.matches"></fmt:message></a></button>
    </main>
</div>
</body>

</html>
