package com.example.trip.activity;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.example.trip.R;
import com.example.trip.adapter.HunterMainListAdapter;
import com.example.trip.adapter.HunterdetailAdapter;
import com.example.trip.entity.Hotel;
import com.example.trip.entity.Hunter;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 找猎人的列表界面
 * 
 * @author lewei
 *
 */
public class HunterMainActivity extends Activity {
	private ListView hunterMainList;
	private ImageView backIcon;
	private List<Hunter> mdata;
	private HunterMainListAdapter adapter;
	private int city_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.huntermain_listview);
		hunterMainList = (ListView) findViewById(R.id.hunterMainList1);
		backIcon = (ImageView) findViewById(R.id.mainhunterImg);
		city_id = getIntent().getIntExtra("city_id", 35);
		mdata = new ArrayList<Hunter>();
		hunterMainList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Hunter hunter = (Hunter) adapter.getItem(position);
				Intent intent = new Intent(getApplicationContext(),
						HunterDetailActivity.class);
				intent.putExtra("hunter", hunter);
				startActivity(intent);
			}
		});

		backIcon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		adapter = new HunterMainListAdapter(mdata, getApplicationContext());
		hunterMainList.setAdapter(adapter);
		initViewData();

	}

	public void initViewData() {
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
		postRequest.PutParams("action", "tophunter");
		postRequest.PutParams("city_id", 35 + "");
		TripApplication.getInstance().getRequestQueue().add(postRequest);
	}

}
