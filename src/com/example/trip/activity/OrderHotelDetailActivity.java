package com.example.trip.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.example.trip.R;
import com.example.trip.adapter.HotelDetailAdapter;
import com.example.trip.entity.Hotel;
import com.example.trip.util.ImageLoaderUtil;
import com.example.trip.util.StringPostRequest;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class OrderHotelDetailActivity extends Activity implements
		OnClickListener {
	private Hotel hotel;
	private ListView hotelList;
	private ImageView travelImg;
	private ImageView headerImg;
	private TextView hotelName;
	private TextView hotelSupport;
	private TextView hotelContent;
	private View view;
	private List<Hotel> mdata;
	private HotelDetailAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hoteldetail_listview);
		hotel = (Hotel) getIntent().getSerializableExtra("hotel");
		initHeaderView();
		initHeaderData();
		hotelList.addHeaderView(view);
		initHotelDetail();
		mdata = new ArrayList<Hotel>();
		adapter = new HotelDetailAdapter(mdata, getApplicationContext());
		// 点击预约按钮
		findViewById(R.id.orderClick).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						OrderForHotelActivity.class);
				intent.putExtra("hotel", hotel);
				intent.putExtra("isTop", 1);
				startActivity(intent);
			}
		});
		hotelList.setAdapter(adapter);
	}

	public void initHeaderView() {
		view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.hoteldetail_headview, null);
		view.findViewById(R.id.orderHmg).setOnClickListener(this);
		hotelList = (ListView) findViewById(R.id.hoteldetailList1);
		travelImg = (ImageView) view.findViewById(R.id.orderHmg);// 返回按钮
		headerImg = (ImageView) view.findViewById(R.id.orderheaderImg);
		hotelName = (TextView) view.findViewById(R.id.orderhotelName);
		hotelSupport = (TextView) view.findViewById(R.id.hotelSupport);
		hotelContent = (TextView) view.findViewById(R.id.orderhotelContent);
	}

	public void initHeaderData() {
		travelImg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		String url = hotel.getOrder_img() + "";
		if (url != null) {
			url = UrlUtil.ROOT_URL + url;
			ImageLoaderUtil.display(url, headerImg);
		}

		hotelName.setText(hotel.getOrder_name());
		hotelSupport.setText(hotel.getOrder_recommentNum());
		hotelContent.setText(hotel.getOrder_descript());
	}

	public void initHotelDetail() {
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
		postRequest.PutParams("action", "detailhotel");
		postRequest.PutParams("order_id", hotel.getOrder_id() + "");
		TripApplication.getInstance().getRequestQueue().add(postRequest);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.orderHmg:
			finish();
			break;

		default:
			break;
		}

	}

}
