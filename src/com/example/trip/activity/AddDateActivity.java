package com.example.trip.activity;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.astuetz.pagerslidingtabstrip.R.color;
import com.baidu.location.f.c;
import com.example.trip.R;
import com.example.trip.adapter.DateAdapter;
import com.example.trip.adapter.DateAdapter.onClickDate;
import com.example.trip.entity.AddNote;
import com.example.trip.entity.Schedule;
import com.example.trip.util.Constant;
import com.example.trip.util.DatabaseOpenHelper;
import com.example.trip.util.StringPostRequest;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.dao.Dao;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts.Data;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AddDateActivity extends Activity implements OnClickListener,
		onClickDate {
	private ListView dateList;
	private List<Schedule> dates;
	private DateAdapter adapter;
	private static int count = 0;
	private String dateStr;
	private TextView nowLocation;
	private TextView headGoTime;
	private int cityId = 38;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_adddate_listview);
		dateList = (ListView) findViewById(R.id.dateList);
		dates = new ArrayList<Schedule>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		dateStr = formatter.format(curDate);
		Schedule date = new Schedule(38, "请选择", "sd", "sd");
		date.setGoDate(getTime(count));
		date.setLeaveDate(getTime(count));
		dates.add(date);
		adapter = new DateAdapter(dates, getApplicationContext());
		adapter.setClickDate(this);
		initView();
		initEvent();
		dateList.setAdapter(adapter);
	}

	private void initView() {
		View headView = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.layout_adddate_headview, null);
		headGoTime = (TextView) headView.findViewById(R.id.headGoTime);
		headGoTime.setText(dateStr);
		headGoTime.setOnClickListener(this);
		nowLocation = (TextView) headView.findViewById(R.id.nowLocation);
		dateList.addHeaderView(headView);
		View footView = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.layout_adddate_footview, null);
		footView.findViewById(R.id.startTravel).setOnClickListener(this);
		dateList.addFooterView(footView);
		RelativeLayout nowLocationLayout = (RelativeLayout) headView
				.findViewById(R.id.nowLocationLayout);
		RelativeLayout headGoTimeLayout = (RelativeLayout) headView
				.findViewById(R.id.headGoTimeLayout);
		RelativeLayout stillAdd = (RelativeLayout) footView
				.findViewById(R.id.stillAdd);
		nowLocationLayout.setOnClickListener(this);
		headGoTimeLayout.setOnClickListener(this);
		stillAdd.setOnClickListener(this);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		dateList.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(
						AddDateActivity.this);
				builder.setTitle("删除");
				builder.setMessage("目的地" + (arg2) + "将被删除");
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub

								Schedule date = (Schedule) adapter
										.getItem(arg2 - 1);
								dates.remove(date);
								adapter.notifyDataSetChanged();

							}
						});
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								arg0.dismiss();
							}
						});
				AlertDialog dialog = builder.create();
				dialog.show();
				return true;
			}
		});
	}

	public void initNewData() {
		StringPostRequest postRequest = new StringPostRequest(
				UrlUtil.TRIP_DATE_URL, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						List<com.example.trip.entity.Date> date = new ArrayList<com.example.trip.entity.Date>();
						Gson gson = new Gson();
						date = gson
								.fromJson(
										arg0,
										new TypeToken<ArrayList<com.example.trip.entity.Date>>() {
										}.getType());
						if (date.size() > 0) {
							Intent intent = new Intent(AddDateActivity.this,
									DateDetailActivity.class);
							intent.putExtra("dates", (Serializable) date);
							startActivity(intent);
						}

					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(AddDateActivity.this, "网络连接错误",
								Toast.LENGTH_LONG).show();
					}
				});
		postRequest.PutParams("action", "selectNew");
		TripApplication.getInstance().getRequestQueue().add(postRequest);

	}

	private void AddDate() {

		// TODO Auto-generated method stub
		StringPostRequest postRequest = new StringPostRequest(
				UrlUtil.TRIP_DATE_URL, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						initNewData();
						finish();
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(AddDateActivity.this, "网络连接错误",
								Toast.LENGTH_LONG).show();
					}
				});

		postRequest.PutParams("action", "addDate");
		postRequest.PutParams("go_date", headGoTime.getText().toString());
		postRequest.PutParams("user_id", TripApplication.getInstance()
				.getUser().getUser_id()
				+ "");
		postRequest.PutParams("arrive_date", headGoTime.getText().toString());
		postRequest.PutParams("city_id", cityId + "");
		postRequest.PutParams("city_name", nowLocation.getText().toString());
		postRequest.PutParams("details", object2json(dates));
		TripApplication.getInstance().getRequestQueue().add(postRequest);
	}

	public static String object2json(List<Schedule> obj) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		for (int i = 0; i < obj.size(); i++) {
			json.append("{\"cityId\":" + obj.get(i).getCityId() + ",");
			json.append("\"cityName\":" + "\"" + obj.get(i).getCityName()
					+ "\",");
			json.append("\"goDate\":" + "\"" + obj.get(i).getGoDate() + "\",");
			json.append("\"leaveDate\":" + "\"" + obj.get(i).getLeaveDate()
					+ "\"}");
			json.append(",");
		}
		json.deleteCharAt(json.length() - 1);
		json.append("]");
		System.out.print(json.toString());
		return json.toString();
	}

	private String getTime(int count) {
		String s = dateStr.substring(dateStr.lastIndexOf("/") + 1);
		s = String.valueOf(Integer.parseInt(s) + count);
		s = dateStr.substring(0, dateStr.lastIndexOf("/") + 1) + s;
		return s;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (arg0.getId()) {
		case R.id.nowLocationLayout:
			intent = new Intent(this, CityListActivity.class);
			intent.putExtra("category", "godate");
			startActivityForResult(intent, 1);
			break;
		case R.id.headGoTimeLayout:
			intent = new Intent(this, CalendarsActivity.class);
			intent.putExtra("category", "godate");
			startActivityForResult(intent, 2);
			break;
		case R.id.startTravel:
			AddDate();
			break;
		case R.id.stillAdd:
			Schedule date = new Schedule(count, "ds", "sd", "sd");
			date.setGoDate(dates.get(dates.size()-1).getLeaveDate());
			date.setLeaveDate(dates.get(dates.size()-1).getLeaveDate());
			count++;
			intent = new Intent(this, CityListActivity.class);
			intent.putExtra("category", "date");
			intent.putExtra("date", date);
			startActivityForResult(intent, 3);

			break;
		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		try {
			if (requestCode == 1 && resultCode == 2) {
				nowLocation.setText(data.getStringExtra("goCityName"));
				cityId = data.getIntExtra("goCityId", 38);
			} else if (requestCode == 2 && resultCode == 3) {
				headGoTime.setText(data.getStringExtra("goDate"));
			} else if (requestCode == 3 && resultCode == 4) {
				Schedule date = (Schedule) data.getSerializableExtra("date");
				dates.add(date);
				adapter.notifyDataSetChanged();
			} else if (requestCode == 5 && resultCode == 5) {
				int num = data.getIntExtra("num", 0);
				String string = data.getStringExtra("goDate");
				dates.get(num).setGoDate(string);
				adapter.notifyDataSetChanged();

			} else if (requestCode == 6 && resultCode == 6) {
				int num = data.getIntExtra("num", 0);
				String string = data.getStringExtra("goDate");
				dates.get(num).setLeaveDate(string);
				adapter.notifyDataSetChanged();

			} else if (requestCode == 7 && resultCode == 7) {
				int num = data.getIntExtra("num", 0);
				String name = data.getStringExtra("goCityName");
				int cityId = data.getIntExtra("goCityId", 38);
				dates.get(num).setCityName(name);
				dates.get(num).setCityId(cityId);
				adapter.notifyDataSetChanged();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onGoDate(int arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, CalendarsActivity.class);
		intent.putExtra("ddd", dates.get(arg0).getLeaveDate());
		intent.putExtra("category", "goDate");
		intent.putExtra("num", arg0);
		startActivityForResult(intent, 5);

	}

	@Override
	public void oLeaveDate(int arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, CalendarsActivity.class);
		intent.putExtra("ddd", dates.get(arg0).getGoDate());
		intent.putExtra("category", "leaveDate");
		intent.putExtra("num", arg0);
		startActivityForResult(intent, 6);
	}

	@Override
	public void onCityName(int arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, CityListActivity.class);
		intent.putExtra("category", "datess");
		intent.putExtra("num", arg0);
		startActivityForResult(intent, 7);
	}
}
