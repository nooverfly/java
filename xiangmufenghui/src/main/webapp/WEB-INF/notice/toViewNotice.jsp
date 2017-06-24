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

<body>
<div class="right_col" role="main">
    <div class="">
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
                                <td ><input type="text" name="noticeId" value="${ notice.noticeId }">${ notice.noticeId }</td>
                            </tr>

                            <tr >
                                <td>公告标题</td>
                                <td>${ notice.noticeTitle }</td>
                            </tr>
                            <tr >
                                <td>创建人</td>
                                <td>${ notice.createBy }</td>
                            </tr>
                            <tr >
                                <td>创建时间</td>
                                <td><fmt:formatDate value="${ notice.createTime }" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                            </tr>

                            <tr class="odd">
                                <td>公告信息</td>
                                <td>
                                    <textarea style="height:120px;width:100%" name="noticeMessage" readonly="readonly">${notice.noticeMessage}</textarea>
                                </td>
                            </tr>

                            </table>

                        <!-- end project list -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="js/jquery-1.12.4.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/fastclick.js"></script>
<script src="js/custom.min.js"></script>
</body>
</html>