<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<ul class="menu">
		<div id="header">
			<h1>${blogInfo.title }</h1>
			<ul>
				<c:choose>
					<c:when test="${empty authUser}">
						<li><a href="${pageContext.servletContext.contextPath}/user/login">로그인</a></li>
					</c:when>
					<c:when test="${authUser.id != blogInfo.id}">
						<li><a href="${pageContext.servletContext.contextPath}/user/logout">로그아웃</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.servletContext.contextPath}/user/logout">로그아웃</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/${authUser.id }/admin/basic">블로그 관리</a></li>
					</c:otherwise>
				</c:choose>
				<li><a href="${pageContext.servletContext.contextPath}/${blogInfo.id }">블로그 메인</a></li>
			</ul>
		</div>

</ul>
