package com.gxkzw.gx.web.my.order;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class EnrollValidator extends Validator{

	@Override
	protected void validate(Controller c) {
		
	}

	@Override
	protected void handleError(Controller c) {
		c.renderJson();
	}

}
