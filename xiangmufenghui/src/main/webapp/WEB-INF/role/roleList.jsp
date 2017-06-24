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
				<a href="#" class="btn btn-primary btn-xs" onclick="formSubmit('roleToview','_self');this.blur();"><i class="fa fa-folder"></i> 查</a>
				<a href="#" class="btn btn-info btn-xs" onclick="formSubmit('roleToupdate','_self');this.blur();"><i class="fa fa-pencil"></i> 改</a>
				<a href="#" class="btn btn-danger btn-xs" onclick="formSubmit('roleDelete','_self');this.blur();"><i class="fa fa-trash-o"></i> 删 </a>
				<a href="#" class="btn btn-primary btn-xs" onclick="formSubmit('roleTocreate','_self');this.blur();" ><i class="fa fa-folder"></i> 增</a>
				<a href="#" class="btn btn-primary btn-xs" onclick="formSubmit('toRoleModule','_self');this.blur();" ><i class="fa fa-folder"></i> 模块</a>
			</div>

			<div class="clearfix"></div>

			<div class="row" STYLE="width: 98%">
				<div class="col-md-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>角色列表</h2>
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
									<td><input type="checkbox" name="selid" onclick="checkAll('deptId',this)"></td>
									<td class="tableHeader">序号</td>
									<td class="tableHeader">角色名称</td>
									<td class="tableHeader">排序号</td>
									<td class="tableHeader">备注信息</td>
								</tr>
								</thead>
								<tbody>
								<c:forEach items="${roleList}" var="r" varStatus="status">
									<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
										<td><input type="checkbox" name="roleId" value="${r.roleId}"/></td>
										<td>${status.index+1}</td>
										<td>${r.name}</td>
										<td>${r.orderNo}</td>
										<td>${r.remarks}</td>
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

