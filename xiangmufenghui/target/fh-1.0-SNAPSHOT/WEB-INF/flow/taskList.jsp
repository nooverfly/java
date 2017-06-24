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
						<h2>任务列表</h2>
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
								<th>任务ID</th>
								<th>任务名称</th>
								<th>创建时间</th>
								<th>办理人</th>
								<th>操作</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${taskList}" var="d">
							<tr>
								<td>${d.id}</td>
								<td>${d.name}</td>
								<td><fmt:formatDate value="${d.createTime}" pattern="yyyy-MM-dd"/></td>
								<td>${d.assignee}</td>
								<td>
									<a href="${ctx}/workflow/viewTaskForm?taskId=${d.id}">办理任务</a>
									<a href="javascript:void(0)">查看当前流程图</a>
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

