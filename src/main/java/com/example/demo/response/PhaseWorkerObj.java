package com.example.demo.response;

import java.util.List;

import com.example.demo.model.OrderPhaseWorker;

public class PhaseWorkerObj {
	private List<OrderPhaseWorker> phaseTwo;
	private List<OrderPhaseWorker> phaseThree;
	private List<OrderPhaseWorker> phaseFour;
	private List<OrderPhaseWorker> phaseFive;
	private int completed;
	private int notDo;
	
	private int twoNotDo;
	private int threeNotDo;
	private int fourNotDo;
	private int fiveNotDo;
	
	public List<OrderPhaseWorker> getPhaseTwo() {
		return phaseTwo;
	}
	public void setPhaseTwo(List<OrderPhaseWorker> phaseTwo) {
		this.phaseTwo = phaseTwo;
	}
	public List<OrderPhaseWorker> getPhaseThree() {
		return phaseThree;
	}
	public void setPhaseThree(List<OrderPhaseWorker> phaseThree) {
		this.phaseThree = phaseThree;
	}
	public List<OrderPhaseWorker> getPhaseFour() {
		return phaseFour;
	}
	public void setPhaseFour(List<OrderPhaseWorker> phaseFour) {
		this.phaseFour = phaseFour;
	}
	public List<OrderPhaseWorker> getPhaseFive() {
		return phaseFive;
	}
	public void setPhaseFive(List<OrderPhaseWorker> phaseFive) {
		this.phaseFive = phaseFive;
	}
	public int getCompleted() {
		return completed;
	}
	public void setCompleted(int completed) {
		this.completed = completed;
	}
	public int getNotDo() {
		return notDo;
	}
	public void setNotDo(int notDo) {
		this.notDo = notDo;
	}
	public int getTwoNotDo() {
		return twoNotDo;
	}
	public void setTwoNotDo(int twoNotDo) {
		this.twoNotDo = twoNotDo;
	}
	public int getThreeNotDo() {
		return threeNotDo;
	}
	public void setThreeNotDo(int threeNotDo) {
		this.threeNotDo = threeNotDo;
	}
	public int getFourNotDo() {
		return fourNotDo;
	}
	public void setFourNotDo(int fourNotDo) {
		this.fourNotDo = fourNotDo;
	}
	public int getFiveNotDo() {
		return fiveNotDo;
	}
	public void setFiveNotDo(int fiveNotDo) {
		this.fiveNotDo = fiveNotDo;
	}
}
