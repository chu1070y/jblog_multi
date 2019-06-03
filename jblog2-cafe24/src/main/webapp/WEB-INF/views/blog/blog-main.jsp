<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("newline", "\n");
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js">
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header_blog.jsp" />
		<div id="wrapper">
			<div id="content">
			<c:if test="${empty posts}">
			<br />
			<h1>글을 등록해주세요.</h1>
			</c:if>
			<c:forEach items="${posts }" var="post" varStatus="status">
			
				<c:if test="${status.count == 1 }">
				<div class="blog-content">
					<h4 id="title_show">${post.title }</h4>
					<p id="content_show">
						${fn:replace(post.content,newline,"<br>") }
					<p>
				</div>
				<hr/>
				<ul class="blog-list">
				</c:if>
				
					<li><a class="changePost" data-no="${post.no }" href="#">${post.title }
						<input type="hidden" class="content_no" id="${post.no }" value="${fn:replace(post.content,newline,'<br>') }" />
						</a> <span>${post.regDate }</span>	</li>
					
			</c:forEach>
				</ul>
				
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/${blogInfo.logo }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			<c:forEach items="${categorys }" var="category">
				<li><a href="${pageContext.request.contextPath}/${blogInfo.id}/${category.no}">${category.title }(${category.count })</a></li>
			</c:forEach>
			</ul>
		</div>
		
		<c:import url="/WEB-INF/views/includes/footer_blog.jsp" />
	</div>
<script>
$(function(){
	
	var title = $("#title_show");
	var content = $("#content_show");
	
	$(".changePost").click(function(e){
		e.preventDefault();
		
		var no = $(this).attr("data-no");		
		var title_no = $(this).html();
		var content_no = $(this).find('input').val();
		
		title.html(title_no);
		content.html(content_no);
		
	});
	
});
	

</script>
</body>
</html>