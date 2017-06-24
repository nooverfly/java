
<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:include page="../base.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- Bootstrap core CSS -->
    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="${ctx}/css/custom.min.css" rel="stylesheet">
</head>
<body>
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <td>根据用户名查询</td>
            <td>
                <select name="userId" style="width:120px">
                    <option value="">----请选择-----</option>
                    <c:forEach items="${userList }" var="l">
                        <option value="${l.userId }">${l.username }</option>
                    </c:forEach>
                </select>
                <a href="javascript:void(0)" id="getuserId" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> 查询</a>
        </div>

        <div class="clearfix"></div>

        <div class="row">
            <div class="col-md-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>登陆日志</h2>
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="#">Settings 1</a>
                                    </li>
                                    <li><a href="#">Settings 2</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a class="close-link"><i class="fa fa-close"></i></a>
                            </li>
                        </ul>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">

                        <!-- start project list -->
                        <table class="table table-striped projects">
                            <thead>
                            <tr>

                                <th style="width: 20%">序号</th>
                                <th>用户名</th>
                                <th>登陆时间</th>
                                <th>注销时间</th>
                            </tr>
                            </thead>
                            <tbody class="tableBody" >
                            <c:forEach items="${logInfoList}" var="u" varStatus="status">
                            <tr>

                                <td>${status.index+1}</td>
                                <td>${u.username}</td>
                                <td><fmt:formatDate value="${u.loginTime}" pattern="yyyy-MM-dd"/></td>
                                <td><fmt:formatDate value="${u.logoutTime}" pattern="yyyy-MM-dd"/></td>

                            </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                        <!-- end project list -->

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/js/jquery-1.12.4.js"></script>
<script src="${ctx}/js/bootstrap.js"></script>
<script src="${ctx}/js/fastclick.js"></script>
<script src="${ctx}/js/custom.min.js"></script>
<script>
    $(function () {
        $("#getuserId").click(function () {
            var userId = $("select[name='userId']").val();
            location.href="/logInfo/select?userId="+userId;
        })
    })
</script>
</body>
</html>