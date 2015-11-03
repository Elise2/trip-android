package com.example.trip.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.example.trip.R;
import com.example.trip.util.Constant;

public class MySettingActivity extends Activity implements OnClickListener {
	private CheckBox lost;
	private SharedPreferences spPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_setting);
		lost = (CheckBox) findViewById(R.id.lost);
		findViewById(R.id.settingImg).setOnClickListener(this);
		spPreferences = getSharedPreferences(Constant.SP_NAME, MODE_PRIVATE);
		lost.setChecked(spPreferences.getBoolean("lost", true));
		lost.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				SharedPreferences.Editor editor = spPreferences.edit();
				editor.putBoolean("lost", arg1);
				editor.commit();
			}
		});
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
