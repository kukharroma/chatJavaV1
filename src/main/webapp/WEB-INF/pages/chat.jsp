<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/jsp-header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${contextPath}/styles/mainStyles.css" rel="stylesheet">
<%! private String user;%>

<script type="text/javascript">
    user = "<%=(String) request.getAttribute("name")%>"
</script>


<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<script type="text/javascript" src="${contextPath}/scripts/libs/underscore-min.js"></script>
<script type="text/javascript" src="${contextPath}/scripts/libs/backbone-min.js"></script>

<script type="text/javascript" src="${contextPath}/scripts/components/users/model/UserItemModel.js"></script>
<script type="text/javascript" src="${contextPath}/scripts/components/users/view/UserCommonView.js"></script>
<script type="text/javascript" src="${contextPath}/scripts/components/users/view/UserItemView.js"></script>

<script type="text/javascript" src="${contextPath}/scripts/components/messages/model/MessageItemModel.js"></script>
<script type="text/javascript" src="${contextPath}/scripts/components/messages/view/MessageCommonView.js"></script>
<script type="text/javascript" src="${contextPath}/scripts/components/messages/view/MessageItemView.js"></script>
<script type="text/javascript" src="${contextPath}/scripts/components/messages/view/SendMessage.js"></script>

<html>
<head>
    <title>Чат</title>
</head>
<body>

<form action="<c:url value='/logout' />" method='GET'>
    <input name="submit" type="submit" value="Вийти" class="myButton" style="width: 150"/>
</form>

<table align="center" class="chatTable">
    <tbody>
    <tr>
        <td class="messageTable">
            <div>
                <div id="messages_list" class="messages_list"></div>
                <div id="messageInputBlock" class="messageInputBlock"></div>
            </div>
        </td>
        <td class="usersTable">
            <div id="users_list" class="users_list"></div>
        </td>
    </tr>
    </tbody>
</table>


<script id="UserItemTmpl" type="text/template">
    <div class="userItem" style="padding-left: 10px;<#if(online){#>
    <#if(user==name){#>
        background: chartreuse;
        color: blue;
    <#}else{#>
        background: chartreuse;
    <#}#>
    <#}else{#> background: darkgray; <#}#>">
        <span><#=name#></span>:
        <span><#if(online){#> Online
            <#}else{#> Offline
                <#}#></span>
    </div>
</script>

<script id="MessageItemTmpl" type="text/template">
    <div>
        <table class="message">
            <tr>
                <td>
                    <span>[<#= formattedCreationTime #>]</span>
                    <span style="
                        <#if(user==sender.name){#>
                            color: blue;
                                <#}else{#>
                            color: red;
                        <#}#>; font-size: 18px  ; "><#= sender.name #></span>:
                </td>
            </tr>
            <tr>
                <td>
                    <span class="textContainer"><#= message #></span>
                </td>
            </tr>
        </table>
    </div>
</script>

<script id="SendMessageTmpl" type="text/html">

    <div>
        <table align="center" style="margin-top: 20">
            <tr>
                <td>
                    <textarea id="messageInput" class="messageInputBlock"
                              placeholder="Введіть повідомлення (Shift + Enter - надіслати)"></textarea>
                </td>
                <td>
                    <button send-message class="myButton">Надіслати</button>
                </td>
            </tr>
        </table>
    </div>
</script>

<script type="text/javascript">
    $(document).ready(function () {
        new UserCommonView()
        new MessagesCommonView()
        new SendMessage()
    })
</script>

</body>
</html>



