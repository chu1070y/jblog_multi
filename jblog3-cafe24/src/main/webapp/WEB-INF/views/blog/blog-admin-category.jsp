<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.servletContext.contextPath}/${authUser.id }/admin/write">글작성</a></li>
				</ul>
		      	<table class="admin-cat" id="category">
					
				</table>
				
				<table style="display: none;">
					<tr id="chartTitle">
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		
		      		<tr id="row">
						<td id="no"></td>
						<td id="title"></td>
						<td id="count"></td>
						<td id="description"></td>
						<td><img id="deleteimg" src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>
					</tr> 
				</table>

      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="title" id="categoryTitle"/></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="description" id="categoryDesc"/></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" id="categoryAdd" value="카테고리 추가"></td>
		      		</tr>
		      	</table> 
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer_blog.jsp" />
	</div>
<input type="hidden" id="blogId" value="${blogInfo.id}" />
<script type="text/javascript">

$(function(){
	
	var blogId = $("#blogId").val();
	
	var no = $("#no");
	var title = $("#title");
	var count = $("#count");
	var description = $("#description");
	var deleteimg = $("#deleteimg");
	var category = $("#category");
	var row = $("#row");
	
	var chartTitle = $("#chartTitle");
	
	function showList(id){
		
		/*ajax 통신*/
		$.ajax({
			url: "${pageContext.servletContext.contextPath}/${authUser.id}/admin/getCategoryList?id=" + id,
			type: "get",
			dataType: "json",
			data: "",
			success: function(response){
				
				category.empty();
				category.append(chartTitle);
				var number = 0;
				
				response.data.forEach(function(element){
					
					no.html(element.no);
					title.html(element.title);
					count.html(element.count);
					description.html(element.description);
					
					var clone = row.clone();
					
					clone.on("click", "img", function(){
						deleteCategory(element.no, id);
					});
					
					category.append(clone);
				});
				
			},
			fail: function(xhr, err){
				console.error("error: " + err);
			}
		});// end ajax
	};// end showList
	
	
	var categoryTitle = $("#categoryTitle");
	var categoryDesc = $("#categoryDesc");
	var categoryAdd = $("#categoryAdd");
	
	function insertCategory(title, desc, id){

		/*ajax 통신*/
		$.ajax({
			url: "${pageContext.servletContext.contextPath}/${authUser.id}/admin/insertCategory",
			type: "post",
			dataType: "json",
			data: {
				title: title.val(),
				description: desc.val(),
				blogId: id
			},
			success: function(response){

				if(response.result == "fail"){
					alert("카테고리명을 입력해주세요.");
					return;
				}
				
				showList(id);
				
			},
			fail: function(xhr, err){
				console.error("error: " + err);
			}
		});// end ajax
	};//end insertCategory
	
	categoryAdd.on("click",function(){
		insertCategory(categoryTitle, categoryDesc, blogId);
	});
	
	function deleteCategory(no, id){
		
		var deleteAll = confirm("해당 카테고리와 포함되는 모든 글들이 지워집니다. 삭제하시겠습니까?");
		
		if(!deleteAll){
			return;
		}
		
		/*ajax 통신*/
		$.ajax({
			url: "${pageContext.servletContext.contextPath}/${authUser.id}/admin/deleteCategory?no=" + no,
			type: "get",
			dataType: "json",
			data: "",
			success: function(response){

				if(!response.data){
					alert("서버오류 발생.");
					return;
				}
				
				alert("카테고리가 삭제되었습니다.");
				showList(id);
			},
			fail: function(xhr, err){
				console.error("error: " + err);
			}
		});// end ajax
	};// end deleteCategory
	

	showList(blogId);
	
});
</script>
	
</body>
</html>