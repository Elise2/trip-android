package com.example.trip.activity;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.example.trip.R;
import com.example.trip.adapter.HunterdetailAdapter;
import com.example.trip.entity.Hunter;
import com.example.trip.util.ImageLoaderUtil;
import com.example.trip.util.StringPostRequest;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HunterDetailActivity extends Activity {
	private Hunter hunter;
	private ListView hunterdetailList;
	private View view;
	private ImageView hunterHeaderImg;
	private ImageView hunterImg;
	private TextView hunterName;
	private Button contactHunter;
	private TextView hunterTitle;
	private LinearLayout hunterType;
	private TextView orderDetailTime;
	private TextView orderLocation;
	private ImageView hunterImg1;
	private TextView hunterInfo;
	private List<Hunter> mdata;
	private HunterdetailAdapter adapter;
	private Button orderClick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hunterdetail_listview);
		hunter = (Hunter) getIntent().getSerializableExtra("hunter");
		hunterdetailList = (ListView) findViewById(R.id.hunterdetailList1);
		orderClick = (Button) findViewById(R.id.hunterorderClick);
		initHeaderView();
		hunterdetailList.addHeaderView(view);
		initHeaderViewData();
		mdata = new ArrayList<Hunter>();
		adapter = new HunterdetailAdapter(mdata, getApplicationContext());
		hunterdetailList.setAdapter(adapter);
		// initFooterView();
		// initFooterData();
		initFooterViewData();
		orderClick.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						OrderForHotelActivity.class);
				intent.putExtra("hunter", hunter);
				intent.putExtra("isTop", 2);
				startActivity(intent);
			}
		});
	}

	public void initHeaderView() {
		view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.hunterdetail_headerview, null);
		hunterHeaderImg = (ImageView) view.findViewById(R.id.hunterHeaderImg);// hunter_title_img
		hunterImg = (ImageView) view.findViewById(R.id.hunterImg);
		hunterName = (TextView) view.findViewById(R.id.hunterName);
		contactHunter = (Button) view.findViewById(R.id.contactHunter);
		hunterTitle = (TextView) view.findViewById(R.id.hunterTitle);
		hunterType = (LinearLayout) view.findViewById(R.id.hunterType);
		orderDetailTime = (TextView) view.findViewById(R.id.orderDetailTime);
		orderLocation = (TextView) view.findViewById(R.id.orderLocation);
	}

	@SuppressLint("NewApi")
	public void initHeaderViewData() {
		String url = hunter.getHunter_title_img() + "";
		if (url != null) {
			url = UrlUtil.ROOT_URL + url;
			ImageLoaderUtil.display(url, hunterHeaderImg);
		}
		String hunterUrl = hunter.getHunter_img() + "";
		if (hunterUrl != null) {
			hunterUrl = UrlUtil.ROOT_URL + hunterUrl;
			ImageLoaderUtil.display(hunterUrl, hunterImg);
		}

		hunterName.setText(hunter.getHunter_name() + "");
		hunterTitle.setText(hunter.getHunter_descript() + "");
		String str = hunter.getHunter_label();
		String[] ArrayStr = null;
		if (str == null || "".equals(str)) {
		} else {
			ArrayStr = converStrToArray(str);
		}
		for (int i = 0; i < ArrayStr.length; i++) {
			Button button = new Button(this);
			button.setTextColor(Color.GRAY);
			button.setTextSize(10);
			button.setBackground(getApplicationContext().getResources()
					.getDrawable(R.drawable.btnbg));
			button.setText(ArrayStr[i]);
			hunterType.addView(button);
		}
		orderDetailTime.setText(hunter.getHunter_time() + "");
		orderLocation.setText(hunter.getHunter_location() + "");
	}

	public void initFooterView() {
		hunterImg1 = (ImageView) findViewById(R.id.hunterImg1);
		hunterInfo = (TextView) findViewById(R.id.hunterInfo);
	}

	public void initFooterData() {
		hunterInfo.setText(hunter.getHunter_detatils_info() + "");
		String hunterImgUrl = hunter.getHunter_img() + "";
		if (hunterImgUrl != null) {
			hunterImgUrl = UrlUtil.ROOT_URL + hunterImgUrl;
			ImageLoaderUtil.display(hunterImgUrl, hunterImg);
		}
	}

	public static String[] converStrToArray(String str) {
		String[] strArray = null;
		strArray = str.split(",");
		return strArray;
	}

	public void initFooterViewData() {
		StringPostRequest postRequest = new StringPostRequest(
				UrlUtil.TRIP_HUNTER_URL, new Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						List<Hunter> hunters = new ArrayList<Hunter>();
						Gson gson = new Gson();
						hunters = gson.fromJson(arg0,
								new TypeToken<ArrayList<Hunter>>() {
								}.getType());
						if (hunters.size() > 0) {
							mdata.clear();
							mdata.addAll(hunters);
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
		postRequest.PutParams("action", "detailhunter");
		postRequest.PutParams("huntermain_id", hunter.getHuntermain_id() + "");
		TripApplication.getInstance().getRequestQueue().add(postRequest);
	}
}
