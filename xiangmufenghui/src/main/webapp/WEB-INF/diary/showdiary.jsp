
<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:include page="../base.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<script>
    function formSubmit (url,sTarget){
        document.forms[0].target = sTarget    //_self  表示在当前页面进行展现
        document.forms[0].action = url;       //sysadmin/dept/start
        document.forms[0].submit();           //表单元素提交
        return true;
    }
</script>
<body>
<form method="post" name="diaryShow">
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            对时间范围为
            <input type="text" style="width:90px;" name="startTime" value="<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
            ~
            <input type="text" style="width:90px;" name="endTime" value="<fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
                <a href="javascript:void(0)" onclick="formSubmit('select','_self');this.blur();" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> 查询</a>
        </div>

        <div class="clearfix"></div>

        <div class="row">
            <div class="col-md-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>日记查询</h2>
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
                            <tr class="odd" hidden="hidden">
                                <td>用户Id</td>
                                <td><input type="text" name="userId" readonly="readonly" value="${sessionScope.session_user.userId}"/></td>
                            </tr>
                            <tr>

                                <th style="width: 20%">序号</th>
                                <th>用户名</th>
                                <th>部门名称</th>
                                <th>录入时间</th>
                                <th>工作内容</th>
                                <th>工作结果</th>
                                <th style="width: 20%">备注</th>
                            </tr>
                            </thead>
                            <tbody class="tableBody" >
                            <c:forEach items="${diaryEntryList}" var="d" varStatus="status">
                                <tr>

                                    <td>${status.index+1}</td>
                                    <td>${d.username}</td>
                                    <td>${d.deptName}</td>
                                    <td><fmt:formatDate value="${d.createTime}" pattern="yyyy-MM-dd"/></td>
                                    <td>${d.workContent}</td>
                                    <td>${d.outcome}</td>
                                    <td>${d.remark}</td>
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
</form>
<script src="${ctx}/staticfile/js/bootstrap.js"></script>
<script src="${ctx}/staticfile/js/fastclick.js"></script>
<script src="${ctx}/staticfile/js/custom.min.js"></script>
</body>
