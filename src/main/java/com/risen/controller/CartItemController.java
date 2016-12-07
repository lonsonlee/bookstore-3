package com.risen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.risen.bean.Book;
import com.risen.bean.CartItem;
import com.risen.bean.User;
import com.risen.service.CartItemService;

/**
 * @author sen
 *  购物车模块控制层
 */

@Controller
@RequestMapping("cart")
public class CartItemController {
	@Resource
	private CartItemService cartItemService;
	
	/**
	 * @param request
	 * @return
	 *  查看购物车
	 */
	@RequestMapping("cart")
	public String toMyCart(HttpServletRequest request){
		String username=(String) request.getSession().getAttribute("loginname");
		request.setAttribute("cartItemList",cartItemService.queryByUsername(username));
		return "front/cart";
	}
	
	/**
	 * @param request
	 * @return
	 *  “加入购物车”处理逻辑
	 */
	@RequestMapping("addToCart")
	public String addToCart(HttpServletRequest request){
		
		//获取查询参数，查询数据库该条目是否存在，如果存在则修改条目数量，不存在则新建条目
		String username=(String) request.getSession().getAttribute("loginname");
		String bid=request.getParameter("bid");
		
		Book book=new Book();
		book.setBid(Integer.parseInt(bid));
		
		User user =new User();
		user.setLoginname(username);
		
		CartItem param=new CartItem();
		param.setBook(book);
		param.setUser(user);
		
		//调用service查询
		CartItem data=cartItemService.queryByBidAndUsername(param);
		
		//传入的quantity
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		
		//判断
		if(data==null){
			//如果等于空，添加条目
			
			param.setQuantity(quantity);
			
			cartItemService.addCartItem(param);
		}else{
			//如果不等于空，更新条目数量
			data.setQuantity(quantity+data.getQuantity());
			cartItemService.updateQuantity(data);
		}
		
		return toMyCart(request);
	}
	
	/**
	 * @param cartItemId
	 * @param quantity
	 * @param response
	 *  修改条目数量（- +）
	 */
	@RequestMapping("updateQ")
	public void updateQuantity(@RequestParam String cartItemId,@RequestParam String quantity,HttpServletResponse response){
		CartItem param =new CartItem();
		
		param.setCartItemId(Integer.parseInt(cartItemId));
		param.setQuantity(Integer.parseInt(quantity));
		cartItemService.updateQuantity(param);
		
		response.setContentType("application/json");
		try {
			PrintWriter out=response.getWriter();
			out.println(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 单条或批量删除购物车条目
	 */
	@RequestMapping("deleteItems")
	public String deleteItems(HttpServletRequest request,@RequestParam String ids){
		//获取参数数组
		String[] arr =ids.split(",");
		
		//调用service
		cartItemService.deleteItems(arr);
		return toMyCart(request);
	}
	
	/**
	 * @param ids
	 * @param total
	 * @param request
	 * @return
	 *  由结算按钮 跳转到下订单页面
	 */
	@RequestMapping("placeod")
	public String placeOrder(@RequestParam String ids,@RequestParam String total,HttpServletRequest request){
		
		String[] arr=ids.split(",");
		List<CartItem> cartItemList=cartItemService.queryByCartItemIds(arr);
		
		request.setAttribute("total",total);
		request.setAttribute("cartItemList",cartItemList);
		request.setAttribute("ids",ids);
		return "front/placeod";
	}
	
}
