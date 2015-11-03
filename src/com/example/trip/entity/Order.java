package com.example.trip.entity;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName="order")
public class Order implements Serializable{
	@DatabaseField
	private String hotelName;//旅馆名
	@DatabaseField
	private String inTime;//入住时间
	@DatabaseField
	private int orderNum;//预订数量
	@DatabaseField
	private int orderPrice;//订单价格
	@DatabaseField
	private String name;//订单者姓名
	@DatabaseField
	private String phonenum;//...电话
	@DatabaseField
	private String email;//...邮箱
	@DatabaseField(id=true)
	private int order_id;
	@DatabaseField
	private String hotelImg;
	@DatabaseField()
	private int isTop;
	
	public int getIsTop() {
		return isTop;
	}
	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}
	public String getHotelImg() {
		return hotelImg;
	}
	public void setHotelImg(String hotelImg) {
		this.hotelImg = hotelImg;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(String hotelName, String inTime, int orderNum, int orderPrice,
			String name, String phonenum, String email,int order_id,int isTop) {
		super();
		this.hotelName = hotelName;
		this.inTime = inTime;
		this.orderNum = orderNum;
		this.orderPrice = orderPrice;
		this.name = name;
		this.phonenum = phonenum;
		this.email = email;
		this.order_id = order_id;
		this.isTop = isTop;
	}
	
}
