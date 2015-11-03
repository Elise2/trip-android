package com.example.trip.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.example.trip.R;
import com.example.trip.activity.SceneryContentActivity.sceneryThread;
import com.example.trip.adapter.StrategySceneryAdapter;
import com.example.trip.entity.Date;
import com.example.trip.entity.Scenery;
import com.example.trip.util.StringPostRequest;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;

public class SceneryListActivity extends Activity {
	private List<Scenery> mdata;
	// 新增属性
	private List<Scenery> topData;
	private ListView myList;
	private StrategySceneryAdapter adapter;
	private Date date;
	private int isType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		Intent intent = getIntent();
		date = (Date) intent.getSerializableExtra("datedetail");
		TextView title = (TextView) findViewById(R.id.title);

		myList = (ListView) findViewById(R.id.list);
		mdata = new ArrayList<Scenery>();
		topData = new ArrayList<Scenery>();
		((TextView) findViewById(R.id.title)).setText("添加景点");
		findViewById(R.id.settingImg).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		// 新增功能，新增每个Item
		myList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Scenery scenery = (Scenery) adapter.getItem(position);
				addData(scenery.getScenery_id());
			}
		});
		adapter = new StrategySceneryAdapter(topData, getApplicationContext());
		myList.setAdapter(adapter);
		getData(date, isType);
	}

	private void addData(int typeid) {
		StringPostRequest stringPost = new StringPostRequest(
				UrlUtil.TRIP_DATE_URL, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub
						JSONObject object;
						try {
							object = new JSONObject(arg0);
							String s = object.getString("info");
							if ("suc".equals(s)) {
								finish();
							} else if ("error".equals(s)) {
								Toast.makeText(SceneryListActivity.this,
										"添加失败", Toast.LENGTH_LONG).show();
								finish();
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
						Toast.makeText(getApplicationContext(), "网络加载失败",
								Toast.LENGTH_LONG).show();
					}
				});

		stringPost.PutParams("action", "adddetailscenery");
		stringPost.PutParams("type", 1 + "");
		stringPost.PutParams("type_id", typeid + "");
		stringPost.PutParams("datedetailid", date.getDateDetailId() + "");
		TripApplication.getInstance().getRequestQueue().add(stringPost);
	}

	private void getData(Date date, int isType) {
		StringPostRequest stringPost = new StringPostRequest(
				UrlUtil.TRIP_DATE_URL, new Listener<String>() {
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
								scenery.setScenery_id(jsonObject
										.getInt("scenery_id"));
								mdata.add(scenery);

							}
							topData.clear();
							for (int j = 0; j < mdata.size(); j++) {
								if (mdata.get(j).getIsTop() == 1) {
									topData.add(mdata.get(j));
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
		stringPost.PutParams("action", "selectallscenery");
		stringPost.PutParams("city", 35 + "");
		TripApplication.getInstance().getRequestQueue().add(stringPost);
	}

}
