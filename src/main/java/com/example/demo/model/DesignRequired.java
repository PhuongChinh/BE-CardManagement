package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Table(name = "design_required", schema = "public")
@NamedQuery(name = "DesignRequired.findAll", query = "SELECT u FROM DesignRequired u order by u.createdTime DESC")
public class DesignRequired  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "objectid-generator")
	@GenericGenerator(name = "objectid-generator", strategy = "com.example.demo.common.ObjectIDGenerator")
	@Column(unique = true, nullable = false, length = 24)
	private String Id;
	
	@Column(length = 254, name = "required_name", nullable = true)
	private String requiredName;
	
	@Column(length = 5000, name = "required_desc", nullable = true)
	private String requiredDesc;
	
	@Column(length = 5000, name = "image_link", nullable = true)
	private String imageLink;
	
	@Column(length = 5000, name = "note", nullable = true)
	private String note;
	
	@Column(name = "is_completed", nullable = true)
	private boolean isCompleted;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="completed_time", nullable = true)
	private Date completedTime;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "completed_by", nullable = true)
	private User completedBy;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = true)
	private Order order;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getRequiredName() {
		return requiredName;
	}

	public void setRequiredName(String requiredName) {
		this.requiredName = requiredName;
	}

	public String getRequiredDesc() {
		return requiredDesc;
	}

	public void setRequiredDesc(String requiredDesc) {
		this.requiredDesc = requiredDesc;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(Date completedTime) {
		this.completedTime = completedTime;
	}

	public User getCompletedBy() {
		return completedBy;
	}

	public void setCompletedBy(User completedBy) {
		this.completedBy = completedBy;
	}

	public Order getOrder() {
		return order;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	
	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	
}
