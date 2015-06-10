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
        <li type="1">发型</li>
        <li type="2">连衣裙</li>
        <li type="3">外套</li>
        <li type="4">上衣</li>
        <li type="5">下装</li>
        <li type="6">袜子</li>
        <li type="7">鞋子</li>
        <li type="8">饰品</li>
        <li type="9">妆容</li>
    </ul>
</div>
<div>s
    <table id="item_table">
        <tr type = "1">
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
    <c:forEach var="list" items="${itemList}">
        <tr class="item_content">
            <td><c:out value="${list.name}" /></td>
            <td><c:out value="${list.num}" /></td>
            <td><c:out value="${list.level}" /></td>
            <td><c:out value="${list.huali}" /></td>
            <td><c:out value="${list.jianyue}" /></td>
            <td><c:out value="${list.youya}" /></td>
            <td><c:out value="${list.huopo}" /></td>
            <td><c:out value="${list.chengshu}" /></td>
            <td><c:out value="${list.keai}" /></td>
            <td><c:out value="${list.xinggan}" /></td>
            <td><c:out value="${list.qingchun}" /></td>
            <td><c:out value="${list.qingliang}" /></td>
            <td><c:out value="${list.baonuan}" /></td>
            <td><c:out value="${list.label1}" /></td>
            <td><c:out value="${list.label2}" /></td>
            <td><c:out value="${list.getfrom}" /></td>
        </tr>
    </c:forEach>
    </table>
    <div id="pageUp_menu">
        <span class="preview">上一页</span>
        <span class="pageIndex">1</span>
        <span class="nextview">下一页</span>
    </div>
</div>
<!--
<select>
    <option  value="">发型</option>
</select>
<select>
    <option  value="">连衣裙</option>
</select>
<select>
    <option  value="">外套</option>
</select>
<select>
    <option  value="">上衣</option>
</select>
<select>
    <option  value="">下装</option>
</select>
<select>
    <option  value="">袜子</option>
</select>
<select>
    <option  value="">鞋子</option>
</select>
<select>
    <option  value="">饰品</option>
</select>
<select>
    <option  value="">妆容</option>
</select>
-->
</body>
</html>
