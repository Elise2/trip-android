package com.example.trip.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.example.trip.R;
import com.example.trip.util.StringPostRequest;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class AddAskActivity extends Activity implements OnClickListener {
	private EditText title, content;
	private String city_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_addask);
		findViewById(R.id.sendAsk).setOnClickListener(this);
		title = (EditText) findViewById(R.id.Asktitle);
		content = (EditText) findViewById(R.id.Askcontent);
		city_id = getIntent().getStringExtra("city_id");
	}

	public void initData(String title, String content) {
		StringPostRequest postRequest = new StringPostRequest(
				UrlUtil.TRIP_ASK_URL, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						JSONObject object;
						try {
							object = new JSONObject(arg0);
							String s = object.getString("info");
							if ("suc".equals(s)) {
								finish();
							} else if ("error".equals(s)) {
								Toast.makeText(AddAskActivity.this, "提问失败",
										Toast.LENGTH_LONG).show();
							}
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(AddAskActivity.this, "网络连接错误",
								Toast.LENGTH_LONG).show();
					}
				});
		postRequest.PutParams("action", "add");
		postRequest.PutParams("ask_user_id", TripApplication.getInstance()
				.getUser().getUser_id()
				+ "");
		postRequest.PutParams("city_id", city_id);
		postRequest.PutParams("ask_problem_title", title);
		postRequest.PutParams("ask_problem_content", content);
		TripApplication.getInstance().getRequestQueue().add(postRequest);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.sendAsk:
			String titles = title.getText().toString();
			String contents = content.getText().toString();
			if (titles == null || "".equals(titles)) {
				Toast.makeText(this, "请先将标题填写完整", Toast.LENGTH_LONG).show();
			} else if (contents == null || "".equals(contents)) {
				Toast.makeText(this, "请先将内容填写完整", Toast.LENGTH_LONG).show();
			} else {
				initData(titles, contents);
			}

			break;
		default:
			break;
		}

	}

}
