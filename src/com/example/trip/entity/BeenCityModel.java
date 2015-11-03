package com.example.trip.entity;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/**
 * 城市属性实体类
 * 
 * @author sy
 * 
 */
@DatabaseTable(tableName = "beencity")
public class BeenCityModel implements Serializable {
	@DatabaseField
	private String cityName;
	@DatabaseField(id = true)
	private int cityId;
	private int isCity;
	@DatabaseField
	private int cityTraverNum;
	private int cityCategory;
	private String nameSort;
	@DatabaseField
	private String cityImg;
	@DatabaseField
	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getIsCity() {
		return isCity;
	}

	public void setIsCity(int isCity) {
		this.isCity = isCity;
	}

	public BeenCityModel() {
		super();
	}

	public BeenCityModel(String cityName, int cityId, int isCity,
			int cityTraverNum, int cityCategory, String nameSort, String cityImg) {
		super();
		this.cityName = cityName;
		this.cityId = cityId;
		this.isCity = isCity;
		this.cityTraverNum = cityTraverNum;
		this.cityCategory = cityCategory;
		this.nameSort = nameSort;
		this.cityImg = cityImg;
	}

	public String getCityImg() {
		return cityImg;
	}

	public void setCityImg(String cityImg) {
		this.cityImg = cityImg;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int isCity() {
		return isCity;
	}

	public void setCity(int isCity) {
		this.isCity = isCity;
	}

	public int getCityTraverNum() {
		return cityTraverNum;
	}

	public void setCityTraverNum(int cityTraverNum) {
		this.cityTraverNum = cityTraverNum;
	}

	public int getCityCategory() {
		return cityCategory;
	}

	public void setCityCategory(int cityCategory) {
		this.cityCategory = cityCategory;
	}

	public String getNameSort() {
		return nameSort;
	}

	public void setNameSort(String nameSort) {
		this.nameSort = nameSort;
	}

}
