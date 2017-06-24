<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- Bootstrap core CSS -->
    <%@ include file="../base.jsp"%>
</head>
<script>
    /* 打开一个新页面：调用时不加第二个参数 add by tony */
    /*'start','_self' */
    function formSubmit (url,sTarget){
        document.forms[0].target = sTarget;    //_self  表示在当前页面进行展现
        document.forms[0].action = url;       //sysadmin/
        document.forms[0].submit();           //表单元素提交
        return true;
    }
</script>

<body>
<form name="icform" method="post">
<div class="right_col" role="main">
    <div class="">
        <ul>
            <a href="#"  class="btn btn-info btn-xs" onclick="formSubmit('updateNotice','_self');this.blur();">更新</a>
            <a href="#" class="btn btn-primary btn-xs"  onclick="window.history.go(-1);this.blur();">返回</a>
        </ul>
        <div class="clearfix"></div>

        <div class="row">
            <div class="col-md-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>公告</h2>
                    </div>
                    <div class="x_content">
                        <!-- start project list -->
                        <table id="ec_table" class="table table-striped projects" width="98%" >
                            <tr hidden="hidden">
                                <td><input type="text" name="noticeId" value="${ noticeId }">${ noticeId }</td>
                            </tr>
                            <tr >
                                <td>公告标题</td>
                                <td><input type="text" name="noticeTitle"  value="${ notice.noticeTitle }"></td>
                            </tr>
                            <tr >
                                <td>创建人</td>
                                <td><input type="text" name="createBy" value="${ notice.createBy }"></td>
                            </tr>

                            <tr >
                                <td>公告信息</td>
                                <td>
                                    <textarea style="height:150px;width:100%" name="noticeMessage" >${notice.noticeMessage}</textarea>
                                </td>
                            </tr>
                            <tr>

                            </tr>
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