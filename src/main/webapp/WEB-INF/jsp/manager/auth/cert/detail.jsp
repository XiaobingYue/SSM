<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/main.css">
	<link rel="stylesheet" href="${APP_PATH}/css/doc.min.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">众筹平台 - 角色维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<%@include file="/WEB-INF/jsp/common/userinfo.jsp" %>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
                <%@include file="/WEB-INF/jsp/common/menu.jsp" %>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				  <li><a href="${APP_PATH}/manager/main.htm">首页</a></li>
				  <li><a href="${APP_PATH}/manager/role/list.htm">数据列表</a></li>
				  <li class="active">实名认证申请详细</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">申请数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form role="form">
				    <c:forEach items="${mcs}" var="mc">
				        <h1>${mc.certname}</h1>
				        <img src="${APP_PATH}/pics/member/${mc.iconpath}">
				    </c:forEach>
				    <br>
				  <button id="passBtn" type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 同意</button>
				  <button type="refuseButton" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 拒绝</button>
				</form>
			  </div>
			</div>
        </div>
      </div>
    </div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			<h4 class="modal-title" id="myModalLabel">帮助</h4>
		  </div>
		  <div class="modal-body">
			<div class="bs-callout bs-callout-info">
				<h4>测试标题1</h4>
				<p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
			  </div>
			<div class="bs-callout bs-callout-info">
				<h4>测试标题2</h4>
				<p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
			  </div>
		  </div>
		  <!--
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		  </div>
		  -->
		</div>
	  </div>
	</div>
    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/layer/layer.js"></script>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
            });
            $("#refuseButton").click(function(){
            	var loadingIndex = -1;
            	// 提交表单
            	$.ajax({
            		url : "${APP_PATH}/manager/auth_cert/operation.do",
            		type : "POST",
            		data : {
            			"taskid" : "${param.id}",
            			"memberid" : "${param.memberid}",
            			"flg" : "0"
            		},
            		beforeSend : function() {
            			loadingIndex = layer.msg('数据审核中', {icon: 16});
            		},
            		success : function(result) {
            			layer.close(loadingIndex);
            			if ( result.success ) {
            				layer.msg("申请信息审核通过", {time:1000, icon:6}, function(){
            					window.location.href = "${APP_PATH}/manager/auth_cert/list.htm";
            				});
            			} else {
            				layer.msg("申请信息审核失败", {time:1000, icon:5, shift:6});
            			}
            		}
            	});
            });
            $("#passBtn").click(function(){
            	var loadingIndex = -1;
            	// 提交表单
            	$.ajax({
            		url : "${APP_PATH}/manager/auth_cert/operation.do",
            		type : "POST",
            		data : {
            			"taskid" : "${param.id}",
            			"memberid" : "${param.memberid}",
            			"flg" : "1"
            		},
            		beforeSend : function() {
            			loadingIndex = layer.msg('数据审核中', {icon: 16});
            		},
            		success : function(result) {
            			layer.close(loadingIndex);
            			if ( result.success ) {
            				layer.msg("申请信息审核通过", {time:1000, icon:6}, function(){
            					window.location.href = "${APP_PATH}/manager/auth_cert/list.htm";
            				});
            			} else {
            				layer.msg("申请信息审核失败", {time:1000, icon:5, shift:6});
            			}
            		}
            	});
            });
        </script>
  </body>
</html>