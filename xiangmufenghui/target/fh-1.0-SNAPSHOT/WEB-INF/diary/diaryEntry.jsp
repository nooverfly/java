
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
<script>
    function formSubmit (url,sTarget){
        document.forms[0].target = sTarget    //_self  表示在当前页面进行展现
        document.forms[0].action = url;       //sysadmin/dept/start
        document.forms[0].submit();           //表单元素提交
        return true;
    }
    /* 全选*/
    function checkAll(who, obj){
        var curCheckBox = document.getElementsByName(who);
        for(i = 0; i < curCheckBox.length; i++){
            curCheckBox.item(i).checked = obj.checked;
        }
    }
</script>
<body>
<form method="post" name="loginForm">
<div class="right_col" role="main">
    <div class="">
        <div class="page-title" align="center">
            <td><a href="#" class="btn btn-danger btn-xs" onclick="formSubmit('save','_self');this.blur();"><i class="fa fa-trash-o"></i> 保存</a></td>
            <td><a href="#" class="btn btn-primary btn-xs" onclick="window.history.go(-1);this.blur();"><i class="fa fa-folder"></i> 返回</a></td>
        </div>

        <div class="clearfix"></div>

        <div class="row">
            <div class="col-md-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>日记录入</h2>
                        <div class="msgtip" align="center">
                            <c:if test="${!empty errorInfo}">
                                <font color="red"> ${errorInfo}</font>
                            </c:if>
                        </div>
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
                            <tr class="odd" hidden="hidden">
                                <td>用户Id</td>
                                <td><input type="text" name="userId" readonly="readonly" value="${sessionScope.session_user.userId}"/></td>
                            </tr>
                            <tr class="odd">
                                <td>姓名</td>
                                <td><input type="text" name="username" readonly="readonly" value="${sessionScope.session_user.username}"/></td>
                            </tr>
                            </tr>
                            <tr class="odd">
                                <td>部门</td>
                                <td><input type="text" name="deptName" readonly="true"value="${sessionScope.session_user.dept.deptName}"></td>
                            </tr>
                            <tr class="odd">
                                <td>工作内容</td>
                                <td>
                                    <textarea style="height:150px;width:100%" name="workContent"></textarea>
                                </td>
                            </tr>
                            <tr class="odd">
                                <td>工作结果</td>
                                <td>
                                    <textarea style="height:150px;width:100%" name="outcome"></textarea>
                                </td>
                            </tr>
                            <tr class="odd">
                                <td>备注信息</td>
                                <td>
                                    <textarea style="height:150px;width:100%" name="remark"></textarea>
                                </td>
                            </tr>

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
<script src="${ctx}/js/jquery-1.12.4.js"></script>
<script src="${ctx}/js/bootstrap.js"></script>
<script src="${ctx}/js/fastclick.js"></script>
<script src="${ctx}/js/custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/WdatePicker.js"></script>
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