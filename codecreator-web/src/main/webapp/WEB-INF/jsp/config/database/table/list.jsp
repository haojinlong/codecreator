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
						href="${pageContext.request.contextPath }/config/database/view/${dbId}">返回</a>&nbsp;&nbsp;
					<a
						href="${pageContext.request.contextPath }/config/database/update/${dbId}/table/add/to">添加表</a>
				</h4>
			</div>
			<div>
				<table width="98%" border="1">
					<tr class="title">
						<td align="center">序号</td>
						<td align="center">表名</td>
						<td align="center">说明</td>
						<td align="center">创建时间</td>
						<td align="center">最新修改时间</td>
						<td align="center">删除</td>
					</tr>
					<c:forEach var="tableInfo" items="${tableList}" varStatus="status">
						<tr>
							<td align="center">${status.index+1 }</td>
							<td align="center"><a
								href="${pageContext.request.contextPath}/config/database/view/${dbId}/table/view/${tableInfo.id}">${tableInfo.name }</a></td>
							<td align="center">${tableInfo.description }</td>
							<td align="center"><fmt:formatDate pattern="yyyy-MM-dd"
									value="${tableInfo.createTime }" /></td>
							<td align="center"><fmt:formatDate
									pattern="yyyy-MM-dd hh:mm:ss" value="${tableInfo.updateTime }" /></td>
							<td align="center"><a
								href="${pageContext.request.contextPath}/config/database/update/${dbId}/table/delete/${tableInfo.id}">删除</a></td>
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