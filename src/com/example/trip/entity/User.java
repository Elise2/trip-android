package com.example.trip.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class User implements Parcelable {

	private int user_id;

	private String username;

	private String userpwd;

	private String userEmail;

	private int userPhoneNum;

	private String userImg;

	private String userLevel;

	private String userStorageId;

	private String userOrder;
	private String userDiscount;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getUserPhoneNum() {
		return userPhoneNum;
	}

	public void setUserPhoneNum(int userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
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

	public String getUserStorageId() {
		return userStorageId;
	}

	public void setUserStorageId(String userStorageId) {
		this.userStorageId = userStorageId;
	}

	public String getUserOrder() {
		return userOrder;
	}

	public void setUserOrder(String userOrder) {
		this.userOrder = userOrder;
	}

	public String getUserDiscount() {
		return userDiscount;
	}

	public void setUserDiscount(String userDiscount) {
		this.userDiscount = userDiscount;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String userpwd) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.userpwd = userpwd;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(user_id);
		dest.writeString(username);
		dest.writeString(userpwd);
		dest.writeString(userEmail);
		dest.writeInt(userPhoneNum);
		dest.writeString(userImg);
		dest.writeString(userLevel);
		dest.writeString(userStorageId);
		dest.writeString(userOrder);
		dest.writeString(userDiscount);
	}

	public static final Parcelable.Creator<User> CREATOR = new Creator<User>() {

		@Override
		public User createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			User user = new User();
			user.setUser_id(source.readInt());
			user.setUsername(source.readString());
			user.setUserpwd(source.readString());
			user.setUserEmail(source.readString());
			user.setUserPhoneNum(source.readInt());
			user.setUserImg(source.readString());
			user.setUserLevel(source.readString());
			user.setUserStorageId(source.readString());
			user.setUserOrder(source.readString());
			user.setUserDiscount(source.readString());
			return user;
		}

		@Override
		public User[] newArray(int size) {
			// TODO Auto-generated method stub
			return new User[size];
		}
	};

}
