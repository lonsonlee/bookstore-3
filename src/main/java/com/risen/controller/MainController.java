package com.risen.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 主控制器
 * @author sen
 *	
 */
@Controller
public class MainController {
	@RequestMapping("register")
	public String toRegister(){
		return "/front/register";
	}
	
	@RequestMapping("login")
	public String toLogin(){
		return "front/login";
	}
	
	@RequestMapping("main")
	public String toMain(){
		return "front/main";
	}
	
	@RequestMapping("updatepw")
	public String toUpdatepw(){
		return "front/updatepw";
	}
	
	@RequestMapping("test2")
	public String test(HttpServletRequest request){
		request.setAttribute("test","kkkkkkkkk");
		return "front/test2";
	}
	
	@RequestMapping("testk")
	public String toTest(){
		return "front/test";
	}
}
