package com.example.trip.entity;

import java.io.Serializable;

import android.R.integer;

import com.baidu.location.i;

public class Scenery implements Serializable {
	private String title;
	private int cityId;
	private String img;
	private String content;
	private int typeId;
	private int talkNum;
	private String sceneryType;
	private String recommendTitle;
	private String recommendContent;
	private String username;
	// 增加userImg
	private String userImg;
	private String hotelPrice;

	private String description;
	private String price;
	private String open_time;
	private String telephone;
	private String obstract;
	private String name;
	private String lng;
	private String lat;
	private int isTop;

	private int scenery_id;

	public int getScenery_id() {
		return scenery_id;
	}

	public void setScenery_id(int scenery_id) {
		this.scenery_id = scenery_id;
	}

	public int getIsTop() {
		return isTop;
	}

	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOpen_time() {
		return open_time;
	}

	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getObstract() {
		return obstract;
	}

	public void setObstract(String obstract) {
		this.obstract = obstract;
	}

	public String getHotelPrice() {
		return hotelPrice;
	}

	public void setHotelPrice(String hotelPrice) {
		this.hotelPrice = hotelPrice;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTalkNum() {
		return talkNum;
	}

	public void setTalkNum(int talkNum) {
		this.talkNum = talkNum;
	}

	public String getSceneryType() {
		return sceneryType;
	}

	public void setSceneryType(String sceneryType) {
		this.sceneryType = sceneryType;
	}

	public String getRecommendTitle() {
		return recommendTitle;
	}

	public void setRecommendTitle(String recommendTitle) {
		this.recommendTitle = recommendTitle;
	}

	public String getRecommendContent() {
		return recommendContent;
	}

	public void setRecommendContent(String recommendContent) {
		this.recommendContent = recommendContent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

}
