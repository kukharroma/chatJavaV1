<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${contextPath}/styles/mainStyles.css" rel="stylesheet">

<html>
<head>
    <title>Зареєструватись у чаті</title>
</head>

<body>

<form method="post" action="<c:url value='/registration' />">

    <table align="center" id="regForm">
        <tr>
            <td><input type='text' name='name' value='${user.name}' placeholder="Логін" class="input"></td>
        </tr>
        <tr>
            <td><input type='password' name='password' value='${user.password}' placeholder="Пароль" class="input"/>
            </td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="Зареєструватись" class="myButton" style="width: 200"/>
            </td>
        </tr>
        <tr>
            <td>
                <input name="reset" type="reset" value="Очистити" class="myButton" style="width: 200"/>
            </td>
        </tr>
    </table>
</form>
<c:if test="${registered}">
    <table align="center" id="loginForm">
        <tr>
            <td>
                <div class="tdText">Ви зареєструвались. Можете увійти в чат.</div>
            </td>
        </tr>
        <tr align="center">
            <form method="get" action="<c:url value='/login' />">
                <td>
                    <input name="submit" type="submit" value="Увійти" class="myButton" style="width: 200" />
                </td>
            </form>
        </tr>
    </table>
</c:if>


<td><c:if test="${(not empty mapErrors) && (not empty  mapErrors.nameFailed)}">
    <div class="errorblock" align="center">
            ${mapErrors.nameFailed}
    </div>
</c:if></td>


<td><c:if test="${(not empty mapErrors) && (not empty  mapErrors.passwordFailed)}">
    <div class="errorblock" align="center">
            ${mapErrors.passwordFailed}
    </div>
</c:if></td>
</body>


</html>

