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
					<h2>模块查看</h2>
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
							<td class="tableHeader">模块名称：</td>
							<td>${module.name}</td>
						</tr>
						</thead>
						<thead>
						<tr>
							<td class="tableHeader">模块类型: </td>
							<td>${module.ctype}</td>
						</tr>
						</thead>
						<thead>
						<tr>
							<td class="tableHeader">上级模块：</td>
							<td>${module.parentModule.name}</td>
						</tr>
						</thead>
						<thead>
						<tr>
							<td class="tableHeader">排序号</td>
							<td>${module.orderNo}</td>
						</tr>
						</thead>
						<thead>
						<tr>
							<td class="tableHeader">状态</td>
							<td>
								<c:if test="${module.state ==1}">启用</c:if>
								<c:if test="${module.state ==0}">停用</c:if>
							</td>
						</tr>
						</thead>
						<thead>
						<tr>
							<td class="tableHeader">备注信息:</td>
							<td colspan="3" style="height:40px;width: 500px">${module.remark}</td>
							<td/>
							<td/>
							<td/>
							<td/>

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

