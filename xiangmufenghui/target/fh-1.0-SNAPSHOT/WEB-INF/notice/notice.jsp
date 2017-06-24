<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

</head>
<script>
    /* 打开一个新页面：调用时不加第二个参数 add by tony */
    /*'start','_self' */
    function formSubmit (url,sTarget){
        document.forms[0].target = sTarget;    //_self  表示在当前页面进行展现
        document.forms[0].action = url;       //notice
        document.forms[0].submit();           //表单元素提交
        return true;
    }
</script>

<body>
<form name="icform" method="post">
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <a href="#" class="btn btn-info btn-xs" onclick="formSubmit('toupdateNotice','_self');this.blur();"><i class="fa fa-pencil"></i> 更新公告</a>
            <a href="#" class="btn btn-danger btn-xs" onclick="formSubmit('todeleteNotice','_self');this.blur();"><i class="fa fa-trash-o"></i> 删除公告 </a>
            <a href="#" class="btn btn-primary btn-xs" onclick="formSubmit('tosaveNotice','_self');this.blur();"><i class="fa fa-folder"></i> 新增公告</a>
        </div>
        <div class="clearfix"></div>

        <div class="row">
            <div class="col-md-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>公告</h2>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <form name="icform" method="post" >
                            <input type="text" placeholder="搜索"  name="message">
                            <button onclick="formSubmit('tosearchNotice','_self');this.blur();">搜索</button>
                        </form>

                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="#">设置1</a>
                                    </li>
                                    <li><a href="#">设置2</a>
                                    </li>
                                </ul>
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
                                <th><input type="checkbox"></th>
                                <th style="width: 10%">序号</th>
                                <th style="width: 50%">公告标题</th>
                                <th style="width: 20%">创建时间</th>
                                <th style="width: 20%" >创建人</th>
                            </tr>
                            </thead>
                        <c:forEach items="${ noticeList }" var="n" varStatus="status">
                            <tbody>
                            <tr>
                                <th><input type="checkbox" name="noticeId" value="${ n.noticeId }"></th>
                                <th style="width: 10%" name="id">${ n.id }</th>
                                <th style="width: 50%"><a href="/toViewNotice?noticeId=${n.noticeId}">${ n.noticeTitle }</a> </th>
                                <th style="width: 20%"><fmt:formatDate value="${ n.createTime}" pattern="yyyy-MM-dd"></fmt:formatDate></th>
                                <th style="width: 20%" >${ n.createBy }</th>
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
<script src="js/jquery-1.12.4.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/fastclick.js"></script>
<script src="js/custom.min.js"></script>
</body>
</html>