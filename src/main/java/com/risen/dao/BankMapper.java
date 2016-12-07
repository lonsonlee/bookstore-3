package com.risen.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.risen.bean.Bank;
/**
 * 
 * @author sen
 *	 获取银行图标
 */
@Repository
public interface BankMapper {

	/**
	 * 获取银行图片的信息集合
	 */
	List<Bank> queryBankList();
	
}
