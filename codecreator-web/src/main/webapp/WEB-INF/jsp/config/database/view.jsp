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
					<a href="${pageContext.request.contextPath }/config/database/list">返回数据库列表</a>
					&nbsp;&nbsp; <a
						href="${pageContext.request.contextPath }/config/database/view/${dbInfo.id}/table/list">查看库表信息</a>
				</h4>
			</div>
			<div>
				<h3>数据库基本信息：</h3>
				<table width="95%" border="1">
					<tr>
						<td><label>数据库名称：</label></td>
						<td>${dbInfo.name }</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td><label>数据库说明：</label></td>
						<td>${dbInfo.description }</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td><label>数据库密码：</label></td>
						<td>${dbInfo.dbKey }</td>
						<td>注意：用于客户端生成</td>
					</tr>
					<tr>
						<td><label>创建时间：</label></td>
						<td><fmt:formatDate value="${dbInfo.createTime }"
								pattern="yyyy-MM-dd" /></td>
						<td></td>
					</tr>
					<tr>
						<td><label>当前版本：</label></td>
						<td>${dbInfo.version }</td>
						<td></td>
					</tr>
					<tr>
						<td><label>最后修改时间：</label></td>
						<td><fmt:formatDate value="${dbInfo.updateTime }"
								pattern="yyyy-MM-dd" /></td>
						<td></td>
					</tr>
					<tr>
						<td>状态：</td>
						<td><c:choose>
								<c:when test="${dbInfo.locked }">
									<b>修改中 &nbsp;&nbsp; <a
										href="${pageContext.request.contextPath }/config/database/update/${dbInfo.id}/finish">完成修改</a></b>
								</c:when>
								<c:otherwise>正常&nbsp;&nbsp;<a
							href="${pageContext.request.contextPath }/config/database/update/${dbInfo.id}/begin">开始修改</a></c:otherwise>
							</c:choose></td>
						<td></td>
					</tr>
					<tr>
						<td>是否可用：</td>
						<td><c:choose>
								<c:when test="${dbInfo.disabled }">
									<b>已过期</b>
								</c:when>
								<c:otherwise>可用</c:otherwise>
							</c:choose></td>
						<td></td>
					</tr>
					<tr>
						<td>数据库所有者：</td>
						<td>${owner.fullName }</td>
						<td></td>
					</tr>
					<tr>
						<td>其他可用人员：</td>
						<td><c:forEach items="${userList }" var="user">
								<c:out value="${user.fullName }"></c:out>
								<br />
							</c:forEach></td>
						<td></td>
					</tr>
				</table>
				<h4></h4>
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