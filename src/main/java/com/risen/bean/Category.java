package com.risen.bean;

import java.util.List;

public class Category {
	/*
	 * 主键
	 */
	private int cid;
	/*
	 * 分类名 
	 */
	private String cname;
	/*
	 * 父分类引用 (多对一)
	 */
	private Category parentCategory;
	/*
	 * 分类描述 
	 */
	private String cdesc;
	/*
	 *子分类集合(一对多) 
	 */
	private List<Category> subCategory;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Category getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}
	public String getCdesc() {
		return cdesc;
	}
	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}
	public List<Category> getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(List<Category> subCategory) {
		this.subCategory = subCategory;
	}
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + ", parentCategory=" + parentCategory + ", cdesc=" + cdesc
				+ ", subCategory=" + subCategory + "]";
	}
	
	
	
	
	
}
