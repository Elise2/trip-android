package com.example.trip.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.trip.entity.Scenery;
import com.example.trip.entity.Weather;

//读取XML文件信息
public class SceneryConfigration {
	public Scenery readInfo(InputStream a) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance(); // 取得SAXParserFactory实例
			SAXParser parser = factory.newSAXParser();
			MyHandler handler = new MyHandler();
			parser.parse(a, handler);
			a.close();
			return handler.getScenery();
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
		private Scenery scenery = null;
		private String tagName = null;// 当前解析的元素标签
		private boolean flag = false;

		public Scenery getScenery() {
			return scenery;
		}

		@Override
		public void startDocument() throws SAXException {
			System.out.println("ERROR");
			scenery = new Scenery();
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			if ("attention".equals(tagName)) {
				flag = true;
			}
			this.tagName = localName;
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			if (!flag) {

				if (this.tagName != null) {
					String data = new String(ch, start, length);
					if (this.tagName.equals("abstract")) {
						scenery.setObstract(data);
					} else if (this.tagName.equals("description")) {
						scenery.setDescription(data);
					} else if (this.tagName.equals("open_time")) {
						scenery.setOpen_time(data);
					} else if (this.tagName.equals("telephone")) {
						scenery.setTelephone(data);
					} else if (this.tagName.equals("name")) {
						scenery.setName(data);
					} else if (this.tagName.equals("price")) {
						scenery.setPrice(data);
					} else if (this.tagName.equals("lng")) {
						scenery.setLng(data);
					} else if (this.tagName.equals("lat")) {
						scenery.setLat(data);
					}
				}
			}
		}

		@Override
		public void endElement(String uri, String localName, String name)
				throws SAXException {
			this.tagName = null;
		}
	}

}
