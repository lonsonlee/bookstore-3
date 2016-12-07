package com.risen.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.risen.bean.Book;
import com.risen.dao.BookMapper;
import com.risen.util.Paging;

/**
 * @author sen
 *	图书模块service层
 */

@Service
public class BookService {
	
	@Resource
	private BookMapper bookMapper;
	
	
	/**
	 * @param cid
	 * @param paging
	 * @return
	 *  
	 */
	public List<Book> queryBookList(Book book,Paging paging){
		//sql语句需要的参数map，包括查询条件(封装到book对象中)和分页的limit参数(封装到paging对象中)
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("book", book);
		params.put("paging",paging);//setCurrentPage方法自动计算了limit参数（controller已调用）
		List<Book> bookList=bookMapper.queryBookList(params);//得到查询结果集
		
		//设置paging的totalItems值
		paging.setTotalItems(bookMapper.getTotalItems(book));
		
		return bookList;
	}
	
	
	/**
	 * @param bid
	 * @return
	 */
	public Book queryByBid(int bid){
		return bookMapper.queryByBid(bid);
		
	}

	
	/**
	 * @param cid
	 * @return
	 *  查询主页默认的图书列表
	 */
	public List<Book> queryHomePageList(int cid) {
		return bookMapper.queryHomePageList(cid);
	}
}
