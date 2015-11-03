package com.example.trip.activity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.example.trip.R;
import com.example.trip.entity.Weather;
import com.example.trip.util.Configration;

public class WeatherActivity extends Activity {
	private String strlocation;
	private TextView tip, yd, cy, zwx, tigan, wr, statu, tempture, citys, wind;
	private Weather w;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_weather);
		w = (Weather) getIntent().getParcelableExtra("weather");
		tip = (TextView) findViewById(R.id.tip);
		yd = (TextView) findViewById(R.id.yd);
		cy = (TextView) findViewById(R.id.cy);
		zwx = (TextView) findViewById(R.id.zwx);
		tigan = (TextView) findViewById(R.id.tigan);
		wr = (TextView) findViewById(R.id.wr);
		statu = (TextView) findViewById(R.id.status);
		tempture = (TextView) findViewById(R.id.tempture);
		citys = (TextView) findViewById(R.id.city);
		wind = (TextView) findViewById(R.id.wind);
		setWeather();
		// new weatherThread().start();

	}

	public void setWeather() {
		citys.setText(w.getCity());
		tempture.setText(w.getTemperature1() + "");
		statu.setText(w.getStatus1());
		wind.setText(w.getDirection1() + w.getPower1() + "级");
		yd.setText(w.getYd_s());
		if (w.getTgd() == null) {
			tigan.setText("体感" + 0 + "℃");
		} else {
			tigan.setText("体感" + w.getTgd() + "℃");

		}
		if (w.getZwx() == null) {
			zwx.setText("紫外线" + 0 + "级");
		} else {
			zwx.setText("紫外线" + w.getZwx() + "级");
		}
		if (w.getPollution() == null) {
			wr.setText("污染" + 0 + "级");

		} else {
			wr.setText("污染" + w.getPollution() + "级");
		}
		cy.setText(w.getChy_shuoming());
		tip.setText("感冒" + w.getGm_l() + "," + w.getGm_s());
	}

	class weatherThread extends Thread {

		public void run() {
			while (strlocation.equals("")) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block e.printStackTrace();
				}
			}
			try {
				Weather w = getWeather(new Configration()
						.EcodingGB2312(strlocation.substring(0,
								strlocation.length())));
				Message message = myHandler.obtainMessage();
				message.obj = w;
				message.what = 1;
				myHandler.sendMessage(message);

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	Handler myHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 1) {
				Weather w = (Weather) msg.obj;
				citys.setText(w.getCity());
				tempture.setText(w.getTemperature1() + "");
				statu.setText(w.getStatus1());
				wind.setText(w.getDirection1() + w.getPower1() + "级");
				yd.setText(w.getYd_s());
				if (w.getTgd() == null) {
					tigan.setText("体感" + 0 + "℃");
				} else {
					tigan.setText("体感" + w.getTgd() + "℃");

				}
				if (w.getZwx() == null) {
					zwx.setText("紫外线" + 0 + "级");
				} else {
					zwx.setText("紫外线" + w.getZwx() + "级");
				}
				if (w.getPollution() == null) {
					wr.setText("污染" + 0 + "级");

				} else {
					wr.setText("污染" + w.getPollution() + "级");
				}
				cy.setText(w.getChy_shuoming());
				tip.setText("感冒" + w.getGm_l() + "," + w.getGm_s()
						+ w.getSsd_s());
			}
		}

	};

	/**
	 * 获取天气tips
	 * 
	 * @return 天气和tips字符串
	 */
	public Weather getWeather(String city) {
		String url = "http://php.weather.sina.com.cn/xml.php?city=" + city
				+ "&password=DJOYnieT8234jlsK&day=0";
		HttpResponse httpResponse = null;
		StringBuffer result = null;
		Weather w = null;
		DefaultHttpClient client = null;
		try {
			client = new DefaultHttpClient();
			client.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					15000);
			HttpGet httpGet = new HttpGet(url);
			httpResponse = client.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				result = new StringBuffer("");
				String s = EntityUtils.toString(httpResponse.getEntity(),
						"UTF-8");
				InputStream in_withcode = new ByteArrayInputStream(
						s.getBytes("UTF-8"));
				w = new Configration().readInfo(in_withcode);
				if (w != null) {
					return w;

					// result.append(w.getCity());// 城市
					// result.append(" 天气状况:" + w.getStatus1());// 天气状况
					// result.append("转" + w.getStatus2() + " ");// 天气状况
					// //result.append(w.getDirection1() + w.getPower1() +
					// "级~");// 风
					// result.append(w.getDirection2() + w.getPower2() +
					// "级 ");// 风
					// result.append("温度:" + w.getTemperature2() + "~"
					// + w.getTemperature1() + "℃");// 低温
					// result.append(" 户外运动:" + w.getYd_s());
					// result.append("体感" + w.getTgd());
					// result.append("污染" + w.getPollution());
					// result.append("紫外线" + w.getZwx());
					// result.append("穿衣指南：" + w.getChy_shuoming());
					// result.append("温馨提示：感冒" + w.getGm_l() + "," + w.getGm_s()
					// + w.getSsd_s());
				}
			} else {
				Log.i("note", "数据请求异常！");
				result = null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// if (result != null)
		// return result.toString();
		// else
		// return null;
		return w;
	}
}
