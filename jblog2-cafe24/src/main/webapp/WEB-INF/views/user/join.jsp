<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header_main.jsp" />
		<form class="join-form" id="join-form" method="post" action="join">
			<label class="block-label" for="name">이름</label>
			<input id="name"name="name" type="text" value="">
			<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('name') }">
					<br/>
					<strong style="color: red"> <spring:message
						code="${errors.getFieldError( 'name' ).codes[0] }"
						text="${errors.getFieldError( 'name' ).defaultMessage }" />
					</strong>
				</c:if>
			</spring:hasBindErrors>
			
			<label class="block-label" for="blog-id">아이디</label>
			<input id="blog-id" name="id" type="text"> 
			<input id="btn-checkid" type="button" value="id 중복체크">
			<img id="img-checkid" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('id') }">
					<br/>
					<strong style="color: red"> <spring:message
						code="${errors.getFieldError( 'id' ).codes[0] }"
						text="${errors.getFieldError( 'id' ).defaultMessage }" />
					</strong>
				</c:if>
			</spring:hasBindErrors>

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />
			<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('password') }">
					<br/>
					<strong style="color: red"> <spring:message
						code="${errors.getFieldError( 'password' ).codes[0] }"
						text="${errors.getFieldError( 'password' ).defaultMessage }" />
					</strong>
				</c:if>
			</spring:hasBindErrors>

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" >
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input id="joinbtn" type="submit" value="가입하기">

		</form>
		
		<input type="hidden" id="problem" value="${problem }">

<script type="text/javascript">

$(function(){
	
	
	var joinform = $("#join-form");
	var checkbtn = $("#btn-checkid");
	var joinbtn = $("#joinbtn");
	var agreeprov = $("#agree-prov");
	
	var check = 0;
	var agree = 0;
	

 	agreeprov.on("click",function(e){
		
		if(agree == 0){
			agree = 1;
			agreeprov.attr("checked","checked");
			
		}else{
			agree = 0;
			agreeprov.removeAttr("checked");
		}
	}); 
	
	if('problem' == $("#problem").val()){
		alert("입력된 정보가 잘못되었습니다. 다시 입력해주세요.");
	}
	
 	$("#blog-id").on("change",function(){
		$("#btn-checkid").show();
		$("#img-checkid").hide();
		check = 0;
	});
 	
 	joinbtn.on("click", function(e) {
 		e.preventDefault();
 		
 		if(check == 0){
 			alert("id 중복체크를 해주세요");
 			return;
 		}
 		
		if(agree == 0){
			alert('서비스 약관에 동의해주세요.');
			return;
		}
 		
 		joinform.submit();
 	});
	
	
 	checkbtn.on("click", function(e) {
		e.preventDefault();
		

		var id = $("#blog-id").val();
		
		if(id == ''){
			alert("아이디를 입력해주세요.");
			return;
		}
		
		/*ajax 통신*/
		$.ajax({
			url: "${pageContext.servletContext.contextPath}/user/checkid?id=" + id,
			type: "get",
			dataType: "json",
			data: "",
			success: function(response){
				if(response.result != "success"){
					console.log(response);
					return;
				}
				
				if(response.data == true){
					alert("이미 존재하는 이메일입니다")
					$("#blog-id").focus();
					$("#blog-id").val("");
					return;
				}
				
				$("#btn-checkid").hide();
				$("#img-checkid").show();
				check = 1;
			},
			fail: function(xhr, err){
				console.error("error: " + err);
			}
		});

	});
});
</script>
		
	</div>
	
</body>
</html>
