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

import com.example.trip.entity.Plane;

//读取XML文件信息
public class PlaneConfigration {
	public List<Plane> readInfo(InputStream a) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance(); // 取得SAXParserFactory实例
			SAXParser parser = factory.newSAXParser();
			MyHandler handler = new MyHandler();
			parser.parse(a, handler);
			a.close();
			return handler.getPlane();
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
		private List<Plane> Planes = null;
		private Plane plane;
		private String tagName = null;// 当前解析的元素标签

		public List<Plane> getPlane() {
			return Planes;
		}

		@Override
		public void startDocument() throws SAXException {
			Planes = new ArrayList<Plane>();

		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			if ("item".equals(qName)) {
				plane = new Plane();
			}
			this.tagName = localName;
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			if (this.tagName != null) {

				String data = new String(ch, start, length);
				if (this.tagName.equals("ArrTime")) {
					plane.setArrTime(data);
				} else if (this.tagName.equals("DepTime")) {
					plane.setDepTime(data);
				} else if (this.tagName.equals("PEKDate")) {
					plane.setFlightDate(data);
				} else if (this.tagName.equals("ArrTerminal")) {
					plane.setArrTerminal(data);
				} else if (this.tagName.equals("DepTerminal")) {
					plane.setDepTerminal(data);
				} else if (this.tagName.equals("FlightNum")) {
					plane.setFlightNum(data);
				} else if (this.tagName.equals("DepCity")) {
					plane.setDepCity(data);
				} else if (this.tagName.equals("ArrCity")) {
					plane.setArrCity(data);
				} else if (this.tagName.equals("Airline")) {
					plane.setAirline(data);
				}
			}

		}

		@Override
		public void endElement(String uri, String localName, String name)
				throws SAXException {
			if ("item".equals(name)) {
				Planes.add(plane);
				plane = null;
			}
			this.tagName = null;
		}
	}

}
