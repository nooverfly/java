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

	<div id="menubar">
		<div id="middleMenubar">
			<div id="innerMenubar">
				<div id="navMenubar">
					<ul>
						<li id="update"><a href="#" onclick="formSubmit('updateRole','_self');this.blur();" class="btn btn-primary btn-xs">更新</a></li>
						<li id="back"><a href="#" onclick="window.history.go(-1);this.blur();" class="btn btn-primary btn-xs">返回</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="row" STYLE="width: 98%">
		<div class="col-md-12">
			<div class="x_panel">
				<div class="x_title">
					<h2>角色修改</h2>
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
						<tr hidden="hidden">
							<td><input type="text" name="roleId" value="${role.roleId}"/></td>
						</tr>
						<thead>
						<tr style="width: 98%">
							<td>角色名称</td>
							<td><input type="text" name="name" value="${role.name}"/></td>
						</tr>
						</thead>
						<thead>
						<tr>
							<td>排序号</td>
							<td><input type="text" name="orderNo"value="${role.orderNo}"/></td>
						</tr>
						</thead>
						<thead>
						<tr>
							<td>备注信息</td>
							<td>
								<textarea style="height:120px;width: 500px;" name="remarks">${role.remarks}</textarea>
							</td>
							<td/>
							<td/>
							<td/>

						</tr>
						</thead>

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

