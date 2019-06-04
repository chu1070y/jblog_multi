package com.cafe24.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.vo.PostVo;

public class BlogInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//1. Handler 종류 확인
		//   Controller에 있는 메소드이면 true를 리턴해준다.
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		//2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Method의 @Blog 받아오긔
		Blog blog = handlerMethod.getMethodAnnotation(Blog.class);
		
		//4. Method에 @Blog 없으면
		//   Class(Type)에 @Blog를 받아오긔
		if(blog == null) {
			blog = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Blog.class);
		}

		//5. @Blog가 안 붙어있는 경우 
		if(blog == null) {
			return true;
		}
		
		System.out.println("-------------------");
		System.out.println(blog);
		System.out.println(handlerMethod.getMethod().getName());
		
		
		// 본격적으로 url 확인 작업 수행
		//6. @Pathvariable 갖고오긔
		Map<String, String> uriTemplateVars = (Map<String, String>) request.getAttribute( 
				HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		String categoryNo = uriTemplateVars.get("categoryNo");
		String postNo = uriTemplateVars.get("postNo");
		String id = uriTemplateVars.get("id");
		
		PostVo postVo = new PostVo();
		postVo.setCategoryNo(categoryNo == null ? null : Long.parseLong(categoryNo));
		postVo.setNo(postNo == null ? null : Long.parseLong(postNo));
		postVo.setId(id);
		
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		BlogService blogService = ac.getBean(BlogService.class);
		
		//7. 카테고리 번호가 없는 경우
		if(categoryNo == null) {
			return true;
		}
		
		//8. 특정 카테고리 번호가 없는 경우
		if(blogService.checkCategoryNo(postVo) == null) {
			response.sendRedirect(request.getContextPath() + "/error/404");
			return false;
		}
		
		//9. 포스트 번호가 없는 경우
		if(postNo == null) {
			return true;
		}
		
		//10. 특정 포스트 번호가 없는 경우
		if(blogService.checkPostNo(postVo) == null) {
			response.sendRedirect(request.getContextPath() + "/error/404");
			return false;
		}
		
		return true;
	}
}
