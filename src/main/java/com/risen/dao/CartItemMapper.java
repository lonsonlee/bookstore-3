package com.risen.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.risen.bean.CartItem;

/**
 * @author sen
 *	购物车模块dao
 */
@Repository
public interface CartItemMapper {
	
	/**
	 * @return
	 *  按照用户名查询用户的购物车
	 */
	List<CartItem> queryByUsername(String username);
	
	
	/**
	 * @param cartItem
	 * @return
	 *  按照bid和username查询购物车条目
	 */
	CartItem queryByBidAndUsername(CartItem param);
	
	/**
	 * @param param
	 *  添加购物车条目
	 */
	void addCartItem(CartItem param);
	
	/**
	 * @param data
	 *  用service查询出的data 更改其quantity属性后  作为查询参数
	 *  更改条目的数量quantity
	 */
	void updateQuantity(CartItem param);


	/**
	 * @param params
	 *  按id批量删除条目
	 */
	void deleteByCartItemIds(int[] params);
	
	/**
	 * @param params
	 * @return
	 *  按照id的数组查询
	 */
	List<CartItem> queryByCartItemIds(int[] params);
}
