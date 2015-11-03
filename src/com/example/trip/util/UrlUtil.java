package com.example.trip.util;

public class UrlUtil {
	public static String BASE_URL = "http://192.168.253.1:8080";
	public static String ROOT_URL = BASE_URL + "/trip/";
	public static String SERVLET_URL = ROOT_URL + "servlet/";
	public static String TRIP_CITY_URL = ROOT_URL + "CityServlet";
	public static String TRIP_VARIETY_URL = SERVLET_URL + "VarietyServlet";
	public static String TRIP_LOGIN_URL = SERVLET_URL + "UserServlet";
	public static String TRIP_SCENENRY_URL = SERVLET_URL + "SceneryServlet";
	public static String TRIP_ASK_URL = SERVLET_URL + "AskServlet";
	public static String TRIP_ANSWER_URL = SERVLET_URL + "AnswerServlet";
	// 新增游记功能
	public static String TRIP_NOTES_URL = SERVLET_URL + "NoteServlet";

	public static String TRIP_DISCUSS_URL = SERVLET_URL + "DiscussServlet";
	public static String TRIP_HOTEL_URL = SERVLET_URL + "HotelServlet";
	public static String TRIP_HUNTER_URL = SERVLET_URL + "HunterServlet";
	public static String TRIP_ORDER_URL = SERVLET_URL + "OrderServlet";

	public static String TRIP_SHORT_URL = SERVLET_URL + "ShortServlet";
	public static String TRIP_DATE_URL = SERVLET_URL + "DateServlet";

}
