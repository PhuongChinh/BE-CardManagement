package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Table(name = "order_phases", schema = "public")
@NamedQuery(name = "OrderPhase.findAll", query = "SELECT u FROM OrderPhase u")
public class OrderPhase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "objectid-generator")
	@GenericGenerator(name = "objectid-generator", strategy = "com.example.demo.common.ObjectIDGenerator")
	@Column(unique = true, nullable = false, length = 24)
	private String Id;
	
	@Column(name = "phase_one", nullable = true)
	private int phaseOne;
	
	@Column(name = "phase_two", nullable = true)
	private int phaseTwo;
	
	@Column(name = "phase_three", nullable = true)
	private int phaseThree;
	
	@Column(name = "phase_four", nullable = true)
	private int phaseFour;
	
	@Column(name = "phase_five", nullable = true)
	private int phaseFive;
	
	@Column(name = "phase_completed", nullable = true)
	private int phaseCompleted;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public int getPhaseOne() {
		return phaseOne;
	}

	public void setPhaseOne(int phaseOne) {
		this.phaseOne = phaseOne;
	}

	public int getPhaseTwo() {
		return phaseTwo;
	}

	public void setPhaseTwo(int phaseTwo) {
		this.phaseTwo = phaseTwo;
	}

	public int getPhaseThree() {
		return phaseThree;
	}

	public void setPhaseThree(int phaseThree) {
		this.phaseThree = phaseThree;
	}

	public int getPhaseFour() {
		return phaseFour;
	}

	public void setPhaseFour(int phaseFour) {
		this.phaseFour = phaseFour;
	}

	public int getPhaseFive() {
		return phaseFive;
	}

	public void setPhaseFive(int phaseFive) {
		this.phaseFive = phaseFive;
	}

	public int getPhaseCompleted() {
		return phaseCompleted;
	}

	public void setPhaseCompleted(int phaseCompleted) {
		this.phaseCompleted = phaseCompleted;
	}
}
