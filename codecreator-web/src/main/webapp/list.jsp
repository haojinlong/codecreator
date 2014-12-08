<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/dev-java.css" rel="stylesheet" type="text/css" />
<title>郝烦记录之javaEE开发培训系列</title>
</head>
<body class="javaee">
	<div id="wrapper">
		<div id="header">
			<jsp:include page="/header.jsp" />
		</div>
		<div id="navfirst">
			<jsp:include page="/menu.jsp" />
		</div>


		<div id="navsecond">
			<h2>基础知识（建设中）</h2>
			<ul>
				<li>javaee开发运行环境搭建</li>
				<li>java编程基础</li>
			</ul>
			<h2>编程技巧</h2>
			<ul>
				<li><a href="/javaee/skill/log/log-0.jsp">java日志框架培训</a></li>
				<li><a>eclipse使用技巧</a></li>
				<li><a>maven使用技巧</a></li>
			</ul>
			<h2>数据层开发</h2>
			<ul>
				<li><a href="/javaee/dao/mybatis/mybatis-0.jsp">mybatis基础培训</a></li>
				<li><a>hiberante基础培训</a></li>
				<li><a>jpa基础培训</a></li>
			</ul>
			<h2>应用层开发</h2>
			<ul>
				<li><a href="/javaee/service/spring/spring-0.jsp">spring基础培训</a></li>
			</ul>
			<h2>mvc层开发</h2>
			<ul>
				<li><a href="/javaee/mvc/springmvc/springmvc-0.jsp">spring-mvc基础培训</a></li>
				<li><a>struts2基础培训（建设中）</a></li>
			</ul>
			<h2>web层开发（建设中）</h2>
			<ul>
				<li><a>html/css基础培训</a></li>
				<li><a>jquery基础培训</a></li>
			</ul>
		</div>
		<div id="maincontent">
			<div>
				<h2>[2014-08] javaee-mvc-springmvc：spring mvc基础培训</h2>
				<div class="thumb">
					<a href="/javaee/mvc/springmvc/springmvc-0.jsp"><img class="thumb" src="/images/thumb-springmvc.png" alt="spring mvc基础培训" /></a>
				</div>
				<div class="thumb_desc">
					<h4>培训主要内容</h4>
					<ul class="thumb_desc">
						<li>介绍spring mvc框架的基本使用方法、请求数据的封装，与前后端框架的整合以及常用的使用技巧等</li>
					</ul>
					<h4>前提：</h4>
					<ul>
						<li>阅读人员具备一定的 Java 开发基础以及关系型数据库的基础知识，具备一定的html/css/javascript使用经验</li>
					</ul>
				</div>
			</div> 
			<div>
				<h2>[2014-07] javaee-service-spring：spring基础培训</h2>
				<div class="thumb">
					<a href="/javaee/service/spring/spring-0.jsp"><img class="thumb" src="/images/thumb-spring.png" alt="spring基础培训" /></a>
				</div>
				<div class="thumb_desc">
					<h4>培训主要内容</h4>
					<ul class="thumb_desc">
						<li>介绍spring框架的基本使用方法和技巧，以及配置文件说明；说明spring与数据持久化框架的集成方式，以及通过spring进行远程服务调用的方式</li>
					</ul>
					<h4>前提：</h4>
					<ul>
						<li>阅读人员具备一定的 Java 开发基础以及关系型数据库的基础知识，最好具有hibernate等数据持久化使用经验</li>
					</ul>
				</div>
			</div>
			<div>
				<h2>[2014-07] javaee-dao-mybatis：mybatis基础培训</h2>
				<div class="thumb">
					<a href="/javaee/dao/mybatis/mybatis-0.jsp"><img class="thumb" src="/images/thumb-mybatis.png" alt="mybatis基础培训" /></a>
				</div>
				<div class="thumb_desc">
					<h4>培训主要内容</h4>
					<ul class="thumb_desc">
						<li>介绍mybatis的基础使用技巧、与c3p0和spring框架的集成，以及通过mybatis实现多表关联映射和动态SQL</li>
					</ul>
					<h4>前提：</h4>
					<ul>
						<li>阅读人员具备一定的 Java 开发基础以及关系型数据库的基础知识</li>
					</ul>
				</div>
			</div>
			<div>
				<h2>[2014-06] javaee-skill-log：Java日志框架</h2>
				<div class="thumb">
					<a href="/javaee/skill/log/log-0.jsp"><img class="thumb" src="/images/thumb-log.png" alt="java日志框架培训" /></a>
				</div>
				<div class="thumb_desc">
					<h4>培训主要内容</h4>
					<ul class="thumb_desc">
						<li>介绍java日志框架的使用技巧，包括slf4j、commons-logging等接口框架和lgo4j、log4j2、logback等实现框架的使用</li>
					</ul>
					<h4>前提：</h4>
					<ul>
						<li>阅读人员具备一定的 Java 开发基础,熟悉 Java 开发环境的配置和使用(如 JDK 、 Eclipse 的安装等)</li>
					</ul>
				</div>
			</div>

		</div>
		<div id="sidebar">
			<h2>参考站点推荐</h2>
			<ul>
				<li><a title="HTML、JavaScript、CSS学习参考" href="http://www.w3cschool.com.cn">w3cschool</a></li>
			</ul>
		</div>
		<div id="footer">
			<jsp:include page="/footer.jsp" />
		</div>
	</div>
</body>
</html>