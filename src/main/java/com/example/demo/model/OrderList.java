package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Table(name = "order_list", schema = "public")
@NamedQuery(name = "OrderList.findAll", query = "SELECT u FROM OrderList u order by u.createdTime DESC")
public class OrderList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "objectid-generator")
	@GenericGenerator(name = "objectid-generator", strategy = "com.example.demo.common.ObjectIDGenerator")
	@Column(unique = true, nullable = false, length = 24)
	private String Id;
	
	@Column(length = 254, name = "order_list_name", nullable = true)
	private String orderListName;
	
	@Column(length = 500, name = "order_list_desc", nullable = true)
	private String orderListDesc;
	
	@Column(length = 254, name = "note", nullable = true)
	private String note;
	
	@Column(name = "quantity", nullable = true)
	private int totalQuantity;
	
	@Column(name = "completed_quantity", nullable = true)
	private float completedQuantity;
	
	@Column(name = "order_quantity", nullable = true)
	private int orderQuantity;
	
	@Column(name = "completed_percent", nullable = true)
	private float completedPercent;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customer_id", nullable=false)
	@JsonIgnore
	private Customer customer;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	
	public float getCompletedQuantity() {
		return completedQuantity;
	}

	public void setCompletedQuantity(float completedQuantity) {
		this.completedQuantity = completedQuantity;
	}

	public float getCompletedPercent() {
		return completedPercent;
	}

	public void setCompletedPercent(float completedPercent) {
		this.completedPercent = completedPercent;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
}
