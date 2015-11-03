package com.example.trip.fragment;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract.CommonDataKinds.Relation;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trip.R;
import com.example.trip.activity.CalculatorActivity;
import com.example.trip.activity.CalendarsActivity;
import com.example.trip.activity.TrafficPlaneActivity;
import com.example.trip.activity.TrafficStatioActivity;
import com.example.trip.entity.Plane;
import com.example.trip.entity.Station;
import com.example.trip.entity.Weather;
import com.example.trip.locationselect.ActivitySelectCity;
import com.example.trip.locationselect.MainActivity;
import com.example.trip.util.Configration;
import com.example.trip.util.Constant;
import com.example.trip.util.PlaneConfigration;
import com.example.trip.util.StationConfigration;
import com.j256.ormlite.stmt.query.In;

public class LookForTrafficFragment extends Fragment implements OnClickListener {
	private TextView goCity;
	private TextView arriveCity;
	private TextView goDate;
	private String s;
	private Button start;
	private RelativeLayout go, arrive, goTime;
	private ImageView rote;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.layout_looktrafficfragment, null);
		s = getArguments().getString("trafficType");
		go = (RelativeLayout) v.findViewById(R.id.go);
		rote = (ImageView) v.findViewById(R.id.rote);
		rote.setOnClickListener(this);
		arrive = (RelativeLayout) v.findViewById(R.id.arrive);
		goTime = (RelativeLayout) v.findViewById(R.id.gotime);
		goTime.setOnClickListener(this);
		goCity = (TextView) v.findViewById(R.id.gocity);
		go.setOnClickListener(this);
		arriveCity = (TextView) v.findViewById(R.id.arrivecity);
		arrive.setOnClickListener(this);
		goDate = (TextView) v.findViewById(R.id.godate);
		start = (Button) v.findViewById(R.id.start);
		start.setOnClickListener(this);
		return v;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		SharedPreferences sPreferences = getActivity().getSharedPreferences(
				Constant.SP_NAME, Context.MODE_PRIVATE);
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sDateFormat.format(new java.util.Date());
		String date = sPreferences.getString("trafficDate", nowDate);
		goDate.setText(date);
	}

	class LookThread extends Thread {
		private String go = goCity.getText().toString();
		private String arrive = arriveCity.getText().toString();
		private String date = goDate.getText().toString();

		public void run() {
			while (go.equals("") || arrive.equals("") || date.equals("")) {
				Toast.makeText(getActivity(), "请将信息填写完整", Toast.LENGTH_LONG)
						.show();
				return;
			}
			if ("飞机".equals(s)) {
				List<Plane> planes = getPlanes(go, arrive, date);
				Message message = myHandler.obtainMessage();
				Bundle bundle = new Bundle();
				bundle.putSerializable("data", (Serializable) planes);
				message.setData(bundle);
				message.what = 1;
				myHandler.sendMessage(message);
			} else if ("火车".equals(s)) {
				List<Station> stations = getStation(go, arrive);
				Message message = myHandler.obtainMessage();
				Bundle bundle = new Bundle();
				bundle.putSerializable("data", (Serializable) stations);
				message.setData(bundle);
				message.what = 2;
				myHandler.sendMessage(message);
			}
		}
	}

	Handler myHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 1) {
				List<Plane> planes = (List<Plane>) msg.getData()
						.getSerializable("data");
				if (planes == null || planes.size() == 0) {
					new AlertDialog.Builder(getActivity()).setTitle("提示")
							.setMessage("亲，没有帮您找到符合条件的飞机票")
							.setPositiveButton("确定", null).show();
				} else {
					Intent intent = new Intent(getActivity(),
							TrafficPlaneActivity.class);
					intent.putExtra("traffic", (Serializable) planes);
					startActivity(intent);
				}
			} else if (msg.what == 2) {
				List<Station> stations = (List<Station>) msg.getData()
						.getSerializable("data");
				if (stations == null || stations.size() == 0) {
					new AlertDialog.Builder(getActivity()).setTitle("提示")
							.setMessage("亲，没有帮您找到符合条件的火车票")
							.setPositiveButton("确定", null).show();
				} else {
					Intent intent = new Intent(getActivity(),
							TrafficStatioActivity.class);
					intent.putExtra("traffic", (Serializable) stations);
					startActivity(intent);
				}
			}
		}

	};

	/**
	 * 获取天气tips
	 * 
	 * @return 天气和tips字符串
	 */
	public List<Station> getStation(String goCity, String arriveCity) {
		List<Station> w = null;
		String url = "http://apis.juhe.cn/train/s2swithprice?" + "start="
				+ goCity + "&end=" + arriveCity
				+ "&dtype=xml&key=9b3c4bb3871b91f185356cbc58fbfbe5";
		HttpResponse httpResponse = null;
		StringBuffer result = null;
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

				// String s = "<item><train_no>T109</train_no>"
				// + ""
				// +
				// "<train_type>特快</train_type><start_station>北京</start_station><start_station_type>始</start_station_type>"
				// +
				// "<end_station>上海</end_station><end_station_type>终</end_station_type><start_time>19:33</start_time>"
				// +
				// "<end_time>10:44</end_time><run_time>15小时11分</run_time><run_distance/><price_list>"
				// +
				// "<item><price_type>硬座</price_type><price>177.5</price></item><item><price_type>硬卧</price_type><price>325.5</price></item></price_list></item>";

				InputStream in_withcode = new ByteArrayInputStream(
						s.getBytes("UTF-8"));
				w = new StationConfigration().readInfo(in_withcode);
				if (w != null) {
					return w;
				}

			} else {
				Log.i("note", "数据请求异常！");
				result = null;
			}
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		return w;
	}

	/**
	 * 获取飞机
	 * 
	 * @return 飞机的数组
	 */
	public List<Plane> getPlanes(String goCity, String arriveCity, String date) {
		List<Plane> w = null;

		String url = "http://apis.juhe.cn/plan/bc?" + "start=" + goCity
				+ "&end=" + arriveCity + "&date=" + date
				+ "&dtype=xml&key=8a20bec268c0269a61ab712af8c06bad";
		HttpResponse httpResponse = null;
		StringBuffer result = null;
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
				// String s =
				// "<item><FlightNum>MU3926</FlightNum><AirlineCode>MU</AirlineCode>"
				// + "<Airline>东方航空</Airline><DepCity>北京首都</DepCity>"
				// +
				// "<ArrCity>上海虹桥</ArrCity> <DepCode>PEK</DepCode><ArrCode>SHA</ArrCode>"
				// +
				// "<OnTimeRate></OnTimeRate><DepTerminal>T2</DepTerminal> <ArrTerminal>T2</ArrTerminal>"
				// +
				// "<FlightDate>2015-11-05</FlightDate> <PEKDate>2015-11-05</PEKDate>"
				// +
				// "<DepTime>06:35</DepTime>  <ArrTime>08:45</ArrTime><Dexpected></Dexpected>"
				// + "<Aexpected></Aexpected></item>";
				InputStream in_withcode = new ByteArrayInputStream(
						s.getBytes("UTF-8"));
				w = new PlaneConfigration().readInfo(in_withcode);
				if (w != null) {
					return w;
				}

			} else {
				Log.i("note", "数据请求异常！");
				result = null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return w;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.start:
			new LookThread().start();
			break;
		case R.id.go:
			startActivityForResult(new Intent(getActivity(),
					ActivitySelectCity.class), 1);
			break;
		case R.id.arrive:
			startActivityForResult(new Intent(getActivity(),
					ActivitySelectCity.class), 2);
			break;
		case R.id.gotime:
			Intent intent = new Intent(getActivity(), CalendarsActivity.class);
			intent.putExtra("category", "traffic");
			startActivity(intent);
			break;
		case R.id.rote:
			String go = goCity.getText().toString();
			String arrive = arriveCity.getText().toString();
			goCity.setText(arrive);
			arriveCity.setText(go);
			break;
		default:
			break;
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == 99) {
			if (requestCode == 1) {
				goCity.setText(data.getStringExtra("lngCityName"));
			} else if (requestCode == 2) {
				arriveCity.setText(data.getStringExtra("lngCityName"));
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
