package com.example.trip.locationselect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.trip.R;

public class MainActivity extends Activity {
	private TextView city_name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_hotel);
		city_name = (TextView) findViewById(R.id.city_name);
	}
	
	public void goSelcet(View v){
		startActivityForResult(new Intent(MainActivity.this,ActivitySelectCity.class), 99);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		try{
			switch (resultCode) {
			case 99:
				city_name.setText(data.getStringExtra("lngCityName"));
				break;
			default:
				break;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
