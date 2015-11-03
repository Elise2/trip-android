package com.example.trip.activity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.trip.R;
import com.example.trip.entity.CityModel;
import com.example.trip.entity.Schedule;
import com.example.trip.util.Constant;
import com.example.trip.util.DatabaseOpenHelper;
import com.example.trip.util.MonthDateView;
import com.example.trip.util.MonthDateView.DateClick;
import com.j256.ormlite.dao.Dao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarsActivity extends FragmentActivity {
	private ImageView iv_left;
	private ImageView iv_right;
	private TextView tv_date;
	private TextView tv_week;
	private TextView tv_today;
	private MonthDateView monthDateView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		List<Integer> list = new ArrayList<Integer>();

		setContentView(R.layout.activity_date);
		iv_left = (ImageView) findViewById(R.id.iv_left);
		iv_right = (ImageView) findViewById(R.id.iv_right);
		monthDateView = (MonthDateView) findViewById(R.id.monthDateView);
		tv_date = (TextView) findViewById(R.id.date_text);
		tv_week = (TextView) findViewById(R.id.week_text);
		tv_today = (TextView) findViewById(R.id.tv_today);
		monthDateView.setTextView(tv_date, tv_week);
		monthDateView.setDaysHasThingList(list);
		monthDateView.setDateClick(new DateClick() {
			@Override
			public void onClickOnDate() {
				String s = tv_date.getText().toString();
				String string = getIntent().getStringExtra("category");
				if ("traffic".equals(string)) {
					s = s.replace("年", "-");
					s = s.replace("月", "-");
					s = s + monthDateView.getmSelDay();
					SharedPreferences sPreferences = getSharedPreferences(
							Constant.SP_NAME, MODE_PRIVATE);
					SharedPreferences.Editor editor = sPreferences.edit();
					editor.putString("trafficDate", s);
					editor.commit();
					finish();
				}
				s = s.replace("年", "/");
				s = s.replace("月", "/");
				s = s + monthDateView.getmSelDay();
				if ("godate".equals(string)) {
					Intent intent = new Intent();
					intent.putExtra("goDate", s);
					setResult(3, intent);
					finish();
					// SharedPreferences sPreferences = getSharedPreferences(
					// Constant.SP_NAME, MODE_PRIVATE);
					// SharedPreferences.Editor editor = sPreferences.edit();
					// editor.putString("goDate", s);
					// editor.commit();
					// finish();
				} else if ("goDate".equals(string)) {
					String ddd = getIntent().getStringExtra("ddd");
					if (ddd.compareTo(s) <= 0) {
						Toast.makeText(CalendarsActivity.this,
								"选择日期不得大于离开日期:" + ddd, Toast.LENGTH_LONG)
								.show();
					} else {
						Intent intent = new Intent();

						intent.putExtra("goDate", s);
						intent.putExtra("num", getIntent()
								.getIntExtra("num", 0));
						setResult(5, intent);
						finish();
					}
					// final Schedule date = (Schedule) getIntent()
					// .getSerializableExtra("date");
					// date.setGoDate(s);
					// final Dao<Schedule, Integer> dao = DatabaseOpenHelper
					// .getInstance(getApplicationContext()).getDateDao();
					// try {
					// dao.update(date);
					// } catch (SQLException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }

				} else if ("leaveDate".equals(string)) {
					String ddd = getIntent().getStringExtra("ddd");
					if (ddd.compareTo(s) <= 0) {
						Intent intent = new Intent();
						intent.putExtra("goDate", s);
						intent.putExtra("num", getIntent()
								.getIntExtra("num", 0));
						setResult(6, intent);
						finish();
					} else {
						Toast.makeText(CalendarsActivity.this,
								"选择日期不得小于出发日期:" + ddd, Toast.LENGTH_LONG)
								.show();
					}

					// final Schedule date = (Schedule) getIntent()
					// .getSerializableExtra("date");
					// date.setLeaveDate(s);
					// final Dao<Schedule, Integer> dao = DatabaseOpenHelper
					// .getInstance(getApplicationContext()).getDateDao();
					// try {
					// dao.update(date);
					// } catch (SQLException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }

				}
			}
		});
		setOnlistener();
	}

	private void setOnlistener() {
		iv_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				monthDateView.onLeftClick();
			}
		});

		iv_right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				monthDateView.onRightClick();
			}
		});

		tv_today.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				monthDateView.setTodayToView();
			}
		});
	}
}
