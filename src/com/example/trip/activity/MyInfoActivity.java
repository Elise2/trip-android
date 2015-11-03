package com.example.trip.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.example.trip.R;
import com.example.trip.adapter.InfoTypeAdapter;
import com.example.trip.entity.Type;
import com.example.trip.fragment.MineInfoFragment;
import com.example.trip.manager.DataManager;

public class MyInfoActivity extends FragmentActivity{
	private PagerSlidingTabStrip tabs;
	private ViewPager myPager;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.information);
		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		myPager = (ViewPager) findViewById(R.id.myPager);
		initViewPager();
	}
	
	private void initViewPager(){
		List<Fragment> fragments = new ArrayList<Fragment>();
		List<Type> category =DataManager.iniTypes();
		for (int i = 0; i < category.size(); i++) {
			Fragment f = new MineInfoFragment();
			Bundle bundle = new Bundle();
			bundle.putInt("typeId", category.get(i).getTypeId());
			f.setArguments(bundle);
			fragments.add(f);
		}
		InfoTypeAdapter adapter = new InfoTypeAdapter(getSupportFragmentManager(), fragments, category);
		this.myPager.setAdapter(adapter);
		this.tabs.setViewPager(myPager);
	}

}
