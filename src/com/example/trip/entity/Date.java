package com.example.trip.entity;

import java.io.Serializable;

import android.R.integer;

public class Date implements Serializable{
	private int dateId;
	private int dateDetailId;
	private String goDate;
	private String arriveDate;
	private String cityName;
	private int cityId;
	private int isGo;
	private int isOld;
	private int userId;
	private int scenery;
	private int playote;
	private int hotel;

	public Date(int dateId, int dateDetailId, String goDate, String arriveDate,
			String cityName, int cityId, int isGo, int isOld, int userId,
			int scenery, int playote, int hotel) {
		super();
		this.dateId = dateId;
		this.dateDetailId = dateDetailId;
		this.goDate = goDate;
		this.arriveDate = arriveDate;
		this.cityName = cityName;
		this.cityId = cityId;
		this.isGo = isGo;
		this.isOld = isOld;
		this.userId = userId;
		this.scenery = scenery;
		this.playote = playote;
		this.hotel = hotel;
	}

	public int getScenery() {
		return scenery;
	}

	public void setScenery(int scenery) {
		this.scenery = scenery;
	}

	public int getPlayote() {
		return playote;
	}

	public void setPlayote(int playote) {
		this.playote = playote;
	}

	public int getHotel() {
		return hotel;
	}

	public void setHotel(int hotel) {
		this.hotel = hotel;
	}

	public int getDateId() {
		return dateId;
	}

	public void setDateId(int dateId) {
		this.dateId = dateId;
	}

	public int getDateDetailId() {
		return dateDetailId;
	}

	public void setDateDetailId(int dateDetailId) {
		this.dateDetailId = dateDetailId;
	}

	public String getGoDate() {
		return goDate;
	}

	public void setGoDate(String goDate) {
		this.goDate = goDate;
	}

	public String getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(String arriveDate) {
		this.arriveDate = arriveDate;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getIsGo() {
		return isGo;
	}

	public void setIsGo(int isGo) {
		this.isGo = isGo;
	}

	public int getIsOld() {
		return isOld;
	}

	public void setIsOld(int isOld) {
		this.isOld = isOld;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date(int dateId, int dateDetailId, String goDate, String arriveDate,
			String cityName, int cityId, int isGo, int isOld, int userId) {
		super();
		this.dateId = dateId;
		this.dateDetailId = dateDetailId;
		this.goDate = goDate;
		this.arriveDate = arriveDate;
		this.cityName = cityName;
		this.cityId = cityId;
		this.isGo = isGo;
		this.isOld = isOld;
		this.userId = userId;
	}

	public Date() {
		super();
	}

}
