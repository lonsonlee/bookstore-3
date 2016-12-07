package com.risen.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.risen.bean.Order;

/**
 * @author sen
 *  订单表dao
 */
@Repository
public interface OrderMapper {
	
	/**
	 * @param order
	 *  新增订单
	 */
	void addOrder(Order order);

	/**
	 * @param loginname
	 * @return 
	 *  按照用户名查询用户订单列表
	 */
	List<Order> queryByLoginname(Map<String,Object> params);
	
	
	/**
	 * @param loginname
	 * @return
	 *  计算用户订单总数
	 */
	int countOrder(String loginname);

	/**
	 * @param oid
	 *  按照oid删除order和orderItem
	 */
	void deleteByOid(String oid);

	/**
	 * @param order
	 *  更新订单状态
	 */
	void updateOrderStatus(Order order);
	
	/**
	 * @param oid
	 * @return
	 *  查看订单状态
	 */
	int queryOrderStatus(String oid);
}
