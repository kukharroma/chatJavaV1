<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/jsp-header.jsp" %>
<link href="${contextPath}/styles/mainStyles.css" rel="stylesheet">

<html>
<head>
    <title>Увійти у чат</title>

</head>
<body>
<div>
    <form action="<c:url value='/j_spring_security_check' />" method='POST'>

        <table align="center" id="loginForm">
            <tr>

                <td><input type='text' name='j_username' value='' placeholder="Логін" class="input">
                </td>
            </tr>
            <tr>

                <td><input type='password' name='j_password' placeholder="Пароль" class="input"/>
                </td>
            </tr>
            <tr>
                <td><input name="submit" type="submit" value="Увійти" class="myButton" style="width: 150"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input name="reset" type="reset" value="Очистити" class="myButton" style="width: 150"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<c:if test="${not empty error}">
    <div align="center" class="errorblock"> Cпробуйте ще раз <br/></div>
</c:if>
</body>
</html>
