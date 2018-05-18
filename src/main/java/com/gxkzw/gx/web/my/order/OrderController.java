package com.gxkzw.gx.web.my.order;

import java.util.List;

import com.gxkzw.gx.common.interceptor.FrontAuthInterceptor;
import com.gxkzw.gx.common.model.Account;
import com.gxkzw.gx.common.model.Certi;
import com.gxkzw.gx.common.model.Order;
import com.gxkzw.gx.common.model.Price;
import com.gxkzw.gx.web.certi.CertiService;
import com.gxkzw.gx.web.certi.PriceService;
import com.gxkzw.gx.web.login.LoginService;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.LogKit;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;

@Before(FrontAuthInterceptor.class)
public class OrderController extends Controller{
	
	private static OrderService osrv = OrderService.me;
	
	public void index() {
		Account account = getAttr(LoginService.loginAccountCacheName);
		List<Order> orderList = osrv.findOrdersByAccountId(account.getId());
		setAttr("orderList",orderList);
		render("index.html");
	}
	
	@Before(EnrollValidator.class)
	@ActionKey("/order/submit")
	public void submit() {
		Account account = getAttr(LoginService.loginAccountCacheName);
		Integer certiId = getParaToInt("certiId");
		Integer kzType = getParaToInt("kzType",0);
		Integer ckExam = getParaToInt("ckExam");
		Integer trainType = getParaToInt("trainType", 0);
		Price price = PriceService.me.findByKzAndTrainType(certiId, kzType, trainType);
		if(price != null) {
			float total = 0;
			if(price.getMerge() != 0) {
				total = price.getMerge();
			}else {
				if(ckExam == 1) {
					total = price.getExam()+price.getTrain();
				}else {
					total = price.getTrain();
				}
			}
			String payment = String.valueOf(total);
			Certi certi = CertiService.me.findById(certiId);
			Ret ret = osrv.submit(certi.getName(), payment, account.getId(), certiId, getPara("name"), 
					getPara("gender"), getPara("age"), getPara("idNumber"), getPara("phone"), 
					getPara("qq"), getPara("addr"), getPara("kzLevel",""), getPara("kzCourse",""), 
					getPara("kzType"), getPara("kzProject"));
			renderJson(ret);
		}else {
			renderJson(Ret.fail("msg", "订单创建失败,请联系管理员"));
		}
	}
	
	@ActionKey("/order/pay")
	public void pay() {
		setAttr("orderId", getPara("orderId"));
		render("pay.html");
	}
}
