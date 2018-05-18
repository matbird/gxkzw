package com.gxkzw.gx.common.model;

import com.gxkzw.gx.common.model.base.BaseOrder;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Order extends BaseOrder<Order> {
	
	public static final int STATUS_NOT_PAY = 0; // 未支付
	public static final int STATUS_PAYING = 1; // 支付待确认
	public static final int STATUS_PAYED = 2; // 支付完成
	public static final int STATUS_CANCEL = 3; // 已取消
	
}
