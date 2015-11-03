package com.example.trip.entity;

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
@DatabaseTable(tableName = "collectioncity")
public class CityModel implements Parcelable {
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

	public CityModel() {
		super();
	}

	public CityModel(String cityName, int cityId, int isCity,
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

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int arg1) {
		// TODO Auto-generated method stub

		dest.writeString(cityName);
		dest.writeString(nameSort);
		dest.writeString(cityImg);
		dest.writeInt(cityId);
		dest.writeInt(isCity);
		dest.writeInt(cityTraverNum);
		dest.writeInt(cityCategory);

	}

	public static final Parcelable.Creator<CityModel> CREATOR = new Creator<CityModel>() {

		@Override
		public CityModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			CityModel city = new CityModel();
			// private String cityName;
			city.setCityName(source.readString());
			city.setNameSort(source.readString());
			city.setCityImg(source.readString());
			city.setCityId(source.readInt());
			city.setCity(source.readInt());
			city.setCityTraverNum(source.readInt());
			city.setCityCategory(source.readInt());
			return city;
		}

		@Override
		public CityModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new CityModel[size];
		}
	};

}
