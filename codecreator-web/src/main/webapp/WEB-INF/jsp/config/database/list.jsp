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
					<a href="${pageContext.request.contextPath }/config/database/add/to">新建数据库</a>
				</h4>
			</div>
			<div>
				<table width="98%" border="1">
					<tr class="title">
						<td align="center">数据库名称</td>
						<td align="center">当前版本</td>
						<td align="center">创建时间</td>
						<td align="center">最新修改时间</td>
						<td align="center">状态</td>
						<td align="center">是否可用</td>
						<td align="center">删除</td>
					</tr>
					<c:forEach var="dbInfo" items="${list}">
						<tr>
							<td align="center"><a
								href="${pageContext.request.contextPath}/config/database/view/${dbInfo.id}">${dbInfo.name }</a></td>
							<td align="center">${dbInfo.version }</td>
							<td align="center"><fmt:formatDate pattern="yyyy-MM-dd"
									value="${dbInfo.createTime }" /></td>
							<td align="center"><fmt:formatDate
									pattern="yyyy-MM-dd hh:mm:ss" value="${dbInfo.updateTime }" /></td>
							<td align="center"><c:choose>
									<c:when test="${dbInfo.locked }">修改中</c:when>
									<c:otherwise>正常</c:otherwise>
								</c:choose></td>
							<td align="center"><c:choose>
									<c:when test="${dbInfo.disabled }">不可用</c:when>
									<c:otherwise>可用</c:otherwise>
								</c:choose></td>
							<td align="center"><a
								href="${pageContext.request.contextPath}/config/database/delete/${dbInfo.id}">删除</a></td>
						</tr>
					</c:forEach>
				</table>
				<p>
					<c:choose>
						<c:when test="${pager.currentPage == 1 }">
							首页&nbsp;&nbsp;上一页
						</c:when>
						<c:otherwise>
							<a
								href="${pageContext.request.contextPath }/config/database/list/1">首页</a>&nbsp;&nbsp;
							<a
								href="${pageContext.request.contextPath }/config/database/list/${pager.previousPage}">上一页</a>
						</c:otherwise>
					</c:choose>
					&nbsp;&nbsp;
					<c:choose>
						<c:when test="${pager.currentPage == pager.maxPage }">
							下一页&nbsp;&nbsp;尾页
						</c:when>
						<c:otherwise>
							<a
								href="${pageContext.request.contextPath }/config/database/list/${pager.nextPage}">下一页</a>&nbsp;&nbsp;
							<a
								href="${pageContext.request.contextPath }/config/database/list/${pager.maxPage}">尾页</a>
						</c:otherwise>
					</c:choose>
				</p>
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