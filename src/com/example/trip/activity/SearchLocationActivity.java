package com.example.trip.activity;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.example.trip.R;
import com.example.trip.entity.CityModel;
import com.example.trip.util.StringPostRequest;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchLocationActivity extends Activity implements OnClickListener {
	private EditText content;
	private TextView search;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_searchlocation);
		search = (TextView) findViewById(R.id.searchs);
		content = (EditText) findViewById(R.id.content);
		search.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.searchs:
			String s = content.getText().toString();
			initCityItem(s);
			break;
		default:
			break;
		}
	}

	public void initCityItem(String name) {
		StringPostRequest request = new StringPostRequest(
				UrlUtil.TRIP_CITY_URL, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						List<CityModel> citys = new ArrayList<CityModel>();
						Gson gson = new Gson();
						citys = gson.fromJson(arg0,
								new TypeToken<ArrayList<CityModel>>() {
								}.getType());
						if (citys != null && citys.size() > 0) {
							Intent intent = new Intent(getApplicationContext(),
									CityDetailsActivity.class);
							intent.putExtra("city", citys.get(0));
							startActivity(intent);
						} else {
							new AlertDialog.Builder(SearchLocationActivity.this)
									.setTitle("提示").setMessage("请输入正确地址")
									.setPositiveButton("确定", null).show();
						}

					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "网络连接失败",
								Toast.LENGTH_LONG).show();
					}
				});
		request.PutParams("action", "select");
		request.PutParams("cityName", name);
		TripApplication.getInstance().getRequestQueue().add(request);
	}
}
