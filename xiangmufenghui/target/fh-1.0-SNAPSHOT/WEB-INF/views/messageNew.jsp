<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/5
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>实时信息更新平台</title>
</head>

<c:forEach items="${messageNew}" var="m">
  <font color="#dc143c" size="8px">  ${m.fromName}给你发送了${m.number}条信息,请及时查看! </font><hr/>

</c:forEach>


</body>
</html>
