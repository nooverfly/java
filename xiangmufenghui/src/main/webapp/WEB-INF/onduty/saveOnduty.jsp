<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- Bootstrap core CSS -->

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
                <a href="#" class="btn btn-primary btn-xs" onclick="formSubmit('saveOnduty','_self');this.blur();"><i class="fa fa-folder"></i> 保存</a>
                <a href="#" class="btn btn-primary btn-xs"  onclick="window.history.go(-1);this.blur();"><i class="fa fa-folder"></i>返回</a>

            <div class="clearfix"></div>

            <div class="row">
                <div class="col-md-12">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>值班表</h2>
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
                            <table class="table table-striped projects" style="width: 98%">
                                <thead>
                                <tr>
                                    <td style="width: 25%">日期/时间</td>
                                    <td>
                                        <input type="text" style="width:120px;" name="ondutyTime"
                                               onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td style="width: 30%">领班负责人</td>
                                    <td><input type="text" name="ondutyLeader" ></td>
                                </tr>
                                <tr>
                                    <td style="width: 30%">值班人数</td>
                                    <td><input type="text" name="ondutyNum" ></td>
                                </tr>
                                <tr>
                                    <td>备注</td>
                                    <td><textarea name="remark" ></textarea></td>
                                </tr>

                                </thead>
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