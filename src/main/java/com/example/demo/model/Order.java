package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "orders", schema = "public")
@NamedQuery(name = "Order.findAll", query = "SELECT u FROM Order u order by u.createdTime DESC")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "objectid-generator")
	@GenericGenerator(name = "objectid-generator", strategy = "com.example.demo.common.ObjectIDGenerator")
	@Column(unique = true, nullable = false, length = 24)
	private String Id;
	
	@Column(length = 254, name = "order_name", nullable = true)
	private String orderName;
	
	@Column(length = 5000, name = "order_desc", nullable = true)
	private String orderDesc;
	
	@Column(length = 5000, name = "note", nullable = true)
	private String note;
	
	@Column(length = 5000, name = "image_link", nullable = true)
	private String imageLink;
	
	@Column(name = "quantity", nullable = true)
	private int quantity;
	
	@Column(name = "price", nullable = true)
	private int price;

	@Column(name = "total_amount", nullable = true)
	private int totalAmount;
	
	@Column(length = 254, name = "status", nullable = true)
	private String status;
	
	@Column(name = "in_progess", nullable = true)
	private boolean inProgess;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order_list_id", nullable=false)
	private OrderList orderList;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_time", nullable = true)
	private Date endTime;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_phase_id", nullable = true)
	private OrderPhase phases;
	
	@OneToMany(mappedBy = "order")
	@JsonIgnore
	private List<OrderPhaseWorker> phaseWorkers;
	
	@OneToMany(mappedBy = "order")
	@JsonIgnore
	private List<DesignRequired> requireds;
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	

	public OrderList getOrderList() {
		return orderList;
	}

	public void setOrderList(OrderList orderList) {
		this.orderList = orderList;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public OrderPhase getPhases() {
		return phases;
	}

	public void setPhases(OrderPhase phases) {
		this.phases = phases;
	}

	public List<OrderPhaseWorker> getPhaseWorkers() {
		return phaseWorkers;
	}

	public void setPhaseWorkers(List<OrderPhaseWorker> phaseWorkers) {
		this.phaseWorkers = phaseWorkers;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public boolean isInProgess() {
		return inProgess;
	}

	public void setInProgess(boolean inProgess) {
		this.inProgess = inProgess;
	}
}
