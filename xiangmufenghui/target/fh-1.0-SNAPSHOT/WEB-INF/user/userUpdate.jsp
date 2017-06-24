<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>用户列表</title>
</head>
<script type="text/javascript" src="${ctx}/staticfile/js/jquery.min.js"></script>
<script>
    //图片上传预览    IE是用了滤镜。
    function previewImage(file)
    {
        var MAXWIDTH  = 90;
        var MAXHEIGHT = 90;
        var div = document.getElementById('preview');
        if (file.files && file.files[0])
        {
            div.innerHTML ='<img id=imghead onclick=$("#previewImg").click()>';
            var img = document.getElementById('imghead');
            img.onload = function(){
                var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                img.width  =  rect.width;
                img.height =  rect.height;
//                 img.style.marginLeft = rect.left+'px';
                img.style.marginTop = rect.top+'px';
            }
            var reader = new FileReader();
            reader.onload = function(evt){img.src = evt.target.result;}
            reader.readAsDataURL(file.files[0]);
        }
        else //兼容IE
        {
            var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
            file.select();
            var src = document.selection.createRange().text;
            div.innerHTML = '<img id=imghead>';
            var img = document.getElementById('imghead');
            img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
            div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
        }
    }
    function clacImgZoomParam( maxWidth, maxHeight, width, height ){
        var param = {top:0, left:0, width:width, height:height};
        if( width>maxWidth || height>maxHeight ){
            rateWidth = width / maxWidth;
            rateHeight = height / maxHeight;

            if( rateWidth > rateHeight ){
                param.width =  maxWidth;
                param.height = Math.round(height / rateWidth);
            }else{
                param.width = Math.round(width / rateHeight);
                param.height = maxHeight;
            }
        }
        param.left = Math.round((maxWidth - param.width) / 2);
        param.top = Math.round((maxHeight - param.height) / 2);
        return param;
    }
</script>
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
						<li id="save"><a href="#" onclick="formSubmit('userUpdate','_self');this.blur();" class="btn btn-primary btn-xs">更新</a></li>
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
					<h2>用户修改</h2>
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
						<tr class="odd" hidden="hidden">
							<td class="tableHeader"><input type="text" name="userId" value="${user.userId}"/></td>
						</tr>
						<thead>
						<tr style="width: 98%">
							<td class="tableHeader">用户名：</td>
							<td><input type="text"  name="username" value="${user.username}"/></td>

							<td class="tableHeader">密码：</td>
							<td><input type="password"  name="password" value="${user.password}"/></td>

						</tr>
						</thead>
						<thead>
						<tr>
							<td class="tableHeader">所在部门:</td>
							<td>
								<select name="dept.deptId" style="width:120px">
									<option value="">---请选择---</option>

									<c:forEach items="${deptList}" var="d">
										<option value="${d.deptId}" <c:if test="${user.dept.deptId ==d.deptId}">selected="selected"</c:if>>${d.deptName}</option>
									</c:forEach>

								</select>
							</td>

						<tr hidden="hidden">
							<td><input type="text" name="headUrl" value="${user.headUrl}"/></td>
						</tr>

						</tr>
						</thead>
						<thead>
						<tr>
							<td class="tableHeader">真实姓名：</td>
							<td><input type="text" name="userInfo.name" value="${user.userInfo.name}"/></td>

							<td class="tableHeader">身份证号</td>
							<td><input type="text" name="userInfo.cardNo" value="${user.userInfo.cardNo}"/></td>
						</tr>
						</thead>
						<thead>
						<tr>
							<td class="tableHeader">上级领导：</td>
							<td>
								<select name="userInfo.manager.userInfoId" style="width:120px">
									<option>---请选择---</option>
									<c:forEach items="${managerList}" var="m">

										<!--自己不能是自己的领导  -->
										<c:if test="${user.userId!=m.userInfoId}">
											<option value="${m.userInfoId}" <c:if test="${mId==m.userInfoId}">selected="selected"</c:if>>${m.name}</option>
										</c:if>

									</c:forEach>
								</select>
							</td>
							<td class="tableHeader">入职日期</td>
							<td>
								<input type="text" style="width:120px;" name="userInfo.joinDate" value="<fmt:formatDate value="${user.userInfo.joinDate}" pattern="yyyy-MM-dd"/>"
									   onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
							</td>
						</tr>
						</thead>
						<thead>
						<tr>
							<td class="tableHeader">工资：</td>
							<td><input type="text" name="userInfo.salary" value="${user.userInfo.salary}"/></td>

							<td class="tableHeader">生日</td>
							<td>
								<input type="text" style="width:120px;" name="userInfo.birthday" value="<fmt:formatDate value="${user.userInfo.birthday}" pattern="yyyy-MM-dd"/>"
									   onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
							</td>
						</tr>
						</thead>
						<thead>
						<tr>
							<td class="tableHeader">性别：</td>
							<td>
								<input type="radio" name="userInfo.gender" value="男" <c:if test="${user.userInfo.gender=='男'}">checked="checked"</c:if>/>男
								<input type="radio" name="userInfo.gender" value="女" <c:if test="${user.userInfo.gender=='女'}">checked="checked"</c:if>/>女
								<input type="radio" name="userInfo.gender" value="其他" <c:if test="${user.userInfo.gender=='其他'}">checked="checked"</c:if>/>其他
							</td>
							<td class="tableHeader">岗位</td>
							<td>
								<input type="text" name="userInfo.station" value="${user.userInfo.station}"/>
							</td>
						</tr>
						</thead>
						<thead>
						<tr>
							<td class="tableHeader">电话号码：</td>
							<td>
								<input type="text" name="userInfo.telephone" value="${user.userInfo.telephone}"/>
							</td>
							<td class="tableHeader">用户级别</td>
							<td>
								<select name="userInfo.userLevel" style="width:120px">


									<option value="4" <c:if test="${user.userInfo.userLevel==4}">selected="selected"</c:if>>普通用户</option>
									<option value="3" <c:if test="${user.userInfo.userLevel==3}">selected="selected"</c:if>>部门经理</option>
									<option value="2" <c:if test="${user.userInfo.userLevel==2}">selected="selected"</c:if>>副总</option>
									<option value="1" <c:if test="${user.userInfo.userLevel==1}">selected="selected"</c:if>>总经理</option>
								</select>
							</td>
						</tr>
						</thead>
						</thead>
						<thead>
						<tr>
							<td class="tableHeader">排序号：</td>
							<td>
								<input type="text" name="userInfo.orderNo" value="${user.userInfo.orderNo}"/>
							</td>

							<td class="tableHeader">状态</td>
							<td>

								<input type="radio" name="state" value="0" <c:if test="${user.state ==0}">checked="checked"</c:if>/>停用
								<input type="radio" name="state" value="1" <c:if test="${user.state ==1}">checked="checked"</c:if>/>启用
							</td>
						</tr>
						</thead>
						<thead>
						<tr>
							<td class="tableHeader">备注信息:</td>
							<td colspan="3" style="height:80px">
								<textarea style="width: 500px;height:80px" name="userInfo.remark">${user.userInfo.remark}</textarea>
							</td>
						</tr>
						</thead>
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

