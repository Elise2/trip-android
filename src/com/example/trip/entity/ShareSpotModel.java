package com.example.trip.entity;
/**
 * 花样撒野中每个专题里面的model
 * @author Administrator
 *
 */
public class ShareSpotModel {
	private String spotLocation;//旅游地点
	private String topImg; //代表图片
	private String photoByName; //拍照人
	private String shareReason; //推荐理由
	public String getSpotLocation() {
		return spotLocation;
	}
	public void setSpotLocation(String spotLocation) {
		this.spotLocation = spotLocation;
	}
	public String getTopImg() {
		return topImg;
	}
	public void setTopImg(String topImg) {
		this.topImg = topImg;
	}
	public String getPhotoByName() {
		return photoByName;
	}
	public void setPhotoByName(String photoByName) {
		this.photoByName = photoByName;
	}
	public String getShareReason() {
		return shareReason;
	}
	public void setShareReason(String shareReason) {
		this.shareReason = shareReason;
	}
	public ShareSpotModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShareSpotModel(String spotLocation, String topImg,
			String photoByName, String shareReason) {
		super();
		this.spotLocation = spotLocation;
		this.topImg = topImg;
		this.photoByName = photoByName;
		this.shareReason = shareReason;
	}
	
	
	
	
}
