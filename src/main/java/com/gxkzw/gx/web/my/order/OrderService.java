package com.gxkzw.gx.web.my.order;

import java.util.Date;
import java.util.List;

import com.gxkzw.gx.common.model.Order;
import com.gxkzw.gx.common.model.OrderInfo;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;

public class OrderService {
	
	public static final OrderService me = new OrderService();
	private static Order dao = new Order().dao();
	private static OrderInfo infoDao = new OrderInfo().dao();
	
	public List<Order> findOrdersByAccountId(int accountId){
		return dao.find("select * from `order` where accountId=?", accountId);
	}
	
	public Ret submit(String content,String payment,int accountId,int certiId,String eName,
			String eGender,String eAge,String eIdNumber,String ePhone,String eQQ,String eAddr,
			String eKzLevel,String eKzCourse,String eKzType,String eKzProject) {
		String orderId = StrKit.getRandomUUID();
		Ret ret = saveOrder(orderId, content, payment, accountId);
		if(ret.isOk()) {
			Ret ret2 = saveOrderInfo(orderId, certiId, eName, eGender, eAge, eIdNumber, ePhone, 
					eQQ, eAddr, eKzLevel, eKzCourse, eKzType, eKzProject);
			ret2.set("orderId", orderId);
			return ret2;
		}
		return ret;
	}
	
	public Ret saveOrder(String id,String content,String payment,int accountId) {
		Order order = new Order();
		order.setId(id);
		order.setAccountId(accountId);
		order.setContent(content);
		order.setStatus(Order.STATUS_NOT_PAY);
		order.setPayment(payment);
		order.setCreatedAt(new Date());
		if(order.save()) {
			return Ret.ok("msg","订单创建成功");
		}else {
			return Ret.fail("msg","订单创建失败");
		}
	}
	
	public Ret saveOrderInfo(String orderId,int certiId,String eName,String eGender,String eAge,
			String eIdNumber,String ePhone,String eQQ,String eAddr,String eKzLevel,
			String eKzCourse,String eKzType,String eKzProject) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrderId(orderId);
		orderInfo.setCertiId(certiId);
		orderInfo.setEName(eName);
		orderInfo.setEGender(eGender);
		orderInfo.setEAge(eAge);
		orderInfo.setEIdNumber(eIdNumber);
		orderInfo.setEPhone(ePhone);
		orderInfo.setEQQ(eQQ);
		orderInfo.setEAddr(eAddr);
		orderInfo.setEKzLevel(eKzLevel);
		orderInfo.setEKzCourse(eKzCourse);
		orderInfo.setEKzType(eKzType);
		orderInfo.setEKzProject(eKzProject);
		if(orderInfo.save()) {
			return Ret.ok("msg", "订单详情创建成功");
		}else {
			return Ret.fail("msg", "订单详情创建失败");
		}
	}
}
