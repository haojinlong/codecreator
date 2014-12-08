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
<style type="text/css">
table {
	border-collapse: collapse;
}

.label, .label label {
	width: 120px;
	text-align: right;
}

.var {
	width: 180px;
}

td.idx {
	width: 40px;
}

td.name {
	width: 100px;
}

td.type {
	width: 100px;
}

td.size {
	width: 80px;
}

td.idx_name {
	width: 100px;
}

td.idx_content {
	width: 220px;
}
</style>
<title>代码生成器——郝烦记录之javaEE开发培训系列</title>
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
					&nbsp;&nbsp; <a
						href="${pageContext.request.contextPath }/config/database/update/${dbId}/table/update/${tableId}/to">修改</a>
					&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath }/config/database/update/${dbId}/table/update/${tableId}/idx/to">修改索引</a>	
				</h4>
			</div>
			<div>
				<h4>库表基本信息：</h4>
				<table border="1">
					<tr>
						<td class="label"><label>表名：</label></td>
						<td class="var">${tableInfo.name }</td>
						<td class="label"><label>初始数据库版本：</label></td>
						<td class="var">${tableInfo.createDbVersion }</td>
					</tr>
					<tr>
						<td class="label"><label>说明：</label></td>
						<td colspan="3">${tableInfo.description }</td>
					</tr>
					<tr>
						<td class="label"><label>创建时间：</label></td>
						<td><fmt:formatDate value="${tableInfo.createTime }"
								pattern="yyyy-MM-dd" /></td>
						<td class="label"><label>最后修改时间：</label></td>
						<td><fmt:formatDate value="${tableInfo.updateTime }"
								pattern="yyyy-MM-dd" /></td>
					</tr>
				</table>
			</div>
			<div>
				<h4>列信息</h4>
				<table width="98%" border="1">
					<tr>
						<td class="idx">序号</td>
						<td class="name">列名</td>
						<td class="type">类型</td>
						<td class="size">长度</td>
						<td class="description">说明</td>
					</tr>
					<c:forEach items="${columnList }" var="columnInfo">
						<tr>
							<td>${columnInfo.idx }</td>
							<td>${columnInfo.name }</td>
							<td>${typeMap[columnInfo.typeCode] }</td>
							<td>${columnInfo.size }</td>
							<td>${columnInfo.description }</td>
						</tr>
					</c:forEach>
				</table>
			</div>

			<div>
				<h4>索引信息</h4>
				<table width="98%" border="1">
					<tr>
						<td class="idx_name">索引名</td>
						<td class="idx_content">索引字段名</td>
					</tr>
					<tr>
						<td>primary</td>
						<td>id</td>
					</tr>
					<c:forEach items="${indexList}" var="indexInfo">
						<tr>
							<td>${indexInfo.name }</td>
							<td>${indexInfo.content }</td>
						</tr>
					</c:forEach>
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