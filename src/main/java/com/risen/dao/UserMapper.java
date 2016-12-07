package com.risen.dao;

import java.util.Map;

import com.risen.bean.User;

/**
 * @author sen
 *	用户注册页面的dao层
 */
public interface UserMapper {
	
	/**
	 * @param loginname
	 * @return 
	 * 按照用户名查询，校验用户名是否注册
	 */
	int validateLoginname(String loginname);
	
	/**
	 * @param email
	 * @return
	 * 按照邮箱查询，校验邮箱是否已被注册
	 */
	int validateEmail(String email);
	
	/**
	 * 新增用户
	 */
	void addUser(User user);
	
	/**
	 * @return
	 *  按照激活码查询
	 */
	User queryByActivationCode(String urlActivationCode);

	/**
	 * 设置用户的激活状态status
	 */
	void updateStatus(String uid);
	
	/**
	 * @param loginMap 登录表单数据（用户名/邮箱、密码）
	 *  按照登录表单数据查询
	 */
	User doLogin(Map<String,String> loginMap);

	/**
	 * @param map
	 *  修改密码
	 */
	void updateLoginpass(Map<String, String> map);
}
