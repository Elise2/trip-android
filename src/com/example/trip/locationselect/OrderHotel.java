package com.example.trip.locationselect;

import com.example.trip.R;
import com.example.trip.activity.OrderHotelMainActivity;
import com.example.viewdemo.main.OrderHotelCalendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OrderHotel extends Activity implements OnClickListener{
	private TextView city_name;
	private LinearLayout timeManager;
	private TextView inTime;
	private TextView outTime;
	private EditText mainWord;
	private SharedPreferences sp;
	private String inday,outday;
	private Button lookHotel;
	private TextView daysNum;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_hotel);
		city_name = (TextView) findViewById(R.id.arrow);
		//提交酒店查询按钮
		lookHotel = (Button) findViewById(R.id.lookHotel);
		lookHotel.setOnClickListener(this);		
		mainWord = (EditText) findViewById(R.id.mainWord);//关键字
		daysNum = (TextView) findViewById(R.id.daysNum);//总共的天数
		inTime = (TextView) findViewById(R.id.inDetailTime);
		outTime = (TextView) findViewById(R.id.outDetailTime);
		timeManager = (LinearLayout) findViewById(R.id.timeManager);
		timeManager.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(OrderHotel.this, OrderHotelCalendar.class);
				startActivity(intent);
			}
		});
//		if (inTime!=null&outTime!=null) {
//			daysNum.setText((Integer.parseInt(outTime.getText().toString())-Integer.parseInt(inTime.getText().toString()))+"");
//		}
		sp=getSharedPreferences("date",Context.MODE_PRIVATE);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		inday=sp.getString("dateIn", "");
		outday=sp.getString("dateOut", "");
		if(!"".equals(inday)){
			inTime.setText(inday+"");
		}
		if(!"".equals(outday)){
			outTime.setText(outday+"");
		}
	}
	//点击我的位置所触触发的事件
	public void goSelcet(View v){
		startActivityForResult(new Intent(OrderHotel.this,ActivitySelectCity.class), 99);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		try{
			switch (resultCode) {
			case 99:
				//if (data.getStringArrayExtra("lngCityName")!=null) {
					city_name.setText(data.getStringExtra("lngCityName"));
				//}else {
				//	city_name.setText("烟台");
				//}
				break;
	
			default:
				break;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lookHotel:
			Intent intent = new Intent(this,OrderHotelMainActivity.class);
			intent.putExtra("city_name", city_name.getText().toString());
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
