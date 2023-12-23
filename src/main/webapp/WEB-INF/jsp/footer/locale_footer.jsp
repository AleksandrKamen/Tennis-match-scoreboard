<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <link rel="stylesheet" href="css/footer.css">
</head>
<div id="locale">
    <form action="locale" method="post">
        <div class="buttonsFooter">
            <button type="submit" name="lang" value="en_US" style="cursor: pointer">EN</button>
            <button type="submit" name="lang" value="ru_RU" style="cursor: pointer">RU</button>
        </div>
    </form>
    <fmt:setLocale value="${sessionScope.lang!=null?sessionScope.lang:'en_US'}"/>
    <fmt:setBundle basename="translations"/>
</div>

