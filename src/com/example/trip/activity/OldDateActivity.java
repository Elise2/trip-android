package com.example.trip.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.example.trip.R;
import com.example.trip.adapter.OldDateAdapter;
import com.example.trip.entity.Date;
import com.example.trip.util.StringPostRequest;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class OldDateActivity extends Activity implements OnClickListener {
	private ListView list;
	private OldDateAdapter adapter;
	private List<String> data;
	private List<Date> dates;
	private List<Date> date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_datedetail);
		findViewById(R.id.backdatadetail).setOnClickListener(this);
		 date = new ArrayList<Date>();
		((TextView) findViewById(R.id.detailtitles)).setText("我的日程");
		list = (ListView) findViewById(R.id.detailList);
		data = new ArrayList<String>();
		adapter = new OldDateAdapter(data, this);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				List<Date> datess=new ArrayList<Date>();
				for (int i = 0; i < date.size(); i++) {
					if(date.get(i).getDateId()==arg2+1){
						datess.add(date.get(i));
					}
				}
				Intent intent=new Intent(OldDateActivity.this,DateDetailActivity.class);
				intent.putExtra("dates", (Serializable)datess);
				startActivity(intent);

			}
		});
		initData();
	}

	public void initData() {
		StringPostRequest postRequest = new StringPostRequest(
				UrlUtil.TRIP_DATE_URL, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						Gson gson = new Gson();
						date = gson.fromJson(arg0,
								new TypeToken<ArrayList<Date>>() {
								}.getType());
						int count = 1;
						String string = "";
						for (int i = 0; i < date.size(); i++) {
							if (date.get(i).getDateId() != count) {
								count = date.get(i).getDateId();
								string = string.substring(0,
										string.length() - 1);
								data.add(string);
								string = new String();
							}
							string = string + date.get(i).getCityName() + "-";
						}
						string = string.substring(0, string.length() - 1);
						data.add(string);
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
		postRequest.PutParams("action", "select");
		TripApplication.getInstance().getRequestQueue().add(postRequest);

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
