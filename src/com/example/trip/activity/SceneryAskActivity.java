package com.example.trip.activity;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.example.trip.R;
import com.example.trip.adapter.SceneryAskAdapter;
import com.example.trip.adapter.SceneryNoteAdapter;
import com.example.trip.entity.Ask;
import com.example.trip.entity.CityModel;
import com.example.trip.entity.PlayNote;
import com.example.trip.entity.Scenery;
import com.example.trip.util.StringPostRequest;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SceneryAskActivity extends Activity implements OnClickListener {
	private ListView list;
	private List<Ask> datas;
	private SceneryAskAdapter adapter;
	private int city_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_travel_ask);
		findViewById(R.id.settingImg).setOnClickListener(this);
		findViewById(R.id.askBtn).setOnClickListener(this);

		city_id = getIntent().getIntExtra("city_id", 35);
		list = (ListView) findViewById(R.id.List);
		datas = new ArrayList<Ask>();
		adapter = new SceneryAskAdapter(datas, this);
		list.setAdapter(adapter);
		initView();
		initEvent();

	}

	public void initEvent() {
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Ask ask = (Ask) adapter.getItem(arg2);
				Intent intent = new Intent(SceneryAskActivity.this,
						SceneryAskContentActivity.class);
				intent.putExtra("ask", ask);
				startActivity(intent);
			}
		});

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		initView();
	}

	public void initView() {

		StringPostRequest stringPost = new StringPostRequest(
				UrlUtil.TRIP_ASK_URL, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub
						Gson gson = new Gson();
						List<Ask> asks = new ArrayList<Ask>();
						asks = gson.fromJson(arg0,
								new TypeToken<ArrayList<Ask>>() {
								}.getType());
						if (asks.size() != 0) {
							datas.clear();
							datas.addAll(asks);
						}

						adapter.notifyDataSetChanged();
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "加载失败",
								Toast.LENGTH_LONG).show();
					}
				});
		stringPost.PutParams("action", "select");
		stringPost.PutParams("city_id", city_id + "");
		TripApplication.getInstance().getRequestQueue().add(stringPost);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.settingImg:
			finish();
			break;
		case R.id.askBtn:
			if (TripApplication.getInstance().getUser() == null
					|| "".equals(TripApplication.getInstance().getUser()
							.getUsername())) {
				Toast.makeText(this, "请先登录", Toast.LENGTH_LONG).show();
			} else {
				Intent intent = new Intent(this, AddAskActivity.class);
				intent.putExtra("city_id", city_id+"");
				startActivity(intent);
			}
			break;

		default:
			break;
		}
	}
}
