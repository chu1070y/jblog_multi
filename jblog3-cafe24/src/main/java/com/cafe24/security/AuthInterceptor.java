package com.cafe24.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	//먼저 @Auth가 있는지 여부를 체크한다. Auth가 없으면 true로 리턴해줘서 모든 사용자가 해당 페이지를 볼 수 있도록하고
	//@Auth가 붙어있다면 권한이 있는 사용자만 해당 페이지를 볼 수 있게 만든다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("-------------AuthInterceptor");
		
		//1. Handler 종류 확인
		//   Controller에 있는 메소드이면 true를 리턴해준다.
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		//2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Method의 @Auth 받아오긔
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		//4. Method에 @Auth 없으면
		//   Class(Type)에 @Auth를 받아오긔
		if(auth == null) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}

		//5. @Auth가 안 붙어있는 경우 
		if(auth == null) {
			return true;
		}
		
		//6. @Auth가 (class 또는 method에) 붙어 있기 때문에
		//   사용자 인증 여부 체크
		HttpSession session = request.getSession();
		
		if(session == null) { //인증이 안되어 있으면
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {// 인증이 안되있음
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		//7. @Pathvariable 갖고오긔
		Map<String, String> uriTemplateVars = (Map<String, String>) request.getAttribute( 
				HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String id = uriTemplateVars.get("id");

		//8. 권한 체크
		System.out.println("interceptor 권한 체크----------" + id );
		if(authUser.getId().equals(id)) {
			return true;
		}
		
		response.sendRedirect(request.getContextPath() + "/user/login");
		return false;
	}
}
