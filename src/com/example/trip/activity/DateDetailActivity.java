package com.example.trip.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.baidu.location.BDLocation.a;
import com.example.trip.R;
import com.example.trip.adapter.DateDetailAdapter;
import com.example.trip.entity.Date;
import com.example.trip.util.StringPostRequest;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.stmt.query.NeedsFutureClause;

public class DateDetailActivity extends Activity implements OnClickListener {
	private List<Date> dates;
	private DateDetailAdapter adapter;
	private ListView detailList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_datedetail);
		findViewById(R.id.backdatadetail).setOnClickListener(this);
		detailList = (ListView) findViewById(R.id.detailList);
		dates = (List<Date>) getIntent().getSerializableExtra("dates");
		adapter = new DateDetailAdapter(dates, this);
		detailList.setAdapter(adapter);

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		initData();

	}

	public void initData() {
		StringPostRequest postRequest = new StringPostRequest(
				UrlUtil.TRIP_DATE_URL, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						JSONArray array;
						try {
							array = new JSONArray(arg0);
							for (int i = 0; i < array.length(); i++) {
								JSONObject object = array.getJSONObject(i);
								dates.get(i).setScenery(
										Integer.parseInt(object.get("scenery")
												.toString()));
								dates.get(i).setPlayote(
										Integer.parseInt(object.get("playnote")
												.toString()));
								dates.get(i).setHotel(
										Integer.parseInt(object.get("hotel")
												.toString()));
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						adapter.notifyDataSetChanged();

					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "网络连接错误",
								Toast.LENGTH_LONG).show();
					}
				});
		postRequest.PutParams("action", "selectdetail");
		postRequest.PutParams("date", object2json(dates));
		TripApplication.getInstance().getRequestQueue().add(postRequest);

	}

	public static String object2json(List<Date> obj) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		for (int i = 0; i < obj.size(); i++) {
			json.append("{\"id\":" + "\"" + obj.get(i).getDateDetailId()
					+ "\"}");
			json.append(",");
		}
		json.deleteCharAt(json.length() - 1);
		json.append("]");
		System.out.print(json.toString());
		return json.toString();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.backdatadetail:
			finish();
			break;

		default:
			break;
		}

	}

}
