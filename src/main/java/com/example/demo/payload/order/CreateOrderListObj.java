package com.example.demo.payload.order;

public class CreateOrderListObj {
	private String orderListName;
	private String orderListDesc;
	private String orderListNote;
	private String customerId;
	public String getOrderListName() {
		return orderListName;
	}
	public void setOrderListName(String orderListName) {
		this.orderListName = orderListName;
	}
	public String getOrderListDesc() {
		return orderListDesc;
	}
	public void setOrderListDesc(String orderListDesc) {
		this.orderListDesc = orderListDesc;
	}
	public String getOrderListNote() {
		return orderListNote;
	}
	public void setOrderListNote(String orderListNote) {
		this.orderListNote = orderListNote;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
}
