package com.example.trip.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

import com.astuetz.PagerSlidingTabStrip;
import com.example.trip.R;
import com.example.trip.adapter.InfoTypeAdapter;
import com.example.trip.adapter.LookForTrafficAdapter;
import com.example.trip.entity.Type;
import com.example.trip.fragment.LookForTrafficFragment;
import com.example.trip.fragment.MineInfoFragment;
import com.example.trip.manager.DataManager;

public class LookForTrafficActivity extends FragmentActivity implements
		OnClickListener {
	private PagerSlidingTabStrip tabs;
	private ViewPager myPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_looktrafficmain);
		findViewById(R.id.travelImg).setOnClickListener(this);
		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		myPager = (ViewPager) findViewById(R.id.myPager);
		initViewPager();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.travelImg:
			finish();
			break;
		default:
			break;
		}
	}

	private void initViewPager() {
		List<Fragment> fragments = new ArrayList<Fragment>();
		Fragment f = new LookForTrafficFragment();
		Bundle bundle = new Bundle();
		bundle.putString("trafficType", "飞机");
		f.setArguments(bundle);
		fragments.add(f);
		Fragment f1 = new LookForTrafficFragment();
		Bundle bundle1 = new Bundle();
		bundle1.putString("trafficType", "火车");
		f1.setArguments(bundle1);
		fragments.add(f1);
		LookForTrafficAdapter adapter = new LookForTrafficAdapter(
				getSupportFragmentManager(), fragments);
		this.myPager.setAdapter(adapter);
		this.tabs.setViewPager(myPager);
	}

}
