package com.example.trip.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.baidu.location.BDLocation.a;
import com.example.trip.R;
import com.example.trip.adapter.DiscussAdapter;
import com.example.trip.adapter.SceneryAskContentAdapter;
import com.example.trip.entity.Answer;
import com.example.trip.entity.Ask;
import com.example.trip.entity.Community;
import com.example.trip.entity.Discuss;
import com.example.trip.manager.ShareManager;
import com.example.trip.util.ImageLoaderUtil;
import com.example.trip.util.StringPostRequest;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class DiscussActivity extends Activity implements OnClickListener {
	private int city_Id;
	private int ask_Id;
	private DiscussAdapter adapter;
	private List<Discuss> datas;
	private ListView list;
	private Community data;
	private EditText content;
	private ShareManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_discuss);
		manager = new ShareManager(this);
		findViewById(R.id.backcommunity).setOnClickListener(this);
		findViewById(R.id.shareCommunity).setOnClickListener(this);
		findViewById(R.id.send).setOnClickListener(this);
		content = (EditText) findViewById(R.id.sendcontent);
		data = (Community) getIntent().getSerializableExtra("community");
		View arg1 = initData(data);
		datas = new ArrayList<Discuss>();
		list = (ListView) findViewById(R.id.discussList);
		list.addHeaderView(arg1);
		adapter = new DiscussAdapter(datas, this);
		list.setAdapter(adapter);
		initView();
	}

	private View initData(Community data) {
		View arg1 = LayoutInflater.from(this).inflate(R.layout.item_community,
				null);
		if (data != null) {
			ImageView face = (ImageView) arg1.findViewById(R.id.face);
			ImageView img = (ImageView) arg1.findViewById(R.id.img);
			TextView comment = (TextView) arg1.findViewById(R.id.comment);
			TextView name = (TextView) arg1.findViewById(R.id.userName);
			TextView level = (TextView) arg1.findViewById(R.id.userLevel);
			TextView time = (TextView) arg1.findViewById(R.id.times);
			TextView location = (TextView) arg1.findViewById(R.id.location);
			TextView content = (TextView) arg1.findViewById(R.id.content);
			final CheckBox like = (CheckBox) arg1.findViewById(R.id.like);
			name.setText(data.getShortCommentname());
			time.setText(data.getShortCommentTime());
			comment.setText(data.getShortCommentNum() + "");
			level.setText(data.getShortCommentLevel());
			location.setText(data.getShortCommentLocation());
			if ("".equals(data.getShortCommentContent())) {
				content.setVisibility(View.GONE);
			} else {
				content.setVisibility(View.VISIBLE);
				content.setText(data.getShortCommentContent());
			}

			like.setText(data.getShortCommentLike() + "");
			String url = data.getShortCommentImg() + "";
			if (!url.equals("")) {
				url = UrlUtil.ROOT_URL + url;
				ImageLoaderUtil.display(url, img);
			}
			String faceUrl = data.getShortCommentFace() + "";
			if (!faceUrl.equals("")) {
				faceUrl = UrlUtil.ROOT_URL + faceUrl;
				ImageLoaderUtil.display(faceUrl, face);
			}
			like.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
					// TODO Auto-generated method stub
					if (arg1) {
						like.setText((Integer.parseInt((like.getText()
								.toString())) + 1) + "");
					} else {
						like.setText((Integer.parseInt((like.getText()
								.toString())) - 1) + "");
					}

				}
			});
		}
		return arg1;

	}

	public void initView() {
		StringPostRequest stringPost = new StringPostRequest(
				UrlUtil.TRIP_DISCUSS_URL, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub
						Gson gson = new Gson();
						List<Discuss> discusses = new ArrayList<Discuss>();
						discusses = gson.fromJson(arg0,
								new TypeToken<ArrayList<Discuss>>() {
								}.getType());
						if (discusses.size() != 0) {
							datas.clear();
							datas.addAll(discusses);
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
		stringPost.PutParams("shortCommentID", data.getShortCommentId() + "");
		TripApplication.getInstance().getRequestQueue().add(stringPost);
	}

	public void sendData(String content) {
		StringPostRequest postRequest = new StringPostRequest(
				UrlUtil.TRIP_DISCUSS_URL, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						JSONObject object;
						try {
							object = new JSONObject(arg0);
							String s = object.getString("info");
							if ("suc".equals(s)) {
								initView();
							} else if ("error".equals(s)) {
								Toast.makeText(DiscussActivity.this, "回复失败",
										Toast.LENGTH_LONG).show();
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(DiscussActivity.this, "网络连接错误",
								Toast.LENGTH_LONG).show();
					}
				});
		postRequest.PutParams("action", "add");
		postRequest.PutParams("userId", TripApplication.getInstance().getUser()
				.getUser_id()
				+ "");
		postRequest.PutParams("content", content);
		postRequest.PutParams("id", data.getShortCommentId() + "");
		TripApplication.getInstance().getRequestQueue().add(postRequest);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		manager.onDestroy();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.backcommunity:
			finish();
			break;
		case R.id.shareCommunity:
			manager.create();
			manager.postShare();
			break;
		case R.id.send:
			if (TripApplication.getInstance().getUser() == null
					|| "".equals(TripApplication.getInstance().getUser()
							.getUsername())) {
				Toast.makeText(this, "请先登录", Toast.LENGTH_LONG).show();
			} else {
				String contents = content.getText().toString();
				if (contents == null || "".equals(contents)) {
					Toast.makeText(this, "请先将内容填写完整", Toast.LENGTH_LONG).show();
				} else {
					sendData(contents);
				}
			}
			break;
		default:
			break;
		}
	}
}
