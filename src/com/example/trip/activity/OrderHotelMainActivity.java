package com.example.trip.activity;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.example.trip.R;
import com.example.trip.adapter.HotelMainAdapter;
import com.example.trip.entity.Hotel;
import com.example.trip.entity.PlayNote;
import com.example.trip.util.StringPostRequest;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class OrderHotelMainActivity extends Activity {
	private String city_name;
	private List<Hotel> mdata;
	private ListView hotelList;
	private HotelMainAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hotelmain_listview);
		findViewById(R.id.hotelImgback).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		hotelList = (ListView) findViewById(R.id.hotelList);
		hotelList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Hotel hotel = (Hotel) adapter.getItem(position);
				Intent intent = new Intent(getApplicationContext(),
						OrderHotelDetailActivity.class);
				intent.putExtra("hotel", hotel);
				startActivity(intent);
				
			}
		});
		city_name = getIntent().getStringExtra("city_name");
		mdata = new ArrayList<Hotel>();
		adapter = new HotelMainAdapter(mdata, this);
		hotelList.setAdapter(adapter);
		initHotel();
		
	}
	public void initHotel() {
		StringPostRequest postRequest = new StringPostRequest(
				UrlUtil.TRIP_HOTEL_URL, new Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						List<Hotel> hotels = new ArrayList<Hotel>();
						Gson gson = new Gson();
						hotels = gson.fromJson(arg0,
								new TypeToken<ArrayList<Hotel>>() {
								}.getType());
						if (hotels.size() > 0) {
							mdata.clear();
							mdata.addAll(hotels);
							adapter.notifyDataSetChanged();
						}
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "网络连接错误",
								Toast.LENGTH_LONG).show();
					}
				});
		postRequest.PutParams("action", "tophotel");
		postRequest.PutParams("city_name","青岛");
		TripApplication.getInstance().getRequestQueue().add(postRequest);
	}
	
}
