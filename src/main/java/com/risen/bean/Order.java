package com.risen.bean;

import java.util.List;

public class Order {
	private String oid;//主键
	
	private String orderTime;//下单时间
	
	private double total;//价格总计
	
	private int status;//订单状态-->   0:等待付款/1:已付款，准备发货/2:等待确认/3:交易完成/4:已取消   
	
	private String consignee;//收件人姓名
	
	private String phone;//收件人电话
	
	private String address;//收件人地址
	
	private User user;//外键（多对一）
	
	private List<OrderItem> orderItemList;//订单条目（一对多）

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
}
