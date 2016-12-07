package com.risen.bean;

import java.math.BigDecimal;

public class CartItem {
	
	private int cartItemId;//主键
	
	private int quantity;//数量
	
	private Book book;//图书信息
	
	private User user;//用户信息
	
	/**
	 * 计算小计
	 * @return
	 */
	public double getSubtotal() {
		BigDecimal cp=new BigDecimal(book.getCurrPrice()+"");
		BigDecimal q=new BigDecimal(quantity+"");
		BigDecimal st=cp.multiply(q);
		return st.doubleValue();
	}


	public int getCartItemId() {
		return cartItemId;
	}
	
	
	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
