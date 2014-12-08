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
		$("#mainData tr").each(function(i) {
			$(this).find("a").attr("href", "javascript:del(" + i + ")");
			$(this).find(".column_type").change(function() {
				var type = $(this).val();
				if (type == 5 || type == 6) {
					typeChanage(i, true);
				} else {
					typeChanage(i, false);
				}
			});
		});
		$("#mainData tr.exists").each(
				function(i) {
					//alert(i + $(this).find(".column_name").val());
					$(this).find(".column_idx").attr("name",
							"columnList[" + i + "].idx");
					$(this).find(".column_name").attr("name",
							"columnList[" + i + "].name");
					$(this).find(".column_type").attr("name",
							"columnList[" + i + "].typeCode");
					$(this).find(".column_size").attr("name",
							"columnList[" + i + "].size");
					$(this).find(".column_description").attr("name",
							"columnList[" + i + "].description");
				});
		$("#mainData tr.addition").each(
				function(i) {
					//alert(i + $(this).find(".column_name").val());
					$(this).find(".column_name").attr("name",
							"addColumnList[" + i + "].name");
					$(this).find(".column_type").attr("name",
							"addColumnList[" + i + "].typeCode");
					$(this).find(".column_size").attr("name",
							"addColumnList[" + i + "].size");
					$(this).find(".column_description").attr("name",
							"addColumnList[" + i + "].description");
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
			$("#mainData tr:eq(" + i + ")").find(".column_size").val("");
		}
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

.column_name, td.name, #name {
	width: 120px;
}

.column_type, td.type {
	width: 120px;
}

.column_size, td.size {
	width: 120px;
}

.column_description, td.description {
	width: 180px;
}

td.del {
	width: 40px;
}

#description {
	width: 220px;
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
				action="${pageContext.request.contextPath }/config/database/update/${dbId}/table/update/${tableId}/do">
				<div>
					<h4>库表基本信息：</h4>
					<table border="1">
						<tr>
							<td colspan="5" align="center"><p><b>库表基本信息</b></p></td>
						</tr>
						<tr>
							<td class="label"><label>表名：</label></td>
							<td class="var"><input type="hidden"
								value="${tableInfo.id }" name="tableInfo.id" /><input
								type="text" value="${tableInfo.name }" name="tableInfo.name"
								id="name" /></td>
							<td class="label"><label>数据库说明：</label></td>
							<td class="var" colspan="2"><input type="text"
								value="${tableInfo.description }" name="tableInfo.description"
								id="description" /></td>
						</tr>
						<tr>
							<td class="label"><label>创建时间：</label></td>
							<td><fmt:formatDate value="${tableInfo.createTime }"
									pattern="yyyy-MM-dd" /></td>
							<td class="label"><label>最后修改时间：</label></td>
							<td colspan="2"><fmt:formatDate
									value="${tableInfo.updateTime }" pattern="yyyy-MM-dd hh:mm:ss" /></td>
						</tr>
						<tr><td colspan="5" align="center"><p><b>列信息</b></p></td></tr>
						<tr>
							<td class="name">列名</td>
							<td class="type">列类型</td>
							<td class="size">长度</td>
							<td class="description">说明</td>
							<td class="del"></td>
						</tr>
						<tbody id="mainData">
							<c:forEach items="${columnList }" var="columnInfo"
								varStatus="status">
								<c:choose>
									<c:when test="${columnInfo.name == 'id' }">
										<tr class="exists">
											<td><input class="column_idx" type="hidden"
												name="columnList[${status.index }].idx"
												value="${columnInfo.idx }" /> <input class="column_name"
												type="text" readonly="readonly"
												name="columnList[${status.index}].name" value="id" size="15" /></td>
											<td><select class="column_type"
												name="columnList[${status.index}].typeCode">
													<c:forEach items="${typeList }" var="type">
														<c:if test="${type.text == 'INT' }">
															<option value="${type.code }">${type.text }</option>
														</c:if>
													</c:forEach>
											</select></td>
											<td><input type="text"
												name="columnList[${status.index }].size" value=""
												disabled="disabled" class="column_size" size="10" /></td>
											<td><input type="text"
												name="columnList[${status.index}].description"
												value="${columnInfo.description}" readonly="readonly"
												class="column_description" /></td>
											<td>删除</td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr class="exists">
											<td><input class="column_idx" type="hidden"
												name="columnList[${status.index }].idx"
												value="${columnInfo.idx }" /> <input class="column_name"
												type="text" name="columnList[${status.index}].name"
												size="15" value="${columnInfo.name }" /></td>
											<td><select class="column_type"
												name="columnList[${status.index}].typeCode">
													<c:forEach items="${typeList }" var="type">
														<c:choose>
															<c:when test="${columnInfo.typeCode == type.code}">
																<option value="${type.code }" selected="selected">
																	${type.text }</option>
															</c:when>
															<c:otherwise>
																<option value="${type.code }">${type.text }</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
											</select></td>
											<td><c:choose>
													<c:when
														test="${columnInfo.typeCode == 5 || columnInfo.typeCode == 6}">
														<input type="text" name="columnList[${status.index}].size"
															value="${columnInfo.size}" class="column_size" />
													</c:when>
													<c:otherwise>
														<input type="text" name="columnList[${status.index}].size"
															disabled="disabled" class="column_size" />
													</c:otherwise>
												</c:choose></td>
											<td><input type="text"
												name="columnList[${status.index }].description"
												class="column_description"
												value="${columnInfo.description }" /></td>
											<td><a href="javascript:del(${status.index })">删除</a></td>
										</tr>
									</c:otherwise>
								</c:choose>
							</c:forEach>
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
						<tr class="addition">
							<td><input class="column_name" type="text"
								name="addColumnList[1].name" size="15" /></td>
							<td><select class="column_type"
								name="addColumnList[1].typeCode">
									<c:forEach items="${typeList }" var="type">
										<option value="${type.code }">${type.text }</option>
									</c:forEach>
							</select></td>
							<td><input type="text" name="addColumnList[1].size"
								disabled="disabled" class="column_size" /></td>
							<td><input type="text" name="addColumnList[1].description"
								class="column_description" /></td>
							<td><a>删除</a></td>
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