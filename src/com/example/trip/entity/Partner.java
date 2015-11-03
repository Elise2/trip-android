package com.example.trip.entity;

public class Partner {
	private String name;
	private String headImg;
	private String start;
	private String end;
	private String startTime;
	private int totalTime;
	private String request;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Partner() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Partner(String name, String headImg, String start, String end,
			String startTime, int totalTime, String request) {
		super();
		this.name = name;
		this.headImg = headImg;
		this.start = start;
		this.end = end;
		this.startTime = startTime;
		this.totalTime = totalTime;
		this.request = request;
	}
	
	
	

}
