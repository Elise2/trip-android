package com.example.trip.entity;

import android.R.integer;

/**
 * 在发现节目lisview中的item,homepager_list_item.xml
 * @author Administrator
 *
 */
public class SubjectRecommend {
	private String subjectTitle;
	private String subjectTime;
	private String subjectLocation;
	private int backgroudImg;
	public String getSubjectTitle() {
		return subjectTitle;
	}
	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}
	public String getSubjectTime() {
		return subjectTime;
	}
	public void setSubjectTime(String subjectTime) {
		this.subjectTime = subjectTime;
	}
	public String getSubjectLocation() {
		return subjectLocation;
	}
	public void setSubjectLocation(String subjectLocation) {
		this.subjectLocation = subjectLocation;
	}
	public int getBackgroudImg() {
		return backgroudImg;
	}
	public void setBackgroudImg(int backgroudImg) {
		this.backgroudImg = backgroudImg;
	}
	public SubjectRecommend() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SubjectRecommend(String subjectTitle, String subjectTime,
			String subjectLocation, int backgroudImg) {
		super();
		this.subjectTitle = subjectTitle;
		this.subjectTime = subjectTime;
		this.subjectLocation = subjectLocation;
		this.backgroudImg = backgroudImg;
	}
	
	
}
