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
<body id="cloth">
<input type="hidden" id="action" value="0"/>
<div>
    <ul id="menu" class="menu">
        <li type="1"><a href="javascript:void(0);">发型</a></li>
        <li type="2"><a href="javascript:void(0);">连衣裙</a></li>
        <li type="3"><a href="javascript:void(0);">外套</a></li>
        <li type="4"><a href="javascript:void(0);">上衣</a></li>
        <li type="5"><a href="javascript:void(0);">下装</a></li>
        <li type="6"><a href="javascript:void(0);">袜子</a></li>
        <li type="7"><a href="javascript:void(0);">鞋子</a></li>
        <li type="8"><a href="javascript:void(0);">饰品</a></li>
        <li type="9"><a href="javascript:void(0);">妆容</a></li>
    </ul>
</div>
<div class="menu">
    <label><input class="allCloth" name="allCloth" type="radio" value="0" checked="checked" />全部</label>
    <label><input class="allCloth" name="allCloth" type="radio" value="1" />自己衣柜</label>
    <label><input class="allCloth" name="allCloth" type="radio" value="2" />没加入衣柜</label>
</div>
<div class="content">
    <table id="item_table">
        <tr type = "1">
            <th>名称</th>
            <th>类型</th>
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
        <tr class="item_content">
            <td>-</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
        </tr>
    </table>
    <div class="error"></div>
    <div id="pageUp_menu">
        <span class="preview">上一页</span>
        <span class="pageIndex">1</span>
        <span class="nextview">下一页</span>
    </div>
</div>
<jsp:include page="base.jsp" />
</body>
</html>
