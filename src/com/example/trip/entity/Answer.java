package com.example.trip.entity;

import android.R.integer;

public class Answer {
	private String img;
	private String name;
	private String level;
	private int isBest;
	private String content;
	private String time;
	private int good;
	private int id;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getGood() {
		return good;
	}

	public void setGood(int good) {
		this.good = good;
	}

	public Answer(String img, String name, String level, int isBest,
			String content, String time, int good) {
		super();
		this.img = img;
		this.name = name;
		this.level = level;
		this.isBest = isBest;
		this.content = content;
		this.time = time;
		this.good = good;
	}

	public int getIsBest() {
		return isBest;
	}

	public void setIsBest(int isBest) {
		this.isBest = isBest;
	}

	public Answer() {
		super();
	}

}
