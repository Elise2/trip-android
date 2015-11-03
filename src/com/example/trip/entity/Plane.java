package com.example.trip.entity;

import java.io.Serializable;

public class Plane implements Serializable {

	public String Airline;// 航空公司
	public String DepCity;// 出发地
	public String ArrCity;// 目的地
	public String DepTime;// 出发时间
	public String ArrTime;// 到达时间
	public String FlightNum;// 飞机型号
	public String FlightDate;// 出发日期
	public String DepTerminal;// 出发航站楼
	public String ArrTerminal;// 到达航站楼

	public Plane(String airline, String depCity, String arrCity,
			String depTime, String arrTime, String flightNum,
			String flightDate, String depTerminal, String arrTerminal) {
		super();
		Airline = airline;
		DepCity = depCity;
		ArrCity = arrCity;
		DepTime = depTime;
		ArrTime = arrTime;
		FlightNum = flightNum;
		FlightDate = flightDate;
		DepTerminal = depTerminal;
		ArrTerminal = arrTerminal;
	}

	public Plane() {
		super();
	}

	public String getAirline() {
		return Airline;
	}

	public void setAirline(String airline) {
		Airline = airline;
	}

	public String getDepCity() {
		return DepCity;
	}

	public void setDepCity(String depCity) {
		DepCity = depCity;
	}

	public String getArrCity() {
		return ArrCity;
	}

	public void setArrCity(String arrCity) {
		ArrCity = arrCity;
	}

	public String getDepTime() {
		return DepTime;
	}

	public void setDepTime(String depTime) {
		DepTime = depTime;
	}

	public String getArrTime() {
		return ArrTime;
	}

	public void setArrTime(String arrTime) {
		ArrTime = arrTime;
	}

	public String getFlightNum() {
		return FlightNum;
	}

	public void setFlightNum(String flightNum) {
		FlightNum = flightNum;
	}

	public String getFlightDate() {
		return FlightDate;
	}

	public void setFlightDate(String flightDate) {
		FlightDate = flightDate;
	}

	public String getDepTerminal() {
		return DepTerminal;
	}

	public void setDepTerminal(String depTerminal) {
		DepTerminal = depTerminal;
	}

	public String getArrTerminal() {
		return ArrTerminal;
	}

	public void setArrTerminal(String arrTerminal) {
		ArrTerminal = arrTerminal;
	}

}
