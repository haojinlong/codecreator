<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/dev-java.css"
	rel="stylesheet" type="text/css" />
<title>代码生成器——郝烦记录之javaEE开发培训系列</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.validate.messages_zh.js"></script>
<script type="text/javascript">
	function validate() {
		$("#newForm").validate({
			rules : {
				name : {
					required : true,
					minlength : 3,
					maxlength : 20,
					remote : "validate"
				}
			},
			messages : {
				name : {
					remote : "名称已存在"
				}
			}
		});

		$(".idx_content").each(function() {
			$(this).rules('add', {
				minlength : 1,
				messages : {
					minlength : "请选择对应的列",
				}
			});
		});
	}

	function addline() {
		$("#mainData").append($("#appendtr tbody").html());
		setName();
		window.open("choose?contentId=content"
				+ ($("#mainData tr.addition").length-1), "choose",
				"height=500,width=400");
	}

	function setName() {
		$("#mainData tr").each(function(i) {
			$(this).find("a").attr("href", "javascript:del(" + i + ")");
		});
		$("#mainData tr.exists").each(
				function(i) {
					//alert(i + $(this).find(".column_name").val());
					$(this).find(".idx_id").attr("name",
							"indexList[" + i + "].id");
					$(this).find(".idx_name").attr("name",
							"indexList[" + i + "].name");
					$(this).find(".idx_content").attr("name",
							"indexList[" + i + "].content");
				});
		$("#mainData tr.addition").each(
				function(i) {
					//alert(i + $(this).find(".column_name").val());
					$(this).find(".idx_content").attr("name",
							"addIndexList[" + i + "].content");
					$(this).find(".idx_content").attr("id", "content" + i);
				});
	}

	function del(i) {
		$("#mainData tr:eq(" + i + ")").remove();
		setName();
	}

	$().ready(function() {
		setName();
		validate();
	});
</script>
<style type="text/css">
td.label {
	width: 120px;
}

td.id {
	width: 50px;
}

.idx_name, td.name, .idx_choose {
	width: 150px;
}

.idx_content, td.content {
	width: 300px;
}

td.del {
	width: 80px;
}

table {
	border-collapse: collapse;
}
</style>
</head>
<body class="creator">
	<div id="wrapper">
		<div id="header">
			<jsp:include page="/header.jsp" />
		</div>
		<div id="navfirst">
			<jsp:include page="/menu.jsp" />
		</div>


		<div id="navsecond">
			<jsp:include page="/WEB-INF/jsp/config/navsecond.jsp" />
		</div>
		<div id="maincontent">
			<form id="newForm"
				action="${pageContext.request.contextPath }/config/database/update/${dbId}/table/update/${tableId}/idx/do">
				<div>
					<h3>索引信息</h3>
					<table id="mainTable" border="1">
						<tr>
							<td class="id">序号</td>
							<td class="name">索引名</td>
							<td class="content">索引字段名</td>
							<td class="del"></td>
						</tr>
						<tbody id="mainData">
							<tr>
								<td>0</td>
								<td>primary</td>
								<td>id</td>
								<td></td>
							</tr>
							<c:forEach items="${indexList }" var="indexInfo"
								varStatus="status">
								<tr class="exists">
									<td>${status.index+1}</td>
									<td><input class="idx_id" type="hidden"
										name="indexList[${status.index}].id" value="${indexInfo.id }" />
										<input class="idx_name" type="text"
										name="indexList[${status.index}].id" readonly="readonly"
										value="${indexInfo.name }" /></td>
									<td><input class="idx_content" type="text"
										name="indexList[${status.index}].id" readonly="readonly"
										value="${indexInfo.content }" /></td>
									<td><a href="javascript:del(${status.index })">删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<table width="98%">
						<tr>
							<td align="center"><input type="button" value="添加索引"
								onclick="addline()" />&nbsp;&nbsp;<input type="submit"
								value="确定" /></td>
						</tr>
					</table>
				</div>
			</form>
			<div style="display: none;">
				<table id="appendtr">
					<tbody>
						<tr class="addition">
							<td></td>
							<td></td>
							<td><input class="idx_content" type="text"
								name="addIndexList[0].content" readonly="readonly" /></td>
							<td><a href="javascript:delAdd()">删除</a></td>
						</tr>
					</tbody>
				</table>
			</div>

		</div>


	</div>
	<div id="sidebar">
		<jsp:include page="/WEB-INF/jsp/config/sidebar.jsp" />
	</div>
	<div id="footer">
		<jsp:include page="/footer.jsp" />
	</div>
</body>
</html>