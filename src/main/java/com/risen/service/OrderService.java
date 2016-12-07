package com.risen.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.risen.bean.Bank;
import com.risen.bean.CartItem;
import com.risen.bean.Order;
import com.risen.bean.OrderItem;
import com.risen.dao.BankMapper;
import com.risen.dao.OrderItemMapper;
import com.risen.dao.OrderMapper;
import com.risen.util.Paging;

/**
 * @author sen
 *  订单模块service
 */
@Service
@Transactional  
public class OrderService {
	@Resource
	private OrderMapper orderMapper;
	
	@Resource
	private OrderItemMapper orderItemMapper;
	@Resource
	private CartItemService cartItemService;
	
	@Resource
	private BankMapper bankMapper;

	/**
	 * @param order
	 *  生成订单
	 */
	public void generateOrder(Order order,String[] arr ) {
		
		//1.添加订单
		orderMapper.addOrder(order);
		
		/*
		 * 创建orderItemList
		 */
		List<CartItem> data=cartItemService.queryByCartItemIds(arr);
		
		List<OrderItem> orderItemList=new ArrayList<>();
		CartItem cartItem=null;
		for(int i=0;i<data.size();i++){
			cartItem=data.get(i);
			OrderItem orderItem=new OrderItem();
			
			orderItem.setBook(cartItem.getBook());
			
			orderItem.setOrder(order);
			
			orderItem.setQuantity(cartItem.getQuantity());
			
			orderItem.setSubtotal(cartItem.getSubtotal());
			
			orderItemList.add(orderItem);
		}
		
		//2.添加订单条目
		orderItemMapper.addOrderItems(orderItemList);
		
		//3.删除购物车条目
		cartItemService.deleteItems(arr);
	}

	/**
	 * @param loginname
	 * @return
	 *  按照用户名查询用户订单列表
	 */
	public List<Order> queryByLoginname(String loginname,Paging paging) {
		Map<String,Object> params=new HashMap<>();
		params.put("loginname", loginname);
		params.put("paging", paging);
		
		int totalItems=orderMapper.countOrder(loginname);
		paging.setTotalItems(totalItems);
		
		return orderMapper.queryByLoginname(params);
	}

	/**
	 * @param oid
	 * 按照oid删除order和orderItem
	 */
	public void deleteOrder(String oid) {
		orderMapper.deleteByOid(oid);
	}
	
	/**
	 * 
	 * @param oid
	 *   设置订单状态
	 */
	public void setOrderStatus(String oid,int status) {
		Order order =new Order();
		order.setOid(oid);
		order.setStatus(status);
		orderMapper.updateOrderStatus(order);
	}
	
	/**
	 * 获取银行图片的信息
	 */
	public List<Bank> queryBankList() {
		return bankMapper.queryBankList();
	}
	
	/**
	 * @param oid
	 * @return
	 *  查看订单状态
	 */
	public int queryOrderStatus(String oid){
		return orderMapper.queryOrderStatus(oid);
	}
}
