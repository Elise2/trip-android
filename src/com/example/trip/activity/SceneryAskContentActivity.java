package com.example.trip.activity;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.baidu.location.BDLocation.a;
import com.example.trip.R;
import com.example.trip.adapter.SceneryAskContentAdapter;
import com.example.trip.entity.Answer;
import com.example.trip.entity.Ask;
import com.example.trip.util.ImageLoaderUtil;
import com.example.trip.util.StringPostRequest;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SceneryAskContentActivity extends Activity implements
		OnClickListener {
	private int city_Id;
	private int ask_Id;
	private SceneryAskContentAdapter adapter;
	private List<Answer> datas;
	private ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_travel_ask_content);
		findViewById(R.id.settingImg).setOnClickListener(this);
		Ask ask = (Ask) getIntent().getSerializableExtra("ask");

		ask_Id = ask.getAskId();
		city_Id = ask.getCityId();
		datas = new ArrayList<Answer>();
		list = (ListView) findViewById(R.id.list);
		View v = LayoutInflater.from(this).inflate(
				R.layout.layout_travel_ask_content_head, null);
		TextView name = (TextView) v.findViewById(R.id.askName);
		TextView content = (TextView) v.findViewById(R.id.askContent);
		TextView time = (TextView) v.findViewById(R.id.askTime);
		TextView title = (TextView) v.findViewById(R.id.askTitle);
		ImageView face = (ImageView) v.findViewById(R.id.askFace);
		TextView bg = (TextView) v.findViewById(R.id.bg);
		name.setText(ask.getAskUserName());
		title.setText(ask.getAskTitle());
		time.setText(ask.getAskTime());
		content.setText(ask.getAskContent());
		bg.setText("共" + ask.getAnswerNum() + "条回答");
		String userUrl = ask.getAskUserImg() + "";
		if (!userUrl.equals("")) {
			userUrl = UrlUtil.ROOT_URL + userUrl;
		}
		ImageLoaderUtil.display(userUrl, face);
		list.addHeaderView(v);
		adapter = new SceneryAskContentAdapter(datas, this);
		list.setAdapter(adapter);
		initView();
	}

	public void initView() {

		StringPostRequest stringPost = new StringPostRequest(
				UrlUtil.TRIP_ANSWER_URL, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub
						Gson gson = new Gson();
						List<Answer> asks = new ArrayList<Answer>();
						asks = gson.fromJson(arg0,
								new TypeToken<ArrayList<Answer>>() {
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
		stringPost.PutParams("city_id", city_Id + "");
		stringPost.PutParams("ask_id", ask_Id + "");
		TripApplication.getInstance().getRequestQueue().add(stringPost);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.settingImg:
			finish();
			break;

		default:
			break;
		}
	}
}
