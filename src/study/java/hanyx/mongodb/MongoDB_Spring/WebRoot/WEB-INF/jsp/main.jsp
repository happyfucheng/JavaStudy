<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
<link rel="stylesheet"
	href="http://yui.yahooapis.com/pure/0.6.0/grids-responsive-min.css">
<title>Spring + MongoDB Demo By Hanyx</title>
</head>
<body>
	<style scoped>
table thead tr th {
	text-align: center;
}

.button-xsmall {
	font-size: 70%;
}

.button-success,.button-error,.button-warning,.button-secondary {
	color: white;
	border-radius: 4px;
	text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
}

.button-success {
	background: rgb(28, 184, 65);
}

.button-error {
	background: rgb(202, 60, 60);
}

.button-warning {
	background: rgb(223, 117, 20);
}

.button-secondary {
	background: rgb(66, 184, 221);
}
</style>

	<div class="main pure-g">
		<div class="pure-u-1-4"></div>
		<div class="pure-u-1-2">
			<div id = "edit-div">
				<form id="edit-form" class="pure-form">
					<fieldset>
						<legend>编辑这个数据:</legend>
						
						<input type="hidden" name="editId">
						<input type="text" name="editName" placeholder="姓名" value="">
						<input type="text" name="editPwd" placeholder="密码" value="">
						
					</fieldset>
				</form>
				<button class="editSubmit button-secondary pure-button">提交</button>
				<button class="editCancel button-warning pure-button">取消</button>
			</div>
			<h3>Person List :</h3>

			<table class="pure-table">
				<thead>
					<tr>
						<th>序号</th>
						<th>姓名</th>
						<th>密码</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${personList}" var="p" varStatus="status">
						<tr
							<c:if test="${status.index%2==0}">class="pure-table-odd"</c:if>>
							<td idVal="${p.id}">${status.index+1}</td>
							<td>${p.name}</td>
							<td>${p.pwd}</td>
							<td>
								<button class="edit button-xsmall button-success pure-button">编辑</button>
								<button class="delete button-xsmall button-error pure-button">删除</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<p></p>
			
			<form id="insert-form" class="pure-form">
				<fieldset>
					<legend>增加一个数据:</legend>
			
					<input type="text" name="personName" placeholder="姓名" value="">
					<input type="text" name="personPwd" placeholder="密码" value="">
					
				</fieldset>
			</form>

			<button class="insert button-secondary pure-button">创建</button>

		</div>
	</div>

</body>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/style/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}";
	
	$(function(){
		$('#edit-div').hide();
	});
	
	$(".delete").click(function() {
		$.post(rootPath + "/delete.ajax", {
			id : $(this).parent().siblings().eq(0).attr("idVal")
		}, function(data) {
			if (data.success) {
				alert("成功!");
				window.location.reload();
			}
		}, "json");
	});
	
	$(".insert").click(function() {
		
		$.post(rootPath + "/insert.ajax", {
			personName:$('#insert-form input').eq(0).val(),
			personPwd:$('#insert-form input').eq(1).val()
		}, function(data) {
			if (data.success) {
				alert("成功!");
				window.location.reload();
			}
		}, "json");
	});
	
	$(".edit").click(function() {
		$("#edit-form input").eq(0).val($(this).parent().siblings().eq(0).attr("idVal"));
		$("#edit-form input").eq(1).val($(this).parent().siblings().eq(1).html());
		$("#edit-form input").eq(2).val($(this).parent().siblings().eq(2).html());
		$("#edit-div").show();
	});
	
	$(".editSubmit").click(function() {
		$('#edit-div').hide();
		
		$.post(rootPath + "/update.ajax", {
			editId:$('#edit-form input').eq(0).val(),
			editName:$('#edit-form input').eq(1).val(),
			editPwd:$('#edit-form input').eq(2).val()
		}, function(data) {
			if (data.success) {
				alert("成功!");
				window.location.reload();
			}
		}, "json");
	});
	
	$(".editCancel").click(function() {
		$('#edit-div').hide();
	});
	
</script>
</html>