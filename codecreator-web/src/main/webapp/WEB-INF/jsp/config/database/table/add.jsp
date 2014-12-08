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

		$("#name").rules('add', {
			required : true,
			minlength : 3,
			maxlength : 20,
			remote : "validate",
			messages : {
				remote : "名称已存在"
			}
		});

		$(".column_name").each(function() {
			$(this).rules('add', {
				required : true,
				minlength : 2,
				maxlength : 20,
				messages : {
					required : "请输入列名",
					minlength : "至少2个字符",
					maxlength : "不能超过20个字符"
				}
			});
		});
	}

	function addline() {
		$("#mainData").append($("#appendtr tbody").html());
		setName();
		validate();
	}

	function setName() {
		$("#mainData tr")
				.each(
						function(i) {
							//alert(i + $(this).find(".column_name").val());
							$(this).find(".column_name").attr("name",
									"columnList[" + i + "].name");
							$(this).find(".column_type").attr("name",
									"columnList[" + i + "].typeCode");
							$(this).find(".column_size").attr("name",
									"columnList[" + i + "].size");
							$(this).find(".column_description").attr("name",
									"columnList[" + i + "].description");
							$(this).find("a").attr("href",
									"javascript:del(" + i + ")");
							$(this).find(".column_type").change(function() {
								var type = $(this).val();
								if (type == 5 || type == 6) {
									typeChanage(i, true);
								} else {
									typeChanage(i, false);
								}
							});
						});
	}

	function del(i) {
		$("#mainData tr:eq(" + i + ")").remove();
		setName();
	}

	function typeChanage(i, enabled) {
		if (enabled) {
			$("#mainData tr:eq(" + i + ")").find(".column_size").attr(
					"disabled", false);
			$("#mainData tr:eq(" + i + ")").find(".column_size").val(30);
		} else {
			$("#mainData tr:eq(" + i + ")").find(".column_size").attr(
					"disabled", "disabled");
		}
	}

	$().ready(function() {
		setName();
		validate();
	});
</script>
<style type="text/css">
.column_name, td.name {
	width: 100px;
}

.column_type, td.type, #name {
	width: 100px;
}

.column_size, td.size {
	width: 80px;
}

.column_description, td.description {
	width: 220px;
}

td.del {
	width: 40px;
}

#description {
	width: 260px;
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
			<div>
				<h4>
					<a
						href="${pageContext.request.contextPath }/config/database/view/${dbId}/table/list">返回表列表</a>
				</h4>
			</div>
			<form id="newForm"
				action="${pageContext.request.contextPath }/config/database/update/${dbId}/table/add/do">
				<div>
					<table id="mainTable" border="1">
						<tr>
							<td colspan="5" align="center"><b>请填写表基础信息</b></td>
						</tr>
						<tr>
							<td>表名：</td>
							<td class="type"><input type="text" name="tableInfo.name"
								class="required" id="name" /></td>
							<td>说明：</td>
							<td colspan="2"><input type="text"
								name="tableInfo.description" id="description" /></td>
						</tr>
						<tr>
							<td colspan="5" align="center"><b>请填写列信息</b></td>
						</tr>
						<tr>
							<td class="name">列名</td>
							<td class="type">列类型</td>
							<td class="size">长度</td>
							<td class="description">说明</td>
							<td class="del"></td>
						</tr>
						<tbody id="mainData">
							<tr>
								<td><input class="column_name" type="text"
									readonly="readonly" name="columnList[0].name" value="id"
									size="15" /></td>
								<td><select class="column_type"
									name="columnList[0].typeCode">
										<c:forEach items="${typeList }" var="type">
											<c:if test="${type.text == 'INT' }">
												<option value="${type.code }">${type.text }</option>
											</c:if>
										</c:forEach>
								</select></td>
								<td><input type="text" name="columnList[0].size" value=""
									disabled="disabled" class="column_size" size="10" /></td>
								<td><input type="text" name="columnList[0].description"
									value="唯一标识" readonly="readonly" class="column_description" /></td>
								<td>删除</td>
							</tr>
							<tr>
								<td><input class="column_name" type="text"
									name="columnList[1].name" size="15" /></td>
								<td><select class="column_type"
									name="columnList[1].typeCode">
										<c:forEach items="${typeList }" var="type">
											<option value="${type.code }">${type.text }</option>
										</c:forEach>
								</select></td>
								<td><input type="text" name="columnList[1].size"
									disabled="disabled" class="column_size" /></td>
								<td><input type="text" name="columnList[1].description"
									class="column_description" /></td>
								<td><a href="javascript:del(1)">删除</a></td>
							</tr>
						</tbody>
					</table>
					<table width="98%">
						<tr>
							<td align="center"><input type="button" value="添加列"
								onclick="addline()" />&nbsp;&nbsp;<input type="submit"
								value="确定" /></td>
						</tr>
					</table>
				</div>
			</form>
			<div style="display: none;">
				<table id="appendtr">
					<tbody>
						<tr>
							<td><input class="column_name" type="text"
								name="columnList[1].name" size="15" /></td>
							<td><select class="column_type"
								name="columnList[1].typeCode">
									<c:forEach items="${typeList }" var="type">
										<option value="${type.code }">${type.text }</option>
									</c:forEach>
							</select></td>
							<td><input type="text" name="columnList[1].size"
								disabled="disabled" class="column_size" /></td>
							<td><input type="text" name="columnList[1].description"
								class="column_description" /></td>
							<td><a>删除</a></td>
						</tr>
					</tbody>
				</table>
			</div>

		</div>
		<div id="sidebar">
			<jsp:include page="/WEB-INF/jsp/config/sidebar.jsp" />
		</div>
		<div id="footer">
			<jsp:include page="/footer.jsp" />
		</div>
	</div>
</body>
</html>