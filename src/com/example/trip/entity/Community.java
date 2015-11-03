package com.example.trip.entity;

import java.io.Serializable;

import android.R.integer;

public class Community implements Serializable{
	private String shortCommentFace;
	private String shortCommentImg;
	private String shortCommentname;
	private String shortCommentLevel;
	private String shortCommentLocation;
	private String shortCommentTime;
	private String shortCommentContent;
	private int shortCommentLike;
	private int shortCommentNum;
	private int shortCommentId;
	private int shortCommentUserId;

	public Community(String shortCommentFace, String shortCommentImg,
			String shortCommentname, String shortCommentLevel,
			String shortCommentLocation, String shortCommentTime,
			String shortCommentContent, int shortCommentLike,
			int shortCommentNum, int shortCommentId, int shortCommentUserId) {
		super();
		this.shortCommentFace = shortCommentFace;
		this.shortCommentImg = shortCommentImg;
		this.shortCommentname = shortCommentname;
		this.shortCommentLevel = shortCommentLevel;
		this.shortCommentLocation = shortCommentLocation;
		this.shortCommentTime = shortCommentTime;
		this.shortCommentContent = shortCommentContent;
		this.shortCommentLike = shortCommentLike;
		this.shortCommentNum = shortCommentNum;
		this.shortCommentId = shortCommentId;
		this.shortCommentUserId = shortCommentUserId;
	}

	public int getShortCommentId() {
		return shortCommentId;
	}

	public void setShortCommentId(int shortCommentId) {
		this.shortCommentId = shortCommentId;
	}

	public int getShortCommentUserId() {
		return shortCommentUserId;
	}

	public void setShortCommentUserId(int shortCommentUserId) {
		this.shortCommentUserId = shortCommentUserId;
	}

	public Community(String shortCommentFace, String shortCommentImg,
			String shortCommentname, String shortCommentLevel,
			String shortCommentLocation, String shortCommentTime,
			String shortCommentContent, int shortCommentLike,
			int shortCommentNum) {
		super();
		this.shortCommentFace = shortCommentFace;
		this.shortCommentImg = shortCommentImg;
		this.shortCommentname = shortCommentname;
		this.shortCommentLevel = shortCommentLevel;
		this.shortCommentLocation = shortCommentLocation;
		this.shortCommentTime = shortCommentTime;
		this.shortCommentContent = shortCommentContent;
		this.shortCommentLike = shortCommentLike;
		this.shortCommentNum = shortCommentNum;
	}

	public String getShortCommentContent() {
		return shortCommentContent;
	}

	public void setShortCommentContent(String shortCommentContent) {
		this.shortCommentContent = shortCommentContent;
	}

	public String getShortCommentname() {
		return shortCommentname;
	}

	public void setShortCommentname(String shortCommentname) {
		this.shortCommentname = shortCommentname;
	}

	public String getShortCommentLevel() {
		return shortCommentLevel;
	}

	public void setShortCommentLevel(String shortCommentLevel) {
		this.shortCommentLevel = shortCommentLevel;
	}

	public String getShortCommentLocation() {
		return shortCommentLocation;
	}

	public void setShortCommentLocation(String shortCommentLocation) {
		this.shortCommentLocation = shortCommentLocation;
	}

	public String getShortCommentTime() {
		return shortCommentTime;
	}

	public void setShortCommentTime(String shortCommentTime) {
		this.shortCommentTime = shortCommentTime;
	}

	public int getShortCommentLike() {
		return shortCommentLike;
	}

	public void setShortCommentLike(int shortCommentLike) {
		this.shortCommentLike = shortCommentLike;
	}

	public int getShortCommentNum() {
		return shortCommentNum;
	}

	public void setShortCommentNum(int shortCommentNum) {
		this.shortCommentNum = shortCommentNum;
	}

	public Community(String shortCommentFace, String shortCommentImg,
			String shortCommentname, String shortCommentLevel,
			String shortCommentLocation, String shortCommentTime,
			int shortCommentLike, int shortCommentNum) {
		super();
		this.shortCommentFace = shortCommentFace;
		this.shortCommentImg = shortCommentImg;
		this.shortCommentname = shortCommentname;
		this.shortCommentLevel = shortCommentLevel;
		this.shortCommentLocation = shortCommentLocation;
		this.shortCommentTime = shortCommentTime;
		this.shortCommentLike = shortCommentLike;
		this.shortCommentNum = shortCommentNum;
	}

	public String getShortCommentFace() {
		return shortCommentFace;
	}

	public void setShortCommentFace(String shortCommentFace) {
		this.shortCommentFace = shortCommentFace;
	}

	public String getShortCommentImg() {
		return shortCommentImg;
	}

	public void setShortCommentImg(String shortCommentImg) {
		this.shortCommentImg = shortCommentImg;
	}

	public Community() {
		super();
	}

}
