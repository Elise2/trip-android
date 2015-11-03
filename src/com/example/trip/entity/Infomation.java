package com.example.trip.entity;

public class Infomation {
	private int InfoId;
	private String title;
	private String content;
	private int typeId;
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Infomation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getInfoId() {
		return InfoId;
	}
	public void setInfoId(int infoId) {
		InfoId = infoId;
	}
	public Infomation(int infoId,String title, String content,int typeId) {
		super();
		this.InfoId = infoId;
		this.title = title;
		this.content = content;
		this.typeId = typeId;
	}
	
}
