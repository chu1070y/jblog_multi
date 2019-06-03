<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
	<c:import url="/WEB-INF/views/includes/header_blog.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li class="selected">기본설정</li>
					<li><a href="${pageContext.servletContext.contextPath}/${authUser.id }/admin/category">카테고리</a></li>
					<li><a href="${pageContext.servletContext.contextPath}/${authUser.id }/admin/write">글작성</a></li>
				</ul>
				<form action="${pageContext.servletContext.contextPath}/${authUser.id }/admin/basic" method="post" enctype="multipart/form-data">
	 		      	<input type="hidden" name="id" value="${authUser.id }">
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="t">블로그 제목</td>
			      			<td><input type="text" size="40" name="title" value="${blogInfo.title }"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">로고이미지</td>
			      			<td><img src="${pageContext.request.contextPath}/${blogInfo.logo }"></td>      			
			      		</tr>      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><input type="file" name="multipartFile" ></td>      			
			      		</tr>           		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s"><input type="submit" value="기본설정 변경"></td>      			
			      		</tr>           		
			      	</table>
			      	
			      		<spring:hasBindErrors name="blogVo" >
							<c:if test="${errors.hasFieldErrors('title') }">
								<strong style="color: red">
									<spring:message code="${errors.getFieldError( 'title' ).codes[0] }" text="${errors.getFieldError( 'title' ).defaultMessage }" />
								</strong>
							</c:if>
						</spring:hasBindErrors>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer_blog.jsp" />
	</div>
</body>
</html>