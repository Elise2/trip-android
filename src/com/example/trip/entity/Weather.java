package com.example.trip.entity;


import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Weather implements Parcelable {
	private String city;
	private int temperature1;
	private String status1;
	private String direction1;
	private String power1;
	private String xcz_s;
	private String yd_s;
	private String gm;
	private String tgd;
	private String pollution;
	private String zwx;
	private String chy_shuoming;
	private String gm_l;
	private String gm_s;
	private String ssd_s;

	public String getSsd_s() {
		return ssd_s;
	}

	public void setSsd_s(String ssd_s) {
		this.ssd_s = ssd_s;
	}

	public String getGm_l() {
		return gm_l;
	}

	public void setGm_l(String gm_l) {
		this.gm_l = gm_l;
	}

	public String getGm_s() {
		return gm_s;
	}

	public void setGm_s(String gm_s) {
		this.gm_s = gm_s;
	}

	public String getChy_shuoming() {
		return chy_shuoming;
	}

	public void setChy_shuoming(String chy_shuoming) {
		this.chy_shuoming = chy_shuoming;
	}

	public String getZwx() {
		return zwx;
	}

	public void setZwx(String zwx) {
		this.zwx = zwx;
	}

	public String getPollution() {
		return pollution;
	}

	public void setPollution(String pollution) {
		this.pollution = pollution;
	}

	public String getTgd() {
		return tgd;
	}

	public void setTgd(String tgd) {
		this.tgd = tgd;
	}

	public String getGm() {
		return gm;
	}

	public void setGm(String gm) {
		this.gm = gm;
	}

	public String getYd_s() {
		return yd_s;
	}

	public void setYd_s(String yd_s) {
		this.yd_s = yd_s;
	}

	public String getXcz_s() {
		return xcz_s;
	}

	public void setXcz_s(String xcz_s) {
		this.xcz_s = xcz_s;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getTemperature1() {
		return temperature1;
	}

	public void setTemperature1(int temperature1) {
		this.temperature1 = temperature1;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public String getDirection1() {
		return direction1;
	}

	public void setDirection1(String direction1) {
		this.direction1 = direction1;
	}

	public String getPower1() {
		return power1;
	}

	public void setPower1(String power1) {
		this.power1 = power1;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		arg0.writeString(city);
		arg0.writeString(status1);
		arg0.writeString(direction1);
		arg0.writeInt(temperature1);
		arg0.writeString(power1);
		arg0.writeString(xcz_s);
		arg0.writeString(yd_s);
		arg0.writeString(gm);
		arg0.writeString(tgd);
		arg0.writeString(pollution);
		arg0.writeString(zwx);
		arg0.writeString(chy_shuoming);
		arg0.writeString(gm_l);
		arg0.writeString(gm_s);
		arg0.writeString(ssd_s);
	}

	public static final Parcelable.Creator<Weather> CREATOR = new Creator<Weather>() {

		@Override
		public Weather createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			Weather weather = new Weather();
			weather.setCity(source.readString());
			weather.setStatus1(source.readString());
			weather.setDirection1(source.readString());
			weather.setTemperature1(source.readInt());
			weather.setPower1(source.readString());
			weather.setXcz_s(source.readString());
			weather.setYd_s(source.readString());
			weather.setGm(source.readString());
			weather.setTgd(source.readString());
			weather.setPollution(source.readString());
			weather.setZwx(source.readString());
			weather.setChy_shuoming(source.readString());
			weather.setGm_l(source.readString());
			weather.setGm_s(source.readString());
			weather.setSsd_s(source.readString());
			return weather;
		}

		@Override
		public Weather[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Weather[size];
		}
	};
}
