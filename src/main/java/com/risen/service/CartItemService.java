package com.risen.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.risen.bean.CartItem;
import com.risen.dao.CartItemMapper;

/**
 * @author sen
 *	购物车模块service
 */
@Service
public class CartItemService {
	@Resource
	private CartItemMapper cartItemMapper;
	
	/**
	 * @param username
	 * @return
	 *  查询用户购物车所有条目
	 */
	public List<CartItem> queryByUsername(String username){
		return cartItemMapper.queryByUsername(username);
	}
	
	/**
	 * @param param
	 * @return
	 *  按照bid和用户名查询条目的service方法
	 */
	public CartItem queryByBidAndUsername(CartItem param){
		return cartItemMapper.queryByBidAndUsername(param);
	}
	
	/**
	 * @param param
	 *  添加购物车条目service方法
	 */
	public void addCartItem(CartItem param){
		cartItemMapper.addCartItem(param);
	}
	
	/**
	 * @param 
	 *  更新条目的数量quantity
	 */
	public void updateQuantity(CartItem data){
		cartItemMapper.updateQuantity(data);
	}
	
	
	/**
	 * @param arr
	 * @return
	 *  将String数组转化成int数组
	 */
	private int[] fromStrArrToIntArr(String[] arr){
		int[] params=new int[arr.length];
		for(int i=0;i<arr.length;i++){
			params[i]=Integer.parseInt(arr[i]);
		}
		return params;
	}
	
	/**
	 * @param arr
	 *  按照id数组批量删除条目
	 */
	public void deleteItems(String[] arr) {
		int[] params=fromStrArrToIntArr(arr);
		
		cartItemMapper.deleteByCartItemIds(params);
	}
	
	/**
	 * @param arr
	 * @return
	 *  按照id数组查询
	 */
	public List<CartItem> queryByCartItemIds(String[] arr){
		int[] params=fromStrArrToIntArr(arr);
		return cartItemMapper.queryByCartItemIds(params);
	}
}
