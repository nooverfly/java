<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>用户列表</title>
</head>

<body>




<div align="center">
    <div class="textbox-title">
        信息列表
    </div>
    <c:forEach var="m" items="${messageList}">

        <hr  >
        <table  align="center">
            <tr>
                <td rowspan="2">
                    <img src="${ctx}/getHeadImg?url=${messageUser.headurl}" width="80px" href="80px">
                </td>
                <td><a href="#" style="color: #2e6da4;font-size:20px;text-decoration: none "   >${m.fromName}</a> : ${m.text}

                </td>
            </tr>
            <tr>
                <td> <small style="color: gray"> <fmt:formatDate value="${m.date}" pattern="MM月dd日 HH:mm:ss"/> </small>==========<a href="#" style="color:#d43f3a">删除</a>  | <a href="#" style="color: #31b0d5" >回复</a>
                </td>
            </tr>
        </table>
    </c:forEach>


</div>



</body>
</html>