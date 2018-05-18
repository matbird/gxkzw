package com.gxkzw.gx.web.certi;

import java.util.ArrayList;
import java.util.List;

import com.gxkzw.gx.common.interceptor.FrontAuthInterceptor;
import com.gxkzw.gx.common.model.Certi;
import com.gxkzw.gx.common.model.Price;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;

@Before(FrontAuthInterceptor.class)
public class CertiController extends Controller{

	public void index() {
		setAttr("certiList", CertiService.me.getIndexData());
		render("index.html");
	}
	
	/**
	 * 报名
	 */
	public void enroll() {
		Certi certi = CertiService.me.findByIdWithExtraData(getParaToInt());
		List<Price> kzTypeList = PriceService.me.findKzTypeByCertiId(getParaToInt());
		List<Price> trainTypeList = PriceService.me.findTrainTypeByCertiId(getParaToInt());
		setAttr("kzTypeList", kzTypeList);
		setAttr("trainTypeList", trainTypeList);
		setAttr("certi", certi);
		render("fill_enroll_info.html");
	}
	
	/**
	 * 报名确认
	 */
	public void confirm() {
		keepPara();
		Integer certiId = getParaToInt("id");
		Certi certi = CertiService.me.findById(certiId);
		String pTrain = getPara("kzProject-train","");
		String pExam = getPara("kzProject-exam","");
		setAttr("kzProject", StrKit.isBlank(pExam) ? pTrain : pTrain + "+" + pExam);
		setAttr("certi", certi);
		setAttr("trainType", getParaToInt("trainType",0));
		setAttr("ckExam", getParaToInt("ckExam",0));
		render("confirm_enroll_info.html");
	}
	
	public void calcFee() {
		Integer ckExam = getParaToInt("ckExam", 0); // 考试选项是否选中
		Integer certiId = getParaToInt("id");
		Integer kzType = getParaToInt("kzType", 0);
		Integer trainType = getParaToInt("trainTye", 0);
		Price price = PriceService.me.findByKzAndTrainType(certiId, kzType, trainType);
		if(price != null) {
			float total = 0;
			String tip = "";
			if(price.getMerge() != 0) {
				total = price.getMerge();
				tip = price.getMerge()+"(总费用)";
			}else {
				if(ckExam == 1) {
					total = price.getExam()+price.getTrain();
					tip = price.getTrain()+"(培训费)+"+price.getExam()+"(考试费)";
				}else {
					total = price.getTrain();
					tip = price.getTrain()+"(培训费)";
				}
			}
			Ret ret = Ret.ok().set("fee_tip", tip)
						.set("fee", total)
						.set("descri", "备注:"+(StrKit.isBlank(price.getDescri()) ? "":price.getDescri()));
			renderJson(ret);
		}else {
			Ret ret = Ret.fail()
					.set("descri","费用计算失败,请联系管理员");
			renderJson(ret);
		}
	}
}
