package com.risen.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.risen.bean.Order;
import com.risen.bean.User;
import com.risen.service.OrderService;
import com.risen.util.Paging;
import com.risen.util.PaymentUtil;
import com.risen.util.UUIDGenerator;

/**
 * @author sen
 * 订单模块控制层
 */
@Controller
@RequestMapping("order")
public class OrderController {
	@Resource
	private OrderService orderService;
	
	
	/**
	 * @return
	 *  生成订单
	 */
	@RequestMapping("generateod")
	public String generateOrder(HttpServletRequest request){
		
		/*
		 * 创建order
		 */
		//获取参数
		String totalStr=request.getParameter("total");
		double total=Double.valueOf(totalStr);
		String consignee=request.getParameter("consignee");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		String loginname=(String) request.getSession().getAttribute("loginname");
		//补齐参数
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String orderTime=sdf.format(new Date());
		String oid=UUIDGenerator.getLong();
		//设值
		User user=new User();
		user.setLoginname(loginname);
		
		Order order=new Order();
		order.setOid(oid);
		order.setAddress(address);
		order.setConsignee(consignee);
		order.setOrderTime(orderTime);
		order.setPhone(phone);
		order.setStatus(0);
		order.setTotal(total);
		order.setUser(user);
		
		/*
		 * 创建orderItemList需要的参数
		 */
		String[] arr=request.getParameter("ids").split(",");
		
		
		/*
		 * 调用service
		 */
		orderService.generateOrder(order,arr);
		
		return toPrePayment(oid, totalStr, request);
	}
	
	
	/**
	 * @param request
	 * @return
	 *  查询我的订单
	 */
	@RequestMapping("order")
	public String toMyOrder(HttpServletRequest request){
		/*
		 * 获取当前用户的用户名
		 */
		String loginname=(String) request.getSession().getAttribute("loginname");
		
		/*
		 * 获取要查询的页码，如果为空，默认查询第一页
		 */
		String currentPage=request.getParameter("currentPage");
		if(currentPage==null){
			currentPage="1";
		}
		Paging paging=new Paging();
		paging.setCurrentPage(Integer.parseInt(currentPage));
		
		/*
		 * 根据用户名和分页条件查询出 列表
		 */
		List<Order> orderList=orderService.queryByLoginname(loginname,paging);
		
		/*
		 * 设置分页对象的url
		 */
		paging.setUrl(paging.acquireUrl(request));
		
		/*
		 * 页面需要的数据
		 */
		Map<String,Object> data=new HashMap<>();
		data.put("orderList",orderList);
		data.put("paging",paging);
		
		/*
		 * 跳转
		 */
		request.setAttribute("data",data);
		
		return "front/order";
	}
	
	/**
	 * @return
	 *  删除订单
	 */
	@RequestMapping("deleteod")
	public String deleteOrder(HttpServletRequest request,@RequestParam String oid){
		orderService.deleteOrder(oid);
		
		return toMyOrder(request);
	}
	
	/**
	 * @param request
	 * @param oid
	 * @return
	 *  取消订单
	 */
	@RequestMapping("cancelod")
	public String cancelOrder(HttpServletRequest request,@RequestParam String oid){
		orderService.setOrderStatus(oid,4);
		
		return toMyOrder(request);
	}
	
	/**
	 * 
	 * @param oid
	 * @param total
	 * @return
	 *  重复代码  跳转到准备支付页面
	 */
	private String toPrePayment(String oid,String total,HttpServletRequest request){
		
		request.setAttribute("bankList",orderService.queryBankList());
		request.setAttribute("oid", oid);
		request.setAttribute("total", total);
		return "front/prePayment";
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 *  从订单列表的支付按钮跳转到准备支付页面
	 */
	@RequestMapping("toPrePayment")
	public String orderToPrePayment(HttpServletRequest request){
		String oid=request.getParameter("oid");
		String total=request.getParameter("total");
		return toPrePayment(oid, total,request);
	}
	
	/**
	 * 读取payment.properties配置文件
	 */
	private Properties getPaymentProperties(){
		Properties props=new Properties();
		try {
			props.load(this.getClass().getClassLoader().getResourceAsStream("payment.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return props;
	}
	
	/**
	 * 利用第三方支付平台 易宝  完成支付
	 */
	@RequestMapping("pay")
	public void pay(HttpServletRequest request,HttpServletResponse response){
		/*
		 * 获取配置文件
		 */
		Properties props=getPaymentProperties();
		
		/*
		 *  获取易宝支付需要的13个参数
		 */
		String p0_Cmd = "Buy";//业务类型，固定值Buy
		String p1_MerId = props.getProperty("p1_MerId");//商号编码，在易宝的唯一标识
		String p2_Order = request.getParameter("oid");//订单编码
		String p3_Amt = "0.01";//支付金额
		String p4_Cur = "CNY";//交易币种，固定值CNY
		String p5_Pid = "";//商品名称
		String p6_Pcat = "";//商品种类
		String p7_Pdesc = "";//商品描述
		String p8_Url = props.getProperty("p8_Url");//易宝在支付成功后，反馈的地址
		String p9_SAF = "";//送货地址
		String pa_MP = "";//扩展信息
		String pd_FrpId = request.getParameter("bank");//支付通道
		String pr_NeedResponse = "1";//应答机制，固定值1
		
		/*
		 * 2. 计算hmac
		 * 需要13个参数
		 * 需要keyValue
		 * 需要加密算法
		 */
		String keyValue = props.getProperty("keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);
		
		/*
		 * 3. 重定向到易宝的支付网关
		 */
		StringBuilder sb = new StringBuilder("https://www.yeepay.com/app-merchant-proxy/node");
		sb.append("?").append("p0_Cmd=").append(p0_Cmd);
		sb.append("&").append("p1_MerId=").append(p1_MerId);
		sb.append("&").append("p2_Order=").append(p2_Order);
		sb.append("&").append("p3_Amt=").append(p3_Amt);
		sb.append("&").append("p4_Cur=").append(p4_Cur);
		sb.append("&").append("p5_Pid=").append(p5_Pid);
		sb.append("&").append("p6_Pcat=").append(p6_Pcat);
		sb.append("&").append("p7_Pdesc=").append(p7_Pdesc);
		sb.append("&").append("p8_Url=").append(p8_Url);
		sb.append("&").append("p9_SAF=").append(p9_SAF);
		sb.append("&").append("pa_MP=").append(pa_MP);
		sb.append("&").append("pd_FrpId=").append(pd_FrpId);
		sb.append("&").append("pr_NeedResponse=").append(pr_NeedResponse);
		sb.append("&").append("hmac=").append(hmac);
		
		try {
			response.sendRedirect(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 支付反馈
	 */
	@RequestMapping("feedback")
	public String paymentFeedBack(HttpServletRequest request,HttpServletResponse response){
		/*
		 * 获取配置文件
		 */
		Properties props=getPaymentProperties();
		
		/*
		 * 获取参数
		 */
		/*
		 * 1. 获取12个参数
		 */
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");
		String hmac = request.getParameter("hmac");
		
		/*
		 * 获取keyValue
		 */
		String keyValue=props.getProperty("keyValue");
		
		/*
		 * 判断调用此方法的url的hmac
		 */
		boolean flag = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId,
				r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType,
				keyValue);
		if(!flag) {
			request.setAttribute("status", "paymentFail");
			request.setAttribute("paymentFail", "无效的签名，支付失败");
			return "front/paymentResult";
		}
		/*
		 * 修改订单状态，返回
		 */
		if(r1_Code.equals("1")) {
			if(orderService.queryOrderStatus(r6_Order)!=1){
				orderService.setOrderStatus(r6_Order, 1);
			}
			if(r9_BType.equals("1")) {
				request.setAttribute("status", "paymentSuccess");
				return "front/paymentResult";				
			} else if(r9_BType.equals("2")) {
				try {
					response.getWriter().print("success");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	@RequestMapping("test")
	public String test(){
		return "front/test";
	}
}
