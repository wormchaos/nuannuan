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
    <title></title>
</head>
<body>
<ul>
    <li>发型</li>
    <li>连衣裙</li>
    <li>外套</li>
    <li>上衣</li>
    <li>下装</li>
    <li>袜子</li>
    <li>鞋子</li>
    <li>饰品</li>
    <li>妆容</li>
</ul>
<table>
    <tr>
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
    <tr>
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
