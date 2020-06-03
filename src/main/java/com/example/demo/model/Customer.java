package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Table(name="customers", schema = "public")
@NamedQuery(name="Customer.findAll", query="SELECT u FROM Customer u order by u.customerName ASC")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "objectid-generator")
	@GenericGenerator(name = "objectid-generator", strategy = "com.example.demo.common.ObjectIDGenerator")
	@Column(unique = true, nullable = false, length = 24)
	private String Id;
	
	@Column(length = 254, name = "customer_name", nullable = true)
	private String customerName;
	
	@Column(length = 254, name = "customer_address", nullable = true)
	private String customerAddress;
	
	@Column(length = 254, name = "note", nullable = true)
	private String note;
	
	@Column(length = 254, name = "phone", nullable = true)
	private String phone;
	
	@Column(length = 254, name = "email", nullable = true)
	private String email;
	
	@Column(name = "is_order", nullable = true)
	private boolean isOrder;
	
	@Column(name = "is_required_design", nullable = true)
	private boolean isRequiredDesign;
	
	@Column(name = "order_list_quantity", nullable = true)
	private int orderListQuantity;
	
	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private List<OrderList> orderList;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<OrderList> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderList> orderList) {
		this.orderList = orderList;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isOrder() {
		return isOrder;
	}

	public void setOrder(boolean isOrder) {
		this.isOrder = isOrder;
	}

	public boolean isRequiredDesign() {
		return isRequiredDesign;
	}

	public void setRequiredDesign(boolean isRequiredDesign) {
		this.isRequiredDesign = isRequiredDesign;
	}

	public int getOrderListQuantity() {
		return orderListQuantity;
	}

	public void setOrderListQuantity(int orderListQuantity) {
		this.orderListQuantity = orderListQuantity;
	}
	
}
