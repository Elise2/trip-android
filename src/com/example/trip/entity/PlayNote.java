package com.example.trip.entity;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "playnote")
public class PlayNote implements Serializable {
	@DatabaseField(id = true)
	private int note_id;
	@DatabaseField(columnName="img")
	private String userimg;
	@DatabaseField
	private String note_content;
	@DatabaseField
	private int see;// 浏览量
	@DatabaseField
	private String sceneryImg;
	@DatabaseField
	private String notes_publish_time;
	@DatabaseField
	private String notes_type;
	@DatabaseField
	private String notes_days;
	@DatabaseField
	private String notes_cost;
	@DatabaseField
	private String username;
	@DatabaseField
	private String note_img;
	@DatabaseField
	private String notes_title;
	@DatabaseField
	private String notes_go_time;
	@DatabaseField
	private String city_id;

	@DatabaseField
	private int note_detail_id;
	@DatabaseField
	private String top_img;
	@DatabaseField
	private int isTop;
	@DatabaseField
	private String userImg;
	@DatabaseField
	private String userLevel;
	@DatabaseField
	private String city_name;

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public int getIsTop() {
		return isTop;
	}

	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}

	public PlayNote(String userimg, String note_content, int see,
			String sceneryImg, String notes_publish_time, String notes_type,
			String notes_days, String notes_cost, String username,
			String note_img, String notes_title, String notes_go_time,
			String city_id, int note_id, int note_detail_id) {
		super();
		this.userimg = userimg;
		this.note_content = note_content;
		this.see = see;
		this.sceneryImg = sceneryImg;
		this.notes_publish_time = notes_publish_time;
		this.notes_type = notes_type;
		this.notes_days = notes_days;
		this.notes_cost = notes_cost;
		this.username = username;
		this.note_img = note_img;
		this.notes_title = notes_title;
		this.notes_go_time = notes_go_time;
		this.city_id = city_id;
		this.note_id = note_id;
		this.note_detail_id = note_detail_id;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	public int getNote_id() {
		return note_id;
	}

	public void setNote_id(int note_id) {
		this.note_id = note_id;
	}

	public int getNote_detail_id() {
		return note_detail_id;
	}

	public void setNote_detail_id(int note_detail_id) {
		this.note_detail_id = note_detail_id;
	}

	public String getUserimg() {
		return userimg;
	}

	public PlayNote(String userimg, String note_content, int see,
			String sceneryImg, String notes_publish_time, String notes_type,
			String notes_days, String notes_cost, String username,
			String note_img, String notes_title, String notes_go_time,
			String city_id, int note_id, int note_detail_id, String top_img,
			int isTop, String userImg2, String userLevel, String city_name) {
		super();
		this.userimg = userimg;
		this.note_content = note_content;
		this.see = see;
		this.sceneryImg = sceneryImg;
		this.notes_publish_time = notes_publish_time;
		this.notes_type = notes_type;
		this.notes_days = notes_days;
		this.notes_cost = notes_cost;
		this.username = username;
		this.note_img = note_img;
		this.notes_title = notes_title;
		this.notes_go_time = notes_go_time;
		this.city_id = city_id;
		this.note_id = note_id;
		this.note_detail_id = note_detail_id;
		this.top_img = top_img;
		this.isTop = isTop;
		userImg = userImg2;
		this.userLevel = userLevel;
		this.city_name = city_name;
	}

	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}

	public String getNote_content() {
		return note_content;
	}

	public void setNote_content(String note_content) {
		this.note_content = note_content;
	}

	public int getSee() {
		return see;
	}

	public void setSee(int see) {
		this.see = see;
	}

	public String getSceneryImg() {
		return sceneryImg;
	}

	public void setSceneryImg(String sceneryImg) {
		this.sceneryImg = sceneryImg;
	}

	public String getNotes_publish_time() {
		return notes_publish_time;
	}

	public void setNotes_publish_time(String notes_publish_time) {
		this.notes_publish_time = notes_publish_time;
	}

	public String getNotes_type() {
		return notes_type;
	}

	public void setNotes_type(String notes_type) {
		this.notes_type = notes_type;
	}

	public String getNotes_days() {
		return notes_days;
	}

	public void setNotes_days(String notes_days) {
		this.notes_days = notes_days;
	}

	public String getNotes_cost() {
		return notes_cost;
	}

	public void setNotes_cost(String notes_cost) {
		this.notes_cost = notes_cost;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNote_img() {
		return note_img;
	}

	public void setNote_img(String note_img) {
		this.note_img = note_img;
	}

	public String getNotes_title() {
		return notes_title;
	}

	public void setNotes_title(String notes_title) {
		this.notes_title = notes_title;
	}

	public String getNotes_go_time() {
		return notes_go_time;
	}

	public void setNotes_go_time(String notes_go_time) {
		this.notes_go_time = notes_go_time;
	}

	public PlayNote(String userimg, String note_content, int see,
			String sceneryImg, String notes_publish_time, String notes_type,
			String notes_days, String notes_cost, String username,
			String note_img, String notes_title, String notes_go_time) {
		super();
		this.userimg = userimg;
		this.note_content = note_content;
		this.see = see;
		this.sceneryImg = sceneryImg;
		this.notes_publish_time = notes_publish_time;
		this.notes_type = notes_type;
		this.notes_days = notes_days;
		this.notes_cost = notes_cost;
		this.username = username;
		this.note_img = note_img;
		this.notes_title = notes_title;
		this.notes_go_time = notes_go_time;
	}

	public PlayNote() {
		super();
	}

	public String getTop_img() {
		return top_img;
	}

	public void setTop_img(String top_img) {
		this.top_img = top_img;
	}

}
