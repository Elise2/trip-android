package com.example.trip.entity;

import java.io.Serializable;

public class Station implements Serializable {
	public String train_no;// 车次
	public String start_station;// 发车站
	public String end_station;// 终点站
	public String start_station_type;// 发车站状态
	public String end_station_type;// 终点站状态
	public String start_time;// 时间
	public String end_time;// 终点站
	public String run_time;// 时间
	public String price_type1;
	public String price1;
	public String price_type2;
	public String price2;

	public String getTrain_no() {
		return train_no;
	}

	public void setTrain_no(String train_no) {
		this.train_no = train_no;
	}

	public String getStart_station() {
		return start_station;
	}

	public void setStart_station(String start_station) {
		this.start_station = start_station;
	}

	public String getEnd_station() {
		return end_station;
	}

	public void setEnd_station(String end_station) {
		this.end_station = end_station;
	}

	public String getStart_station_type() {
		return start_station_type;
	}

	public void setStart_station_type(String start_station_type) {
		this.start_station_type = start_station_type;
	}

	public String getEnd_station_type() {
		return end_station_type;
	}

	public void setEnd_station_type(String end_station_type) {
		this.end_station_type = end_station_type;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getRun_time() {
		return run_time;
	}

	public void setRun_time(String run_time) {
		this.run_time = run_time;
	}

	public String getPrice_type1() {
		return price_type1;
	}

	public void setPrice_type1(String price_type1) {
		this.price_type1 = price_type1;
	}

	public String getPrice_type2() {
		return price_type2;
	}

	public void setPrice_type2(String price_type2) {
		this.price_type2 = price_type2;
	}

	public Station(String train_no, String start_station, String end_station,
			String start_station_type, String end_station_type,
			String start_time, String end_time, String run_time,
			String price_type1, String price1, String price_type2, String price2) {
		super();
		this.train_no = train_no;
		this.start_station = start_station;
		this.end_station = end_station;
		this.start_station_type = start_station_type;
		this.end_station_type = end_station_type;
		this.start_time = start_time;
		this.end_time = end_time;
		this.run_time = run_time;
		this.price_type1 = price_type1;
		this.price1 = price1;
		this.price_type2 = price_type2;
		this.price2 = price2;
	}

	public String getPrice1() {
		return price1;
	}

	public void setPrice1(String price1) {
		this.price1 = price1;
	}

	public String getPrice2() {
		return price2;
	}

	public void setPrice2(String price2) {
		this.price2 = price2;
	}

	public Station() {
		super();
	}

}
