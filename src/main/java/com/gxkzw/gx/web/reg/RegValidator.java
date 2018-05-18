package com.gxkzw.gx.web.reg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gxkzw.gx.common.kit.ValidatorKit;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.Validator;

public class RegValidator extends Validator{

	@Override
	protected void validate(Controller c) {
		setShortCircuit(true);
		
		// 校验姓名
		validateRequired("realName", "realNameMsg", "姓名不能为空");
		validateString("realName", 1, 10, "realNameMsg", "姓名不能超过10个字");
		String realName = c.getPara("realName").trim();
		if (realName.contains("@") || realName.contains("＠")) { // 全角半角都要判断
			addError("realNameMsg", "姓名不能包含 \"@\" 字符");
		}
		if (realName.contains(" ") || realName.contains("　")) {
			addError("realNameMsg", "姓名不能包含空格");
		}
		Ret ret = ValidatorKit.validateRealName(realName);
		
		if (ret.isFail()) {
			addError("realNameMsg", ret.getStr("msg"));
		}
		
		// 校验身份证号
		validateRequired("idNumber", "idNumberMsg", "身份证号不能为空");
		Ret ret2 = null;
		try {
			ret2 = ValidatorKit.validateIdNumber(c.getPara("idNumber"));
		} catch (Exception e) {
			addError("idNumberMsg", "身份证校验失败,请联系管理员");
		}
		if(ret2.isFail()) {
			addError("idNumberMsg", ret2.getStr("msg"));
		}
		
		// 校验邮箱
		validateRequired("userName", "userNameMsg", "邮箱不能为空");
		validateEmail("userName", "userNameMsg", "邮箱格式不正确");
		if(RegService.me.isUserNameExists(c.getPara("userName"))) {
			addError("userNameMsg", "邮箱已被注册");
		}
		
		// 校验密码
		validateString("password", 6, 100, "passwordMsg", "密码长度不能小于6");

		// 验证码校验放在最后，在 shortCircuit 模式下避免刷新验证码，也即避免重复输入验证码
		validateCaptcha("captcha", "captchaMsg", "验证码不正确");
	}

	@Override
	protected void handleError(Controller c) {
		c.renderJson();
	}
}
