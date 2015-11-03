package com.example.trip.entity;

import java.io.Serializable;

import android.R.integer;

public class Ask implements Serializable{

	private String askTitle;
	private String askContent;
	private String askTime;
	private String askUserImg;
	private String askUserName;
	private int askSee;
	private int askId;
	private int cityId;
	private int answerNum;

	public int getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(int answerNum) {
		this.answerNum = answerNum;
	}

	public Ask(String askTitle, String askContent, String askTime,
			String askUserImg, String askUserName, int askSee, int askId,
			int cityId, int answerNum) {
		super();
		this.askTitle = askTitle;
		this.askContent = askContent;
		this.askTime = askTime;
		this.askUserImg = askUserImg;
		this.askUserName = askUserName;
		this.askSee = askSee;
		this.askId = askId;
		this.cityId = cityId;
		this.answerNum = answerNum;
	}

	public String getAskTitle() {
		return askTitle;
	}

	public void setAskTitle(String askTitle) {
		this.askTitle = askTitle;
	}

	public String getAskContent() {
		return askContent;
	}

	public void setAskContent(String askContent) {
		this.askContent = askContent;
	}

	public String getAskTime() {
		return askTime;
	}

	public void setAskTime(String askTime) {
		this.askTime = askTime;
	}

	public String getAskUserImg() {
		return askUserImg;
	}

	public void setAskUserImg(String askUserImg) {
		this.askUserImg = askUserImg;
	}

	public String getAskUserName() {
		return askUserName;
	}

	public void setAskUserName(String askUserName) {
		this.askUserName = askUserName;
	}

	public int getAskSee() {
		return askSee;
	}

	public void setAskSee(int askSee) {
		this.askSee = askSee;
	}

	public int getAskId() {
		return askId;
	}

	public void setAskId(int askId) {
		this.askId = askId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public Ask() {
		super();
	}

}
