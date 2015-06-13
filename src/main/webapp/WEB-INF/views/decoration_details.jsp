<%--
  Created by IntelliJ IDEA.
  User: wormchaos
  Date: 2015/6/7
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>首页</title>
    <link href="/nuannuan/css/styles.css" rel="stylesheet" type="text/css" />
    <script src="/nuannuan/js/jquery-1.8.3.min.js"></script>
    <script src="/nuannuan/js/common.js"></script>
</head>
<body>
<div>
    <ul id="menu">
        <select id="brief">
            <option value="1">简约</option>
            <option value="-1">华丽</option>
            <option value="0">无</option>
        </select>
        <select id="elegance">
            <option value="1">优雅</option>
            <option value="-1">活泼</option>
            <option value="0">无</option>
        </select>
        <select id="lovely">
            <option value="1">可爱</option>
            <option value="-1">成熟</option>
            <option value="0">无</option>
        </select>
        <select id="pure">
            <option value="1">清纯</option>
            <option value="-1">性感</option>
            <option value="0">无</option>
        </select>
        <select id="cool">
            <option value="1">清凉</option>
            <option value="-1">保暖</option>
            <option value="0">无</option>
        </select>
        <input id="search_decoration" type="button" value="查询" />
    </ul>
</div>
<div>
    <table id="item_table">
        <tr type = "1">
            <th>类别</th>
            <th>名称</th>
            <th>No.</th>
            <th>心级</th>
            <th>华丽</th>
            <th>简约</th>
            <th>优雅</th>
            <th>活泼</th>
            <th>成熟</th>
            <th>可爱</th>
            <th>性感</th>
            <th>清纯</th>
            <th>清凉</th>
            <th>保暖</th>
            <th>标签1</th>
            <th>标签2</th>
            <th>获取途径</th>
        </tr>
    </table>
</div>
</body>
</html>
