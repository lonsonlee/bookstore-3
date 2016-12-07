package com.risen.service;


import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.risen.bean.User;
import com.risen.dao.UserMapper;
import com.risen.exception.UserException;
import com.risen.util.MailUtils;
import com.risen.util.UUIDGenerator;

/**
 * @author sen
 *	用户注册的service层
 */
@Service
public class UserService {
	
	@Resource
	private UserMapper userMapper;
	
	
	/**
	 * @param loginname
	 * @return
	 *  用户名是否已被注册
	 */
	public boolean validateLoginname(String loginname) {
		return userMapper.validateLoginname(loginname)==0;
	}
	
	
	/**
	 * @param email
	 * @return
	 *  邮箱是否已被注册
	 */
	public boolean validateEmail(String email) {
		return userMapper.validateEmail(email)==0;
	}
	
	/**
	 * 新增用户
	 */
	public User addUser(User user){
		/*
		 * 1.补齐表单没有的数据
		 */
		user.setStatus(false);//设置状态为未激活
		user.setUid(UUIDGenerator.getShort()+UUIDGenerator.getShort());//生成16位uid
		user.setActivationCode(UUIDGenerator.getLong()+UUIDGenerator.getLong());//生成64位激活码
		/*
		 * 2.向数据库添加数据
		 */
		userMapper.addUser(user);
		/*
		 * 3.给用户发送激活邮件
		 */
		sendEmail(user);
		
		return user;
	}
	
	/**
	 * 给用户发邮件
	 */
	public void sendEmail(User user){
		//把配置文件内容封装到Properties对象中
		Properties prop=new Properties();
		try {   //javaMail.properties位于classpath下
			prop.load(this.getClass().getClassLoader().getResourceAsStream("javaMail.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//获取用户邮箱地址
		String to=user.getEmail();
		//设置邮件内容，将模板文件的占位符替换
		String content=MessageFormat.format(prop.getProperty("javaMail.content"), user.getActivationCode());
		//修改prop对象
		prop.setProperty("javaMail.content", content);
		//发送邮件
		try {
			MailUtils.sendEmail(prop, to);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	
	/**
	 *  注册激活的service层方法
	 * @throws UserException 
	 */
	public User activate(String urlActivationCode) throws UserException{
		//按照激活码查询，判断User对象是否为空，如果为空，为无效激活码，抛出异常
		
		User user=userMapper.queryByActivationCode(urlActivationCode);
		if(user==null){
			throw new UserException("invalid");
		//如果status为true，用户已经激活过了
		}else if(user.getStatus()){
			throw new UserException("havedone");
		}
		
		//调用dao设置status完成激活
		userMapper.updateStatus(user.getUid());
		return user;
	}
	
	/**
	 * 用户登录的service层方法
	 */
	public User doLogin(Map<String,String> loginMap){
		return userMapper.doLogin(loginMap);
	}


	/**
	 * @param map
	 *  用户修改密码的service层方法
	 */
	public void updateLoginpass(Map<String, String> map) {
		userMapper.updateLoginpass(map);
	}
	
}
