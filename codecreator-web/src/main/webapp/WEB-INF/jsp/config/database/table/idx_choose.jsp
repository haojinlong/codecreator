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

<script type="text/javascript">
	function reset() {
		$("#content").val("");
		$("input").each(function(){
			$(this).attr("disabled", false);
		});
	}
	
	function choose(name, idx){
		var content;
		if ($("#content").val().length > 0){
			content = $("#content").val() + ',' + name;
		}
		else{
			content = name;
		}
		$("#content").val(content);
		$("#button_"+idx).attr("disabled", "disabled");
	}
	
	function submit(){
		var contentId = $("#contentId").val();
		var content = $("#content").val();
		//alert(contentId + content);
		window.opener.document.getElementById(contentId).value=content;
		window.close();
	}
</script>
<style type="text/css">
.id {
	width: 60px;
}

.name {
	width: 230px;
}

.choose {
	width: 60px;
}

#content {
	width: 280px;
}

table {
	border-collapse: collapse;
}
</style>
</head>
<body>

	<table border="1">
		<tr>
			<td colspan="3"><b>请依此选择列：</b></td>
		</tr>
		<tr>
			<td class="id">序号</td>
			<td class="name">列名</td>
			<td class="choose"></td>
		</tr>
		<tbody>
			<c:forEach items="${columnList}" var="columnInfo">
				<tr>
					<td>${columnInfo.idx }</td>
					<td>${columnInfo.name }</td>
					<td><input type="button" value="选择"
						id="button_${columnInfo.idx }"
						onclick="javascript:choose('${columnInfo.name}', ${columnInfo.idx})" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td>字段：</td>
				<td colspan="2"><input type="text" id="content"
					readonly="readonly" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="button" value="重置"
					onclick="javascript:reset()" /> <input type="button" value="确定"
					onclick="javascript:submit()" /> <input type="hidden"
					value="${contentId }" id="contentId" /></td>
			</tr>
		</tbody>
	</table>
</body>
</html>