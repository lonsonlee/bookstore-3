package com.risen.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.risen.bean.OrderItem;

/**
 * @author sen
 *  订单条目表
 */
@Repository
public interface OrderItemMapper {
	
	/**
	 * @param orderItemList
	 *   批量新增订单条目
	 */
	void addOrderItems(List<OrderItem> orderItemList);
}
