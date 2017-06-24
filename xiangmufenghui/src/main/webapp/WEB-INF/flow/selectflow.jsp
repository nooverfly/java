<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        body{
            background-color: lightgrey;
        }
    </style>
</head>
<body>
<div class="x_content">


    <div class="" role="tabpanel" data-example-id="togglable-tabs">
        <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
            <li role="presentation" class="active"><a href="#tab_content1" id="home-tab" role="tab" data-toggle="tab" aria-expanded="true">请假流程</a>
            </li>
            <li role="presentation" class=""><a href="#tab_content2" role="tab" id="profile-tab" data-toggle="tab" aria-expanded="false">加班流程</a>
            </li>
            <li role="presentation" class=""><a href="#tab_content3" role="tab" id="profile-tab2" data-toggle="tab" aria-expanded="false">调休流程</a>
            </li>
        </ul>
        <div id="myTabContent" class="tab-content">
            <div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="home-tab" style="text-align: center">
                <p>为了规范公司考勤管理制度，严格请假流程，保障公司正常运作，根据国家有关规定并参照公司制定的《公司日常管理制度》，</p>
                <p>结合公司的实际情况，特制定本请假流程，并与公司的《公司日常管理制度》并行，相关处罚规定参见《公司日常管理制度》</p>
                <<img src="${ctx}/staticfile/image/leavebill.png" alt="">
                <p style="text-align: center;font-size: large"><a href="${ctx}/LBflow/toaddqj.action">请假流程</a></p>
            </div>
            <div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="profile-tab">
                <p>加班流程介绍</p>
            </div>
            <div role="tabpanel" class="tab-pane fade" id="tab_content3" aria-labelledby="profile-tab">
                <p>调休流程介绍</p>
            </div>
        </div>
    </div>
</div>

<script src="${ctx}/staticfile/js/bootstrap.js"></script>
<script src="${ctx}/staticfile/js/custom.min.js"></script>
<script src="${ctx}/staticfile/js/messageindex.js"></script>
</body>
</html>
