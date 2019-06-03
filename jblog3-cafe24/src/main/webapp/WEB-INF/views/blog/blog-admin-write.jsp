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
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js">
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header_blog.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.servletContext.contextPath}/${authUser.id }/admin/basic">기본설정</a></li>
					<li><a href="${pageContext.servletContext.contextPath}/${authUser.id }/admin/category">카테고리</a></li>
					<li class="selected">글작성</li>
				</ul>

				<form action="${pageContext.servletContext.contextPath}/${authUser.id }/admin/writePost" method="post">
			      	<table class="admin-cat-write">
			      		<tr>
			      			<td class="t">제목</td>
			      			<td>
			      				<input type="text" size="60" name="title">
				      			<select name="categoryNo">
				      				<c:forEach items='${categoryList}' var='categ' varStatus="status">
				      					<option value="${categ.no }">${categ.title }</option>
				      				</c:forEach>
				      			</select>
				      		</td>
			      		</tr>
						
			      		<tr>
			      			<td class="t">내용</td>
			      			<td><textarea name="content"></textarea></td>
			      		</tr>
			      		
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td class="s"><input type="submit" value="포스트하기"></td>
			      		</tr>
			      		
			      	</table>
			     	 	<spring:hasBindErrors name="postVo" >
							<c:if test="${errors.hasFieldErrors('title')}">
								<strong style="color: red">
									<spring:message code="${errors.getFieldError( 'title' ).codes[0] }" text="${errors.getFieldError( 'title' ).defaultMessage }" />
								</strong>
							</c:if>
						</spring:hasBindErrors>
						<spring:hasBindErrors name="postVo">
							<c:if test="${errors.hasFieldErrors('content') }">
								<strong style="color: red">
									<spring:message code="${errors.getFieldError( 'content' ).codes[0] }" text="${errors.getFieldError( 'content' ).defaultMessage }" />
								</strong>
							</c:if>
						</spring:hasBindErrors>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer_blog.jsp" />
	</div>
<input type="hidden" id="result" value="${result }" />

<script>
	
	if ("fail" == $("#result").val()){
		alert("요청실패!!\n카테고리가 없습니다.\n카테고리부터 만들어주세요.");
	}
	
	if ("success" == $("#result").val()){
		alert("정상적으로 등록되었습니다.");
	}
</script>
</body>
</html>