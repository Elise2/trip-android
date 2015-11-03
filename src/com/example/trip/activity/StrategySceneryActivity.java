package com.example.trip.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.example.trip.R;
import com.example.trip.adapter.StrategySceneryAdapter;
import com.example.trip.entity.CityModel;
import com.example.trip.entity.Scenery;
import com.example.trip.manager.ShareManager;
import com.example.trip.util.StringPostRequest;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class StrategySceneryActivity extends Activity {
	private List<Scenery> mdata;
	// 新增属性
	private List<Scenery> topData;
	private ListView myList;
	private StrategySceneryAdapter adapter;
	private CityModel city;
	private int isType;
	private ShareManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.strategy_scenery_listview);
		manager = new ShareManager(this);
		Intent intent = getIntent();
		city = intent.getParcelableExtra("city");
		isType = getIntent().getIntExtra("isType", 0);
		TextView title = (TextView) findViewById(R.id.title);
		if (isType == 1) {
			title.setText("景点");
		} else if (isType == 2) {
			title.setText("美食");
		} else if (isType == 3) {
			title.setText("娱乐");
		}
		myList = (ListView) findViewById(R.id.sceneryList);
		mdata = new ArrayList<Scenery>();
		topData = new ArrayList<Scenery>();
		findViewById(R.id.sceneryImgback).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						finish();
					}
				});
		findViewById(R.id.sceneryShareImg).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						manager.create();
						manager.postShare();
					}
				});
		// 新增功能，新增每个Item
		myList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Scenery scenery = (Scenery) adapter.getItem(position);
				Intent intent = new Intent(getApplicationContext(),
						SceneryContentActivity.class);
				intent.putExtra("scenery", scenery);
				intent.putExtra("scenerys", (Serializable) mdata);
				startActivity(intent);
			}
		});
		adapter = new StrategySceneryAdapter(topData, getApplicationContext());
		myList.setAdapter(adapter);
		getData(city, isType);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		manager.onDestroy();
	}

	private void getData(CityModel city, int isType) {
		StringPostRequest stringPost = new StringPostRequest(
				UrlUtil.TRIP_SCENENRY_URL, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub
						try {
							JSONArray jsonArray = new JSONArray(arg0);
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject jsonObject = jsonArray
										.getJSONObject(i);
								Scenery scenery = new Scenery();
								scenery.setSceneryType(jsonObject
										.getString("scenery_type"));
								scenery.setRecommendContent(jsonObject
										.getString("comment"));
								scenery.setUsername(jsonObject
										.getString("username"));
								scenery.setImg(jsonObject
										.getString("scenery_img"));
								scenery.setTalkNum(jsonObject
										.getInt("scenery_talkNum"));
								scenery.setTitle(jsonObject
										.getString("scenery_title"));
								scenery.setTalkNum(jsonObject
										.getInt("scenery_talkNum"));
								scenery.setName(jsonObject
										.getString("sceneryname"));
								scenery.setIsTop(jsonObject.getInt("isTop"));
								// 新增属性
								scenery.setUserImg(jsonObject
										.getString("userimg"));
								mdata.add(scenery);
								topData.clear();
								for (int j = 0; j < mdata.size(); j++) {
									if (mdata.get(j).getIsTop() == 1) {
										topData.add(mdata.get(j));
									}
								}
							}
							adapter.notifyDataSetChanged();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "网络加载失败",
								Toast.LENGTH_LONG).show();
					}
				});
		stringPost.PutParams("city_id", 35 + "");
		stringPost.PutParams("isType", isType + "");
		TripApplication.getInstance().getRequestQueue().add(stringPost);
	}

}
