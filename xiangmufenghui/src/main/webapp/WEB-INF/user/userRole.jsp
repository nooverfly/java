<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>用户角色分配</title>
	<link rel="stylesheet" href="${ctx}/staticfile/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx}/staticfile/zTree/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx}/staticfile/zTree/js/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="${ctx}/staticfile/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
</head>

<script type="text/javascript">
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
    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };

    //这里相当于在拿后来传来的数据，后台在传数据的时候，属性名要对应上
    var zNodes =${zTreeJSON}

        $(document).ready(function(){
            $.fn.zTree.init($("#htZtree"), setting, zNodes);

            var zTreeObj = $.fn.zTree.getZTreeObj("htZtree");
            zTreeObj.expandAll(true);		//展开所有树节点
        });


    //获取到所以用户选中的节点id值
    //获取所有选择的节点，提交时调用下面函数
    function submitCheckedNodes(treeNode) {
        var nodes = new Array();
        var zTreeObj = $.fn.zTree.getZTreeObj("htZtree");
        nodes = zTreeObj.getCheckedNodes(true);		//取得选中的结点
        var str = "";
        for (i = 0; i < nodes.length; i++) {
            if (str != "") {
                str += ",";
            }
            str += nodes[i].id;
        }

        $('#roleIds').val(str);		//将拼接完成的字符串放入隐藏域，这样就可以通过post提交
    }
</script>

<body>

<form name="icform" method="post">

	<div class="right_col" role="main">
		<div class="">
			<div class="page-title">
				<li id="save"><a href="#" onclick="submitCheckedNodes();formSubmit('saveUserRole','_self');this.blur();" class="btn btn-primary btn-xs">保存</a></li>
				<li id="back"><a href="#" onclick="window.history.go(-1);this.blur();" class="btn btn-primary btn-xs">返回</a></li>
			</div>

			<div class="clearfix"></div>

			<div class="row" STYLE="width: 98%">
				<div class="col-md-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>员工角色分配</h2>
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
								<!--隐藏域用来传递数据  -->
								<input type="hidden" id="userId" name="userId" value="${userId}"/>
								<input type="hidden" id="roleIds" name="roleIds"/>
								<ul id="htZtree" class="ztree"></ul>
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

