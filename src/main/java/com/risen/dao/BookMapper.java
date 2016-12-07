package com.risen.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.risen.bean.Book;

/**
 * @author sen
 * 图书模块dao层
 *
 */

@Repository
public interface BookMapper {
	
	/**
	 * @param params
	 * @return
	 *  按照分类id查询(分类)
	 */
	List<Book> queryBookList(Map<String,Object> params);
	
	/**
	 * @param book
	 * @return
	 *   获取paging对象的totalItems参数
	 */
	int getTotalItems(Book book);
	
	/**
	 * @param bid
	 * @return
	 *  按照书id查询
	 */
	Book queryByBid(int bid);

	/**
	 * @param cid
	 * @return
	 *   查询主页默认的图书列表
	 */
	List<Book> queryHomePageList(int cid);
}
