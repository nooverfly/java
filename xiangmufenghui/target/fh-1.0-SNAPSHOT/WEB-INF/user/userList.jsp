<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>用户列表</title>
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

<form name="icform" method="post">

<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<a href="javascript:void(0)" onclick="formSubmit('userToview','_self');this.blur();"  class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> 查</a>
			<a href="javascript:void(0)" onclick="formSubmit('userToupdate','_self');this.blur();"  class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> 改</a>
			<a href="javascript:void(0)" onclick="formSubmit('userDelete','_self');this.blur();"  class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> 删 </a>
			<a href="javascript:void(0)" onclick="formSubmit('userTocreate','_self');this.blur();"  class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> 增</a>
			<a href="javascript:void(0)" onclick="formSubmit('userStart','_self');this.blur();"  class="btn btn-info btn-xs"><i class="fa fa-folder"></i>启用</a>
			<a href="javascript:void(0)" onclick="formSubmit('userStop','_self');this.blur();"  class="btn btn-info btn-xs"><i class="fa fa-folder"></i> 停用</a>
			<a href="javascript:void(0)" onclick="formSubmit('userRole','_self');this.blur();"  class="btn btn-info btn-xs"><i class="fa fa-folder"></i> 角色</a>

		</div>

		<div class="clearfix"></div>

		<div class="row" STYLE="width: 98%">
			<div class="col-md-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>员工列表</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
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
								<th><input type="checkbox" name="selid" onclick="checkAll('userId',this)"></th>
								<th>序号</th>
								<th>用户头像</th>
								<th>用户名</th>
								<th>真实姓名</th>
								<th>所属部门</th>
								<th>身份证号</th>
								<th>上级领导</th>
								<th>入职日期</th>
								<th>状态</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${userList}" var="u" varStatus="status">
							<tr>
								<td><input type="checkbox" name="userId" value="${u.userId}"/></td>
								<td>${status.index+1}</td>
								<td >
									<c:if test="${u.headUrl==null}"><img src="../../staticfile/image/head/timg.jpg" height="30" width="30"/></c:if>
									<c:if test="${u.headUrl!=null}"><img src="../../${u.headUrl}" height="30" width="30"/></c:if>

								</td>
								<td>${u.username}</td>
								<td>${u.userInfo.name}</td>
								<td>${u.dept.deptName}</td>
								<td>${u.userInfo.cardNo}</td>
								<td>${u.userInfo.manager.name}</td>
								<td><fmt:formatDate value="${u.userInfo.joinDate}" pattern="yyyy-MM-dd"/></td>
								<td>
									<c:if test="${u.state==1}"><a href="userStop?userId=${u.userId}"><font color="green">启用</font></a></c:if>
									<c:if test="${u.state==0}"><a href="userStart?userId=${u.userId}"><font color="red">停用</font></a></c:if>
								</td>
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
<script src="${ctx}/staticfile/js/custom.min.js"></script>
</body>
</html>

