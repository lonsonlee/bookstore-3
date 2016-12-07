package com.risen.util;

import javax.servlet.http.HttpServletRequest;

public class Paging {
	/*
	 * 1.当前页码
	 */
	private int currentPage;
	/*
	 * 2.每页最大条数
	 */
	private int onePageMax=Iconstant.ONE_PAGE_MAX;
	/*
	 * 3.总页数
	 */
	private int totalPages;
	/*
	 * 4.查询出的条目总数
	 */
	private int totalItems; 
	/*
	 * 5.请求的url路径
	 */
	private String url;
	/*
	 * 6.limit参数1
	 */
	private int limitIndex;
	/*
	 * 7.limit参数2
	 */
	private int limitSpan;
	
	
	/**
	 * @param request
	 * @return
	 *  获取paging对象的url参数
	 */
	public String acquireUrl(HttpServletRequest request){

		String url=request.getRequestURI()+"?"+request.getQueryString();
		/*
		 * 如果url中存在currentPage参数，则截取掉
		 */
		int index=url.indexOf("&currentPage=");
		if(index!=-1){
			url=url.substring(0,index);
		}
		return url;
		
	}
	
	
	/**
	 * 计算limit参数
	 */
	private void calculateLimit(){
		limitIndex=(currentPage-1)*onePageMax;
		limitSpan=onePageMax;
	}
	
	/**
	 * 计算totalPages
	 */
	private void calculate(){
		totalPages=totalItems/onePageMax;
		int plus=(totalItems%onePageMax==0?0:1);
		totalPages=totalPages+plus;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		calculateLimit();
	}

	public int getOnePageMax() {
		return onePageMax;
	}

	public void setOnePageMax(int onePageMax) {
		this.onePageMax = onePageMax;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
		calculate();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getLimitIndex() {
		return limitIndex;
	}

	public void setLimitIndex(int limitIndex) {
		this.limitIndex = limitIndex;
	}

	public int getLimitSpan() {
		return limitSpan;
	}

	public void setLimitSpan(int limitSpan) {
		this.limitSpan = limitSpan;
	}
	
	
}
