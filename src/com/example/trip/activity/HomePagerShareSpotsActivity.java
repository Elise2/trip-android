package com.example.trip.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.example.trip.R;
import com.example.trip.adapter.ShareSpotAdapter;
import com.example.trip.entity.CityModel;
import com.example.trip.entity.ShareSpotModel;
import com.example.trip.entity.User;
import com.example.trip.util.StringPostRequest;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 花样撒野每个专题的具体实现model
 * 
 * @author Administrator
 * 
 */
public class HomePagerShareSpotsActivity extends Activity {
	private ListView myList;
	private List<ShareSpotModel> mdatas;
	private List<CityModel> cities;
	private List<User> users;
	private ShareSpotAdapter adapter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepager_traveltype_list);
		myList = (ListView) findViewById(R.id.travelTypeList);
		cities = new ArrayList<CityModel>();
		users = new ArrayList<User>();
		mdatas = new ArrayList<ShareSpotModel>();
		initHeaderview();
		initData();
		adapter = new ShareSpotAdapter(mdatas, cities, users, this);
		myList.setAdapter(adapter);
	}

	private void initData() {
		StringPostRequest request = new StringPostRequest(
				UrlUtil.TRIP_VARIETY_URL, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						List<ShareSpotModel> s = new ArrayList<ShareSpotModel>();
						List<CityModel> c = new ArrayList<CityModel>();
						List<User> u = new ArrayList<User>();
						JSONArray array;
						try {
							array = new JSONArray(arg0);
							for (int i = 0; i < array.length(); i++) {
								JSONObject object = array.getJSONObject(i);
								CityModel city = new CityModel();
								User user = new User();
								ShareSpotModel shareSpotModel = new ShareSpotModel();
								String name = object.getString("cityName");
								shareSpotModel.setSpotLocation(name);
								city.setCityName(name);
								name = object.getString("username");
								shareSpotModel.setPhotoByName(name);
								user.setUsername(name);
								city.setCityImg(object.getString("cityImg"));
								city.setCityTraverNum(object
										.getInt("cityTraverNum"));
								user.setUserLevel(object
										.getString("user_level"));
								user.setUserImg(object.getString("userimg"));
								city.setCityId(object.getInt("cityId"));
								user.setUser_id(object.getInt("user_id"));
								shareSpotModel.setShareReason(object
										.getString("shareReason"));
								shareSpotModel.setTopImg(object
										.getString("topImg"));
								s.add(shareSpotModel);
								c.add(city);
								u.add(user);
							}
							if (s.size() > 0) {
								cities.clear();
								users.clear();
								cities.addAll(c);
								users.addAll(u);
								mdatas.clear();
								mdatas.addAll(s);
								adapter.notifyDataSetChanged();
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "fdfd",
								Toast.LENGTH_LONG).show();
					}
				});
		request.PutParams("type", 1 + "");
		TripApplication.getInstance().getRequestQueue().add(request);
	}

	public void initHeaderview() {
		View headerView = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.homepager_traveltype_headerview, null);
		myList.addHeaderView(headerView);
	}

	private void initData1() {
		mdatas.add(new ShareSpotModel("新加坡", "", "elise",
				"新加坡是东南亚的一个岛国，也是一个城市国家。该国位于马来半岛南端，毗邻马六甲海峡南口，其南面有新加坡海峡与印尼相隔"));
		mdatas.add(new ShareSpotModel(
				"新西兰",
				"",
				"Bulse",
				"新西兰（New Zealand），又译纽西兰，是一个政治体制实行君主立宪制混合英国式议会民主制的国家，新西兰位于太平洋西南部，领土由南岛、北岛两大岛屿组成，以库克海峡分隔，南岛邻近南极洲，北岛与斐济及汤加相望。首都惠灵顿以及最大城市奥克兰均位于北岛"));
		mdatas.add(new ShareSpotModel("新加坡", "", "elise",
				"新加坡是东南亚的一个岛国，也是一个城市国家。该国位于马来半岛南端，毗邻马六甲海峡南口，其南面有新加坡海峡与印尼相隔"));
		mdatas.add(new ShareSpotModel(
				"新西兰",
				"",
				"Bulse",
				"新西兰（New Zealand），又译纽西兰，是一个政治体制实行君主立宪制混合英国式议会民主制的国家，新西兰位于太平洋西南部，领土由南岛、北岛两大岛屿组成，以库克海峡分隔，南岛邻近南极洲，北岛与斐济及汤加相望。首都惠灵顿以及最大城市奥克兰均位于北岛"));
		mdatas.add(new ShareSpotModel("新加坡", "", "elise",
				"新加坡是东南亚的一个岛国，也是一个城市国家。该国位于马来半岛南端，毗邻马六甲海峡南口，其南面有新加坡海峡与印尼相隔"));
		mdatas.add(new ShareSpotModel(
				"新西兰",
				"",
				"Bulse",
				"新西兰（New Zealand），又译纽西兰，是一个政治体制实行君主立宪制混合英国式议会民主制的国家，新西兰位于太平洋西南部，领土由南岛、北岛两大岛屿组成，以库克海峡分隔，南岛邻近南极洲，北岛与斐济及汤加相望。首都惠灵顿以及最大城市奥克兰均位于北岛"));
	}

}
