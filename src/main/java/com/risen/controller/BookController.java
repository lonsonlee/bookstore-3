package com.risen.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.risen.bean.Book;
import com.risen.service.BookService;
import com.risen.util.Paging;

/**
 * @author sen
 *	图书模块控制层
 */

@Controller
@RequestMapping("book")
public class BookController {
	
	@Resource
	private BookService bookService;
	
	/**
	 * @return
	 *  按照分类获取图书列表(分页)
	 */
	@RequestMapping("list")
	public String queryBookList(HttpServletRequest request){
		/*
		 * 1.获取查询条件参数：查询条件、查询页码（如果页码参数为空，则设置为1）
		 */
		String currentPage=request.getParameter("currentPage");
		if(currentPage==null){
			currentPage="1";
		}
		
		Book book=new Book();
		
		//按书分类查询
		String cid=request.getParameter("cid");
		if(cid!=null&&!cid.trim().isEmpty()){
			book.setCid(Integer.parseInt(cid));
		}
		
		//按书名模糊查询
		String bname=request.getParameter("bname");
		if(bname!=null&&!bname.trim().isEmpty()){
			book.setBname(bname);
		}
		
		//按作者查询
		String author=request.getParameter("author");
		if(author!=null&&!author.trim().isEmpty()){
			book.setAuthor(author);
		}
		
		//按出版社查询
		String press=request.getParameter("press");
		if(press!=null&&!press.trim().isEmpty()){
			book.setPress(press);
		}
		
		
		Paging paging=new Paging();
		paging.setCurrentPage(Integer.parseInt(currentPage));
		
		List<Book> bookList=bookService.queryBookList(book,paging);	
		
		/*
		 * 2.设置paging的url
		 */
		paging.setUrl(paging.acquireUrl(request));
		
		/*
		 * 3.页面需要的数据
		 */
		
		Map<String,Object> data=new HashMap<>();
		data.put("bookList", bookList);
		data.put("paging", paging);
		
		/*
		 * 4.返回
		 */
		request.setAttribute("data",data);
		return "front/main";
	}
	
	/**
	 * @param request
	 * @return
	 *  查询主页默认的图书列表
	 */
	@RequestMapping("home")
	public String queryHomePageList(HttpServletRequest request){
		List<Book> homeList=bookService.queryHomePageList(105);
		request.setAttribute("homeList", homeList);
		return "front/home";
	}
	
	/**
	 * @param bid
	 * @param request
	 * @return
	 *  获取图书得详细信息
	 */
	@RequestMapping("detail")
	public String getDetail(@RequestParam String bid,HttpServletRequest request){
		Book book=bookService.queryByBid(Integer.parseInt(bid));
		request.setAttribute("book",book);
		return "front/detail";
	}
	
	@RequestMapping("test")
	public String test(){
		return "front/test";
	}
}
