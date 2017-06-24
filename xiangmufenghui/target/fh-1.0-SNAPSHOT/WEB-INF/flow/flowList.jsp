<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>

<body>
<div class="right_col" role="main">
	<div class="">

		<div class="clearfix"></div>

		<div class="row">
			<div class="col-md-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>流程列表</h2>
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
								<th>流程ID</th>
								<th>请假人</th>
								<th>请假天数</th>
								<th>请假事由</th>
								<th>请假备注</th>
								<th>请假时间</th>
								<th>请假状态</th>
								<th>操作</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${lbList}" var="d">
							<tr>
								<td>${d.id}</td>
								<td>${d.user.username}</td>
								<td>${d.days}</td>
								<td>${d.content}</td>
								<td>${d.remark}</td>
								<td><fmt:formatDate value="${d.leaveDate}" pattern="yyyy-MM-dd"/></td>
								<td>
									<c:if test="${d.state==0}"><font color="green">初始录入</font></c:if>
									<c:if test="${d.state==1}"><font color="red">开始审批</font></c:if>
									<c:if test="${d.state==2}"><font color="blue">审批完成</font></c:if>
								</td>
								<td>
									<c:if test="${d.state==0}">
										<a href="${ctx}/workflow/startProcess.action?id=${d.id}" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i>申请请假 </a>
										<a href="${ctx}/LBflow/toupdateLB?id=${d.id}" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> 修改 </a>
										<a href="${ctx}/LBflow/deleteLB?id=${d.id}" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> 删除 </a>
									</c:if>
									<c:if test="${d.state==1}">
										<a href="${ctx}/workflow/viewHisComment?id=${d.id}" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> 查看审核记录 </a>
									</c:if>
									<c:if test="${d.state==2}">
										<a href="${ctx}/workflow/viewHisComment?id=${d.id}" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i>查看审核记录 </a>
										<%--<a href="javascript:void(0)" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> 删除 </a>--%>
									</c:if>
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
<script src="${ctx}/staticfile/js/bootstrap.js"></script>
<script src="${ctx}/staticfile/js/custom.min.js"></script>
</body>
</html>

