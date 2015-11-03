package com.example.trip.entity;

import java.io.Serializable;

public class Hotel implements Serializable{
	private int user_id;
	private int city_id;
	private int order_id;
	private String order_name;
	private String order_price;
	private String order_img;
	private String order_type;
	private String order_descript;
	private String order_recommentNum;
	private String usercotent;
	private String username;
	private String city_name;
	private String userimg;
	private String user_level;
	private String publishtime;
	public String getUser_level() {
		return user_level;
	}
	public void setUser_level(String user_level) {
		this.user_level = user_level;
	}
	public String getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getOrder_name() {
		return order_name;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	public String getOrder_price() {
		return order_price;
	}
	public void setOrder_price(String order_price) {
		this.order_price = order_price;
	}
	public String getOrder_img() {
		return order_img;
	}
	public void setOrder_img(String order_img) {
		this.order_img = order_img;
	}
	public String getOrder_type() {
		return order_type;
	}
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}
	public String getOrder_descript() {
		return order_descript;
	}
	public void setOrder_descript(String order_descript) {
		this.order_descript = order_descript;
	}
	public String getOrder_recommentNum() {
		return order_recommentNum;
	}
	public void setOrder_recommentNum(String order_recommentNum) {
		this.order_recommentNum = order_recommentNum;
	}
	public String getUsercotent() {
		return usercotent;
	}
	public void setUsercotent(String usercotent) {
		this.usercotent = usercotent;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getUserimg() {
		return userimg;
	}
	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}
}
