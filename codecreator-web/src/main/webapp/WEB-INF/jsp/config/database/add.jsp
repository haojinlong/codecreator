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
	$().ready(function() {
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
	});
</script>
<style type="text/css">
label {
	vertical-align: middle;
}

td.label {
	width: 150px;
	text-align: right;
}

td.input, #name, #description {
	width: 350px;
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
					<a href="${pageContext.request.contextPath }/config/database/list">返回数据库列表</a>
				</h4>
			</div>
			<div>
				<form id="newForm"
					action="${pageContext.request.contextPath }/config/database/add/do">
					<table border="1">
						<tr>
							<td colspan="3" align="center"><b>请填写数据库基础信息</b></td>
						</tr>
						<tr>
							<td class="label"><label for="name">数据库名称：</label></td>
							<td class="input"><input id="name" name="name"
								class="required" /></td>
						</tr>
						<tr>
							<td class="label"><label for="description">数据库说明：</label></td>
							<td class="input"><textarea id="description" rows="5"
									name="description"></textarea></td>
						</tr>
						<tr>
							<td class="label"><label for="userList">请选择可访问用户：</label></td>
							<td class="input"><c:forEach items="${userList}" var="user">
									<c:choose>
										<c:when test="${user.id == userProps.userId }">
											<input type="checkbox" name="userList" value="${user.id }"
												checked="checked" readonly="readonly" />${user.fullName } <br />
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="userList" value="${user.id }" />${user.fullName } <br />
										</c:otherwise>
									</c:choose>
								</c:forEach></td>
						</tr>
						<tr>
							<td align="center" colspan="2"><input type="reset" name="重置" />&nbsp;&nbsp;
								<input class="submit" type="submit" name="确定" /></td>
						</tr>
					</table>
				</form>
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