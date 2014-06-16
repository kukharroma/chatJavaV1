<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${contextPath}/styles/mainStyles.css" rel="stylesheet">

<html>
<head>
    <title>Чат</title>
</head>
<body>

<table align="center" id="loginForm">
    <tr>
        <form method="get" action="<c:url value='/login' />">
            <td>
                <input name="submit" type="submit" value="Увійти" class="myButton" style="width: 200"/>
            </td>
        </form>
    </tr>
    <tr>
        <form method="get" action="<c:url value='/registration' />">
            <td>
                <input name="submit" type="submit" value="Зареєструватись" class="myButton" style="width: 200"/>
            </td>
        </form>
    </tr>
</table>
</body>
</html>
