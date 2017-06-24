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

    <div class="right_col" role="main">
        <div class="">
            <div class="page-title">
                <a href="#" class="btn btn-primary btn-xs" onclick="formSubmit('toview','_self');this.blur();"><i class="fa fa-folder"></i> 查</a>
                <a href="#" class="btn btn-info btn-xs" onclick="formSubmit('toupdate','_self');this.blur();"><i class="fa fa-pencil"></i> 改</a>
                <a href="#" class="btn btn-danger btn-xs" onclick="formSubmit('delete','_self');this.blur();"><i class="fa fa-trash-o"></i> 删 </a>
                <a href="#" class="btn btn-primary btn-xs" onclick="formSubmit('tocreate','_self');this.blur();" ><i class="fa fa-folder"></i> 增</a>
                <a href="#" class="btn btn-primary btn-xs" onclick="formSubmit('start','_self');this.blur();"><i class="fa fa-folder"></i> 启用</a>
                <a href="#" class="btn btn-primary btn-xs" onclick="formSubmit('stop','_self');this.blur();"><i class="fa fa-folder"></i> 停用</a>
            </div>

            <div class="clearfix"></div>

            <div class="row" STYLE="width: 98%">
                <div class="col-md-12">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>部门列表</h2>
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
                                    <td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('deptId',this)"></td>
                                    <td class="tableHeader">序号</td>
                                    <td class="tableHeader">编号</td>
                                    <td class="tableHeader">上级</td>
                                    <td class="tableHeader">名称</td>
                                    <td class="tableHeader">状态</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${deptList}" var="d" varStatus="status">
                                    <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
                                        <td><input type="checkbox" name="deptId" value="${d.deptId}"/></td>
                                        <td>${status.index+1}</td>
                                        <td>${d.deptId}</td>
                                        <td>${d.parentDept.deptName}</td>
                                        <td><a href="dept/toview?id=${d.deptId}">${d.deptName}</a></td>
                                        <td>
                                            <c:if test="${d.state==1}"><a href="stop?deptId=${d.deptId}"><font color="green">启用</font></a></c:if>
                                            <c:if test="${d.state==0}"><a href="start?deptId=${d.deptId}"><font color="red">停用</font></a></c:if>
                                        </td>
                                    </tr>
                                    <td/>
                                    <td/>
                                    <td/>
                                    <td/>



                                </c:forEach>
                                <td/>
                                <td/>
                                <td/>
                                <td/>

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
<script src="${ctx}/staticfile/js/bootstrap.js"></script>
<script src="${ctx}/staticfile/js/custom.min.js"></script>
</body>
</html>

