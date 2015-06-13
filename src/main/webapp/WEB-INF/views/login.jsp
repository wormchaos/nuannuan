<%--
  Created by IntelliJ IDEA.
  User: wormchaos
  Date: 2015/6/7
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        .hide {
            display: none;
        }
        .show {
            color: red;
            font-weight: bold;
        }
        .error {
            color: red;
            font-weight: bold;
        }
    </style>
    <link href="/nuannuan/css/styles.css" rel="stylesheet" type="text/css" />
    <script src="/nuannuan/js/jquery-1.8.3.min.js"></script>
    <script src="/nuannuan/js/common.js"></script>
</head>
<body>
<div id="main">
    <div>
        <form title="登录" id="loginForm" action="login" method="post" class="form-wrapper cf">
            <input id="username" name="username" value="${username}" type="text" placeholder="填入用户名" />
            <input id="password" name="password" value="" type="password" placeholder="填入密码" />
            <input id="passwordConfirm" style="display: none;" name="passwordConfirm" value="" type="password" placeholder="再次填入密码" />
            <input id="referer" name="referer" value="${referer}" type="hidden" />
            <input id="login" type="button" onclick="javascript:void(0);" value="登录" />
            <input id="login_change" style="display: none;" type="button" onclick="javascript:void(0);" value="转到登录" />
            <input id="register_change" type="button" onclick="javascript:void(0);" value="转到注册" />
        </form>
        <div id="error">${error}</div>
    </div>
</div>

</body>
</html>
