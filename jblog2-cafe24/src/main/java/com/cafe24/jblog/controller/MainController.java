package com.cafe24.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping({"", "/main", "/main/index"})
	public String index() {
		System.out.println("main 페이지");
		return "main/index";
	}
	
	@GetMapping("/error/404")
	public String error404() {
		
		return "error/404";
	}
	
	@GetMapping("/error/500")
	public String error500() {
		
		return "error/500";
	}
}
