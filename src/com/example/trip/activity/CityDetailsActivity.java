package com.example.trip.activity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.trip.R;
import com.example.trip.entity.BeenCityModel;
import com.example.trip.entity.CityModel;
import com.example.trip.entity.Weather;
import com.example.trip.locationselect.OrderHotel;
import com.example.trip.manager.ShareManager;
import com.example.trip.util.Configration;
import com.example.trip.util.Constant;
import com.example.trip.util.DatabaseOpenHelper;
import com.example.trip.util.ImageLoaderUtil;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;
import com.j256.ormlite.dao.Dao;

public class CityDetailsActivity extends Activity implements OnClickListener {
	private ImageView backgroundImg, weather;
	private TextView cityName, num, tempture;
	private ImageLoader loader;
	private RelativeLayout ac;
	private LinearLayout situation, ask, travelNotes, reserve;
	private CityModel city;
	private BeenCityModel bennCityModel;

	private String strlocation;
	private Weather weathers;
	private CheckBox collection;
	private CheckBox been;

	private Dao<CityModel, Integer> collectionCityDao;
	private Dao<BeenCityModel, Integer> bennDao;

	private ShareManager manager;

	// 景点
	private LinearLayout scenery;// 景点
	private LinearLayout delicous;// 美食
	private LinearLayout playGame;// 娱乐
	private LinearLayout lookParter;// 驴友

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_city_details);
		findViewById(R.id.shares).setOnClickListener(this);
		findViewById(R.id.hunter).setOnClickListener(this);

		collectionCityDao = DatabaseOpenHelper.getInstance(this)
				.getCollectionCityDao();
		bennDao = DatabaseOpenHelper.getInstance(this).getBeenDao();
		manager = new ShareManager(this);
		been = (CheckBox) findViewById(R.id.been);

		collection = (CheckBox) findViewById(R.id.collection);
		city = getIntent().getParcelableExtra("city");

		bennCityModel = new BeenCityModel(city.getCityName(), city.getCityId(),
				city.isCity(), city.getCityTraverNum(), city.getCityCategory(),
				city.getNameSort(), city.getCityImg());
		try {
			if (collectionCityDao.idExists(city.getCityId())) {
				collection.setChecked(true);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (bennDao.idExists(city.getCityId())) {
				been.setChecked(true);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		been.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					try {
						SimpleDateFormat sDateFormat = new SimpleDateFormat(
								"MM月");
						String time = sDateFormat.format(new java.util.Date());
						bennCityModel.setTime(time);
						bennDao.createIfNotExists(bennCityModel);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						if (bennDao.idExists(city.getCityId())) {
							bennDao.delete(bennCityModel);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		collection.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					try {
						collectionCityDao.createIfNotExists(city);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						if (collectionCityDao.idExists(city.getCityId())) {
							collectionCityDao.delete(city);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		weather = (ImageView) findViewById(R.id.weather);
		weather.setOnClickListener(this);
		tempture = (TextView) findViewById(R.id.tempture);
		backgroundImg = (ImageView) findViewById(R.id.backgroundImg);
		cityName = (TextView) findViewById(R.id.cityName);
		num = (TextView) findViewById(R.id.num);
		strlocation = city.getCityName();
		ac = (RelativeLayout) findViewById(R.id.actionbar);
		situation = (LinearLayout) findViewById(R.id.situation);
		situation.setOnClickListener(this);
		ask = (LinearLayout) findViewById(R.id.ask);
		ask.setOnClickListener(this);
		travelNotes = (LinearLayout) findViewById(R.id.travelNotes);
		travelNotes.setOnClickListener(this);

		// 添加景点点击事件
		scenery = (LinearLayout) findViewById(R.id.scenery);
		scenery.setOnClickListener(this);
		delicous = (LinearLayout) findViewById(R.id.delicous);
		delicous.setOnClickListener(this);
		playGame = (LinearLayout) findViewById(R.id.playGame);
		playGame.setOnClickListener(this);

		// 找驴友功能
		lookParter = (LinearLayout) findViewById(R.id.lookParter);
		lookParter.setOnClickListener(this);
		findViewById(R.id.backIcon).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		if (city != null) {
			cityName.setText(city.getCityName());
			num.setText("有" + city.getCityTraverNum() + "位蜂蜂去过");
			loader = new ImageLoader(TripApplication.getInstance()
					.getRequestQueue(), TripApplication.getInstance()
					.getImageCache());
			String url = city.getCityImg() + "";
			if (!url.equals("")) {
				url = UrlUtil.ROOT_URL + url;
			}
			ImageLoaderUtil.display(url, backgroundImg);
		}
		new weatherThread().start();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch (arg0.getId()) {
		case R.id.situation:
			intent = new Intent(this, ShortCityStrategyActivity.class);
			intent.putExtra("name", city.getCityName());
			startActivity(intent);
			break;
		case R.id.ask:
			intent = new Intent(this, SceneryAskActivity.class);
			// intent.putExtra("name", city.getCityName());
			startActivity(intent);
			break;
		case R.id.hunter:
			intent = new Intent(this, HunterMainActivity.class);
			// intent.putExtra("name", city.getCityName());
			startActivity(intent);
			break;
		case R.id.travelNotes:
			intent = new Intent(this, SceneryNoteActivity.class);
			intent.putExtra("city", city);
			startActivity(intent);
			break;
		case R.id.scenery:
			intent = new Intent(this, StrategySceneryActivity.class);
			intent.putExtra("city", city);
			intent.putExtra("isType", Constant.SCENERY_ISTYPE_ONE);
			startActivity(intent);
			break;
		case R.id.delicous:
			intent = new Intent(this, StrategySceneryActivity.class);
			intent.putExtra("city", city);
			intent.putExtra("isType", Constant.SCENERY_ISTYPE_TWO);
			startActivity(intent);
			break;
		case R.id.playGame:
			intent = new Intent(this, StrategySceneryActivity.class);
			intent.putExtra("city", city);
			intent.putExtra("isType", Constant.SCENERY_ISTYPE_THREE);
			startActivity(intent);
			break;
		case R.id.weather:
			if (weathers != null) {
				intent = new Intent(this, WeatherActivity.class);
				intent.putExtra("weather", weathers);
				startActivity(intent);
			}
			break;
		case R.id.lookParter:
			startActivity(new Intent(this, PartnerActivity.class));
			break;
		case R.id.shares:
			manager.create();
			manager.postShare();
			break;

		default:
			break;
		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		manager.onDestroy();
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
				if (w == null) {
					message.what = 2;
				} else {
					message.obj = w;
					message.what = 1;
				}
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
				String statu = w.getStatus1();
				weathers = w;
				if ("晴".equals(statu)) {
					weather.setImageResource(R.drawable.weathericon_sunny);
				} else if ("多云".equals(statu)) {
					weather.setImageResource(R.drawable.weathericon_cloudy);
				} else if ("阴".equals(statu)) {
					weather.setImageResource(R.drawable.weathericon_cloud);
				} else if (statu.contains("雨")) {
					weather.setImageResource(R.drawable.weathericon_rain);
				} else if ("雨夹雪".equals(statu)) {
					weather.setImageResource(R.drawable.weathericon_snow_rain);
				} else if (statu.contains("雪")) {
					weather.setImageResource(R.drawable.weathericon_snow);
				} else if ("多云转晴".equals(statu)) {
					weather.setImageResource(R.drawable.weathericon_partlycloudysun);
				} else if ("风".equals(statu)) {
					weather.setImageResource(R.drawable.weathericon_wind);
				} else if ("雾".equals(statu)) {
					weather.setImageResource(R.drawable.weathericon_fog);
				} else if ("闪电".equals(statu)) {
					weather.setImageResource(R.drawable.weathericon_lightning);
				}
				int tem = w.getTemperature1();
				tempture.setText(tem + "℃");
			} else if (msg.what == 2) {
				weather.setImageResource(R.drawable.weathericon_none);
				tempture.setText("");
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
					if (w.getCity() == null) {
						w = null;
					} else {
						return w;
					}
				}
			} else {
				Log.i("note", "数据请求异常！");
				w = null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return w;
	}
}
