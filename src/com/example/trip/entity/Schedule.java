package com.example.trip.entity;

import java.io.Serializable;

public class Schedule implements Serializable{
	private int cityId;
	private String cityName;
	private String goDate;
	private String leaveDate;

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getGoDate() {
		return goDate;
	}

	public void setGoDate(String goDate) {
		this.goDate = goDate;
	}

	public String getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}

	public Schedule(int cityId, String cityName, String goDate, String leaveDate) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.goDate = goDate;
		this.leaveDate = leaveDate;
	}

	public Schedule() {
		super();
	}

}
