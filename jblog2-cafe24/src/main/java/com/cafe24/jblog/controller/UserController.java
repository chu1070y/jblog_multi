package com.cafe24.jblog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.jblog.dto.JSONResult;
import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/join")
	public String join() {
		
		return "user/join";
	}
	
	@PostMapping("/join")
	public String joinPost(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			
			for(ObjectError error : list) {
				System.out.println(error);
			}
			
			return "user/join";
		}
		
		if(!userService.insertUser(vo)) {
			model.addAttribute("problem", "problem");
			return "user/join";
		}
		
		return "user/joinsuccess";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "user/login";
	}
	
	@PostMapping("/auth")
	public void auth() {

	}
	
	@ResponseBody
	@RequestMapping("/checkid")
	public JSONResult checkid(@RequestParam(value="id", required=true, defaultValue="") String id){
		
		Boolean check = userService.checkid(id);
		return JSONResult.success(check);
	}

}
