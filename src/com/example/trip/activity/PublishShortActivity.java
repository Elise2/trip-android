package com.example.trip.activity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.example.trip.R;
import com.example.trip.entity.Community;
import com.example.trip.locationselect.ActivitySelectCity;
import com.example.trip.locationselect.MainActivity;
import com.example.trip.util.StringPostRequest;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.stat.common.p;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PublishShortActivity extends Activity implements OnClickListener {
	private ImageView img;
	private TextView commit, area;
	private EditText content;
	private String imgString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_publicshort);
		findViewById(R.id.back).setOnClickListener(this);
		img = (ImageView) findViewById(R.id.img);
		content = (EditText) findViewById(R.id.content);
		area = (TextView) findViewById(R.id.location);
		findViewById(R.id.goLocation).setOnClickListener(this);

		findViewById(R.id.commit).setOnClickListener(this);
		Intent intent = getIntent();
		if (intent != null) {
			byte[] bis = intent.getByteArrayExtra("bitmap");
			Bitmap bitmap = BitmapFactory.decodeByteArray(bis, 0, bis.length);
			img.setImageBitmap(bitmap);
			imgString = convertBitmap(bitmap);
		}

	}

	public String convertBitmap(Bitmap bitmap) {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		// 将图片进行压缩
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
		try {
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] buffer = outputStream.toByteArray();
		byte[] encode = Base64.encode(buffer, Base64.DEFAULT);
		return new String(encode);
	}

	public void initData(String location, String content) {
		StringPostRequest postRequest = new StringPostRequest(
				UrlUtil.TRIP_SHORT_URL, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						finish();

					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(PublishShortActivity.this, "网络连接错误",
								Toast.LENGTH_LONG).show();
					}
				});
		postRequest.PutParams("action", "add");
		postRequest.PutParams("userId", TripApplication.getInstance().getUser()
				.getUser_id()
				+ "");
		postRequest.PutParams("img", imgString);
		postRequest.PutParams("location", location);
		postRequest.PutParams("content", content);
		TripApplication.getInstance().getRequestQueue().add(postRequest);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.commit:
			String contents = content.getText().toString();
			String location = area.getText().toString();
			initData(location, contents);
			break;
		case R.id.goLocation:
			startActivityForResult(new Intent(this, ActivitySelectCity.class),
					99);
			break;
		default:
			break;
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		try {
			switch (resultCode) {
			case 99:
				area.setText(data.getStringExtra("lngCityName"));
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
