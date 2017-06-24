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
						<li id="save"><a href="#" onclick="formSubmit('update','_self');this.blur();" class="btn btn-primary btn-xs">更新</a></li>
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
					<h2>部门修改</h2>
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
						<tr style="width: 98%">
							<td>部门ID</td>
							<td/>
							<td/>
							<td/>
							<td/>
							<td><input type="text" name="deptId" value="${dept.deptId}"/></td>
							<td/>							<td/>
							<td/>
							<td/>
							<td/>
							<td/>
							<td/>
							<td/>
							<td/>
							<td/>


						</tr>
						</thead>
						<thead>
						<tr>
							<td>部门名称</td>
							<td/>
							<td/>
							<td/>
							<td/>
							<td><input type="text" name="deptName" value="${dept.deptName}"/></td>
						</tr>
						</thead>
						<thead>
						<tr>
							<td>上级部门</td>
							<td/>
							<td/>
							<td/>
							<td/>
							<td>
								<select name="parentDept.deptId" style="width:120px">
									<option value="">---请选择---</option>
									<c:forEach items="${parentList}" var="p">
										<!--排除自己之外  -->
										<c:if test="${dept.deptId !=p.deptId}">

											<!--回显上级部门信息  -->
											<option value="${p.deptId}"
													<c:if test="${dept.parentDept.deptId == p.deptId}">selected="selected"</c:if>
											>${p.deptName}
											</option>

										</c:if>
									</c:forEach>
								</select>
							</td>
						</tr>
						</thead>
						<thead>
						<tr>
							<td>状态</td>
							<td/>
							<td/>
							<td/>
							<td/>
							<td>
								<input type="radio" name="state" value="0" <c:if test="${dept.state ==0}">checked="checked"</c:if>/>停用
								<input type="radio" name="state" value="1" <c:if test="${dept.state ==1}">checked="checked"</c:if>/>启用
							</td>
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

