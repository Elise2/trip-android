package com.example.trip.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.trip.entity.Station;

//读取XML文件信息
public class StationConfigration {
	public List<Station> readInfo(InputStream a) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance(); // 取得SAXParserFactory实例
			SAXParser parser = factory.newSAXParser();
			MyHandler handler = new MyHandler();
			parser.parse(a, handler);
			a.close();
			return handler.getStation();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String EcodingGB2312(String a) throws UnsupportedEncodingException {
		return URLEncoder.encode(a, "gb2312");
	}

	private class MyHandler extends DefaultHandler {
		private List<Station> Stations = null;
		private Station Station;
		private String tagName = null;// 当前解析的元素标签
		private int flag = 0;

		public List<Station> getStation() {
			return Stations;
		}

		@Override
		public void startDocument() throws SAXException {
			Stations = new ArrayList<Station>();

		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
		
			if (flag == 0) {
				if ("item".equals(qName)) {
					Station = new Station();
				}
			} else {
				if ("item".equals(qName)) {
					flag++;
				}
			}
			if ("price_list".equals(qName)) {
				flag = 1;
			}
			this.tagName = localName;
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {

			if (this.tagName != null) {
				String data = new String(ch, start, length);
				if (this.tagName.equals("train_no")) {
					Station.setTrain_no(data);
				} else if (this.tagName.equals("start_station")) {
					Station.setStart_station(data);
				} else if (this.tagName.equals("start_station_type")) {
					Station.setStart_station_type(data);
				} else if (this.tagName.equals("end_station")) {
					Station.setEnd_station(data);
				} else if (this.tagName.equals("end_station_type")) {
					Station.setEnd_station_type(data);
				} else if (this.tagName.equals("start_time")) {
					Station.setStart_time(data);
				} else if (this.tagName.equals("end_time")) {
					Station.setEnd_time(data);
				} else if (this.tagName.equals("run_time")) {
					Station.setRun_time(data);
				}
				if (flag ==2) {
					if (this.tagName.equals("price_type")) {
						Station.setPrice_type1(data);
					} else if (this.tagName.equals("price")) {
						Station.setPrice1(data);
					}
				} else if (flag == 3) {
					if (this.tagName.equals("price_type")) {
						Station.setPrice_type2(data);
					} else if (this.tagName.equals("price")) {
						Station.setPrice2(data);
					}
				}

			}

		}

		@Override
		public void endElement(String uri, String localName, String name)
				throws SAXException {
			if (flag == 0) {
				if ("item".equals(name)) {
					Stations.add(Station);
					Station = null;
				}
			}

			if ("price_list".equals(name)) {
				flag = 0;
			}
			this.tagName = null;
		}
	}

}
