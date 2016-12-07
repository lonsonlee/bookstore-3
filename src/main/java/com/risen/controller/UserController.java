package com.risen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.risen.bean.User;
import com.risen.exception.UserException;
import com.risen.service.UserService;

/**
 * @author sen
 *	
 */
@Controller
@RequestMapping("user")
public class UserController {
	
	@Resource(name="userService")
	private UserService userService;
	
	
	/**
	 * @param loginname
	 * @param response
	 *  前端ajax校验用户名是否已被注册
	 */
	@RequestMapping("loginname")
	public void validateLoginname(@RequestParam String loginname,HttpServletResponse response){
		//调用service层方法校验用户名是否已被注册
		boolean result=userService.validateLoginname(loginname);
		common(result,response);
	}
	
	/**
	 * @param email
	 * @param response
	 *  前端ajax校验邮箱是否已被注册
	 */
	@RequestMapping("email")
	public void validateEmail(@RequestParam String email,HttpServletResponse response){
		//盗用service层方法校验邮箱是否已被注册
		boolean result=userService.validateEmail(email);
		common(result,response);
	}
	
	
	/**
	 * @param captcha
	 * @param request
	 * @param response
	 * 	前端ajax校验验证码是否相等
	 */
	@RequestMapping("captcha")
	public void validateCaptcha(@RequestParam String captcha,HttpServletRequest request,HttpServletResponse response){
		//调用captchaMatch方法校验验证码是否相等
		boolean result=captchaMatch(captcha, request);
		common(result, response);
	}
	
	/**
	 * @param result
	 * @param response
	 *  重复代码，返回一个boolean值到客户端
	 */
	private void common(boolean result,HttpServletResponse response){
		response.setContentType("application/json");
		try {
			PrintWriter out =response.getWriter();
			out.print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return
	 * 重复代码，判断两个验证码是否相等
	 */
	private boolean captchaMatch(String captcha,HttpServletRequest request){
		//获取session中的验证码
		String imgCaptcha=(String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//将输入框中的数据与session中的数据进行比较，忽略大小写
		return captcha.toLowerCase().equals(imgCaptcha.toLowerCase());
	}
	
	/**
	 * 用户注册逻辑
	 */
	@RequestMapping("doRegister")
	public String addUser(HttpServletRequest request){
		/*
		 * 1.接收表单数据，封装到javabean中
		 */
		User user=new User();
		try {
			//调用commons-beanutils工具方法，将map转化为javabean
			BeanUtils.populate(user,request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		/*
		 * 2.调用backValidate()进行后端数据校验
		 */
		Map<String ,String> errors=backValidate(user, request);
		//如果校验存在错误信息，则返回页面重新输入
		if(errors.size()>0){
			request.setAttribute("errors",errors);
			request.setAttribute("user", user);
			return "front/register";
		}
		/*
		 * 3.调用service层方法，添加新用户,把user信息返回到result.jsp，方便重新发送邮件
		 */
		User resultUser=userService.addUser(user);
		/*
		 * 4.跳转
		 */
		
		request.setAttribute("flag","registerSuccess");
		request.setAttribute("resultUser", resultUser);
		return "front/result";
		
	}
	
	/**
	 * 给用户重新发送激活邮件
	 */
	@RequestMapping("resend")
	public void resend(HttpServletRequest request,HttpServletResponse response){
		User resultUser=new User();
		try {
			BeanUtils.populate(resultUser, request.getParameterMap());
			System.out.println(resultUser.getActivationCode());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		userService.sendEmail(resultUser);
		common(true, response);
	}
	
	/**
	 * @param user
	 * @param request
	 * @return
	 *  后端对于注册页面的数据校验方法
	 */
	private Map<String,String> backValidate(User user,HttpServletRequest request){
		/*
		 * 将校验的错误信息存在map中，返回到register.jsp
		 */
		Map<String,String> errors=new HashMap<>();
		/*
		 * 1.用户名校验
		 */
		String loginname=user.getLoginname();
		if(loginname==null||loginname.trim().isEmpty()){
			errors.put("loginname","用户名不能为空");
		}else if(loginname.length()<4||loginname.length()>20){
			errors.put("loginname","用户名长度需在4到20之间");
		}else if(!userService.validateLoginname(loginname)){
			errors.put("loginname", "用户名已被注册");
		}
		
		/*
		 * 2.密码校验
		 */
		String loginpass=user.getLoginpass();
		if(loginpass==null||loginpass.trim().isEmpty()){
			errors.put("loginpass","密码不能为空");
		}else if(loginpass.length()<6||loginpass.length()>24){
			errors.put("loginpass", "密码长度须在6到24之间");
		}
		
		/*
		 * 3.确认密码校验
		 */
		String reloginpass=user.getReloginpass();
		if(reloginpass==null||reloginpass.trim().isEmpty()){
			errors.put("reloginpass","确认密码不能为空");
		}else if(!reloginpass.equals(loginpass)){
			errors.put("reloginpass", "两次输入不一致");
		}
		/*
		 * 4.邮箱校验
		 */
		String email=user.getEmail();
		if(email==null||email.trim().isEmpty()){
			errors.put("email", "Email不能为空");
		}else if(!email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")){
			errors.put("email","Email格式不正确");
		}else if(!userService.validateEmail(email)){
			errors.put("email", "Email已被注册");
		}
		
		/*
		 * 5.验证码校验
		 */
		String captcha=user.getCaptcha();
		if(captcha==null||captcha.trim().isEmpty()){
			errors.put("captcha","验证码不能为空");
		}else if(!captchaMatch(captcha, request)){
			errors.put("captcha", "验证码不正确");
		}
		
		return errors;
	}
	
	/**
	 * 用户激活
	 */
	@RequestMapping("activate")
	public String activate(HttpServletRequest request){
		/*
		 * 获取参数
		 */
		String urlActivationCode=request.getParameter("activationCode");
		/*
		 * 调用service方法进行激活
		 */
		User user=null;
		try {
			user=userService.activate(urlActivationCode);
		} catch (UserException e) {
			if(e.getMessage().equals("invalid")){
				request.setAttribute("error","无效的激活码");
				request.setAttribute("flag", "invalid");
				return "front/result";
			}else if(e.getMessage().equals("havedone"))
				return "front/main";
		}
		request.getSession().setAttribute("loginname", user.getLoginname());
		request.getSession().setAttribute("loginpass", user.getLoginpass());
		return "front/main";
	}
	
	
	/**
	 * @return
	 *  用户登录逻辑
	 */
	@RequestMapping("doLogin")
	public String dologin(@RequestParam String username,@RequestParam String password,HttpServletRequest request,HttpServletResponse response){
		
		
		
		/*
		 * 1.校验表单数据是否为空，如果为空返回login.jsp ，显示错误信息
		 */
		if(username==null||username.trim().isEmpty()||password==null||password.trim().isEmpty()){
			return toLogin(username, password, request);
		}
		/*
		 * 2.如果数据都不为空，根据表单数据查询数据库进行校验
		 */
		Map<String,String> loginMap=new HashMap<>();
		loginMap.put("username", username);
		loginMap.put("password", password);
		User user=userService.doLogin(loginMap);
		
		/*
		 * 3.判断查询结果是否为空，证明用户名或者密码错误，如果为空返回login.jsp,显示错误信息
		 */
		if(user==null){
			return toLogin(username, password, request);
		}
		
		/*
		 * 4.判断用户是否勾选“记住我”复选框，如果有，创建cookie
		 */
		String[]rememberMe=request.getParameterValues("rememberMe");
		if(rememberMe!=null&&rememberMe.length>0){
			//创建两个cookie分别存储用户名和密码返回到浏览器
			Cookie cookie1=new Cookie("loginname",user.getLoginname());
			cookie1.setMaxAge(60*60*24*10);
			cookie1.setPath("/");
			response.addCookie(cookie1);
			Cookie cookie2=new Cookie("loginpass", user.getLoginpass());
			cookie2.setMaxAge(60*60*24*10);
			cookie2.setPath("/");
			response.addCookie(cookie2);
		}
		
		/*
		 * 5.判断用户的激活状态，根据激活状态在main.jsp 作相应的显示
		 */
		request.setAttribute("status",user.getStatus());
		request.getSession().setAttribute("loginname", user.getLoginname());
		request.getSession().setAttribute("loginpass", user.getLoginpass());
		return "front/main";
	}
	
	
	/**
	 * @return
	 *  doLogin方法重复代码：保存错误信息，返回到登录页面
	 */
	private String toLogin(String username,String password,HttpServletRequest request){
		//保存错误信息
		request.setAttribute("error", "用户名或密码错误");
		//保存用户输入信息，方便回显
		request.setAttribute("username", username);
		request.setAttribute("password", password);
		//放回登录页面
		return "front/login";
	}
	
	/**
	 * @return
	 *  修改密码
	 */
	@RequestMapping("doUpdatepw")
	public String doUpdatepw(@RequestParam String oldPassword,@RequestParam String newPassword,@RequestParam String cnewPassword,HttpServletRequest request){
		
		
		/*
		 * 1.校验表单数据
		 */
		Map<String,String> errors=new HashMap<String, String>();
		//校验旧密码
		if(oldPassword==null||oldPassword.trim().isEmpty())
			errors.put("oldPassword","请填写此项");
		//校验新密码
		if(newPassword==null||newPassword.trim().isEmpty()){
			errors.put("newPassword","请填写此项");
		}else if(newPassword.length()<6||newPassword.length()>20){
			errors.put("newPassword","密码长度须在6到20之间");
		}
		//校验确认密码
		if(cnewPassword==null||cnewPassword.trim().isEmpty()){
			errors.put("cnewPassword", "请填写此项");
		}else if(!cnewPassword.equals(newPassword)){
			errors.put("cnewPassword", "两次输入不一致");
		}
		/*
		 * 2.如果存在错误，重新跳转，保存错误信息和回显信息
		 */
		if(errors.size()>0){
			Map<String,String> inputs=new HashMap<String, String>();
			inputs.put("oldPassword", oldPassword);
			inputs.put("newPassword", newPassword);
			inputs.put("cnewPassword", cnewPassword);
			request.setAttribute("errors", errors);
			request.setAttribute("inputs", inputs);
			return "front/updatepw";
		}
		/*
		 * 3.如果不存在错误，从数据库校验旧密码是否正确
		 * 	（可调用service的doLogin()方法，判断返回用户是否为空）
		 */
		
		Map<String,String> map=new HashMap<String, String>();
		map.put("username", (String)request.getSession().getAttribute("loginname"));
		map.put("password",oldPassword);
		User user=userService.doLogin(map);
		
		/*
		 * 4.如果user为空，证明旧密码错误
		 */
		if(user==null){
			request.setAttribute("oldPasswordError", "旧密码不正确");
			return "front/updatepw";
		}
		/*
		 * 5.如果user不为空，修改数据库密码为新密码
		 */
		map.replace("password", oldPassword, newPassword);
		userService.updateLoginpass(map);
		request.setAttribute("successMsg", "密码修改成功");
		
		return "front/updatepw";
	}
	
	/**
	 * @param status
	 * @return 
	 *  用户退出
	 */
	@RequestMapping("doLogout")
	public String doLogout(SessionStatus status){
		status.setComplete();
		return "front/login";
	}
	
	
	
}
