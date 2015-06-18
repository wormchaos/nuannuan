<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty username}">
    <div id="loginDiv">
        <input id="username" name="username" value="${username}" type="text" placeholder="填入用户名" />
        <input id="password" name="password" value="" type="password" placeholder="填入密码" />
        <input type="button" onclick="javascript:void(0);" value="登录" />
    </div>
</c:if>