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
						<li id="save"><a href="#" onclick="formSubmit('saveModule','_self');this.blur();" class="btn btn-primary btn-xs">保存</a></li>
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
					<h2>模块新增</h2>
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
							<td>模块名称</td>
							<td><input type="text" name="name"/></td>

						</tr>
						</thead>
						<thead>
						<tr>
							<td>模块类型</td>
							<td>
								<select name="ctype" style="width:121px">
									<option value="">---请选择---</option>
									<option value="1">主菜单</option>
									<option value="2">左侧菜单</option>
									<option value="3">按钮</option>
								</select>
							</td>
						</tr>
						</thead>
						<thead>
						<tr>
							<td>上级模块</td>
							<td>
								<select name="parentModule.moduleId" style="width:121px">
									<option value="">---请选择---</option>
									<option value="">无上级</option>

									<c:forEach items="${moduleList}" var="m">
										<option value="${m.moduleId}">${m.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						</thead>
						<thead>
						<tr>
							<td>排序号</td>
							<td><input type="text" name="orderNo"/></td>
						</tr>
						</thead>
						<thead>
						<tr>
							<td>状态</td>
							<td>
								<input type="radio" name="state" value="1"/>启用
								<input type="radio" name="state" value="0"/>停用
							</td>
						</tr>
						</thead>
						<thead>
						<tr>
							<td>备注信息</td>
							<td>
								<textarea style="height:120px;width: 500px;" name="remark"></textarea>
							</td>
						</tr>
						</thead>
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

