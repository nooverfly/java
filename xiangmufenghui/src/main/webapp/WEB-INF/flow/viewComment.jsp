<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
<div class="page-title">
    <div class="title_left">
        <h3>请假任务办理</h3>
    </div>
</div>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>请假申请的任务办理</h2>
                <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li>
                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <br />
                <form data-parsley-validate class="form-horizontal form-label-left">

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">请假天数 <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="text" name="days" required="required" class="form-control col-md-7 col-xs-12"
                            value="${leaveBill.days}" readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">请假原因 <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="text" name="content" required="required" class="form-control col-md-7 col-xs-12"
                            value="${leaveBill.content}" readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">请假备注 <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <textarea name="remark" class="form-control col-md-7 col-xs-12" required="required" readonly="readonly">${leaveBill.remark}</textarea>
                        </div>
                    </div>
                    <div class="ln_solid">
                    <div class="form-group">
                        <button class="btn btn-success" onclick="window.history.go(-1);">返回</button>
                    </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<hr/>
<c:if test="${commentList!=null && commentList.size()>0}">
<div class="row">
    <div class="col-md-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>显示请假申请的批注信息</h2>
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
                        <th>时间</th>
                        <th>批注人</th>
                        <th>批注信息</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${commentList}" var="d">
                        <tr>
                            <td><fmt:formatDate value="${d.time}" pattern="yyyy-MM-dd"/></td>
                            <td>${d.userId}</td>
                            <td>${d.fullMessage}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <!-- end project list -->

            </div>
        </div>
    </div>
</div>
</c:if>
<c:if test="${commentList==null || commentList.size()==0}">
    <div class="row">
        <div class="col-md-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>暂时没有批注信息</h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
</c:if>
</body>
</html>
