package com.example.trip.activity;

import com.example.trip.R;
import com.example.trip.manager.ShareManager;
import com.example.trip.util.Constant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShortCityStrategyActivity extends Activity implements
		OnClickListener {
	private TextView cityName;
	private ShareManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_city_strategy);
		String name = getIntent().getStringExtra("name");
		cityName = (TextView) findViewById(R.id.cityName);
		cityName.setText(name + "城市攻略");
		findViewById(R.id.info).setOnClickListener(this);
		findViewById(R.id.cost).setOnClickListener(this);
		findViewById(R.id.tips).setOnClickListener(this);
		findViewById(R.id.in).setOnClickListener(this);
		findViewById(R.id.out).setOnClickListener(this);
		findViewById(R.id.festival).setOnClickListener(this);
		findViewById(R.id.hightlight).setOnClickListener(this);
		findViewById(R.id.sharess).setOnClickListener(this);
		manager = new ShareManager(this);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		manager.onDestroy();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.info:
			Intent intent = new Intent(this,
					ShortCityStrategyDetailActivity.class);
			intent.putExtra("situationss", Constant.INFO);
			startActivity(intent);
			break;
		case R.id.cost:
			intent = new Intent(this, ShortCityStrategyDetailActivity.class);
			intent.putExtra("situationss", Constant.COST);
			startActivity(intent);
			break;
		case R.id.tips:
			intent = new Intent(this, ShortCityStrategyDetailActivity.class);
			intent.putExtra("situationss", Constant.TIPS);
			startActivity(intent);
			break;
		case R.id.in:
			intent = new Intent(this, ShortCityStrategyDetailActivity.class);
			intent.putExtra("situationss", Constant.IN);
			startActivity(intent);
			break;
		case R.id.out:
			intent = new Intent(this, ShortCityStrategyDetailActivity.class);
			intent.putExtra("situationss", Constant.OUT);
			startActivity(intent);
			break;
		case R.id.festival:
			intent = new Intent(this, ShortCityStrategyDetailActivity.class);
			intent.putExtra("situationss", Constant.FESTIVAL);
			startActivity(intent);
			break;
		case R.id.hightlight:
			intent = new Intent(this, ShortCityStrategyDetailActivity.class);
			intent.putExtra("situationss", Constant.HIGHTLIGHT);
			startActivity(intent);
			break;
		case R.id.sharess:
			manager.create();
			manager.postShare();
			break;
		default:
			break;
		}
	}

}
