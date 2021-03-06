package com.gxkzw.gx.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseOrder<M extends BaseOrder<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setContent(java.lang.String content) {
		set("content", content);
	}

	public java.lang.String getContent() {
		return get("content");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}

	public java.lang.Integer getStatus() {
		return get("status");
	}

	public void setPayment(java.lang.String payment) {
		set("payment", payment);
	}

	public java.lang.String getPayment() {
		return get("payment");
	}

	public void setAccountId(java.lang.Integer accountId) {
		set("accountId", accountId);
	}

	public java.lang.Integer getAccountId() {
		return get("accountId");
	}

	public void setCreatedAt(java.util.Date createdAt) {
		set("createdAt", createdAt);
	}

	public java.util.Date getCreatedAt() {
		return get("createdAt");
	}

	public void setPayedAt(java.util.Date payedAt) {
		set("payedAt", payedAt);
	}

	public java.util.Date getPayedAt() {
		return get("payedAt");
	}

}
