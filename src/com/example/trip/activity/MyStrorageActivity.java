package com.example.trip.activity;

import java.util.ArrayList;
import java.util.List;

import com.astuetz.PagerSlidingTabStrip;
import com.example.trip.R;
import com.example.trip.adapter.MyStorageTypeAdapter;
import com.example.trip.entity.PlayNote;
import com.example.trip.fragment.CollectionArticleFragment;
import com.example.trip.fragment.CollectionCityFragment;
import com.example.trip.fragment.PlayNoteFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 我的收藏
 * 
 * @author z
 * 
 */
public class MyStrorageActivity extends FragmentActivity implements
		OnClickListener {
	private PagerSlidingTabStrip tabs;
	private ViewPager myPager;
	private PlayNote playnote;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_storage);
		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		myPager = (ViewPager) findViewById(R.id.myPager);
		//findViewById(R.id.title).setOnClickListener(this);
		findViewById(R.id.saveback).setOnClickListener(this);
		initViewPager();
	}

	private void initViewPager() {
		List<Fragment> fragments = new ArrayList<Fragment>();
		Fragment f = new CollectionCityFragment();
		fragments.add(f);
		Fragment f1 = new PlayNoteFragment();
		fragments.add(f1);
		Fragment f2 = new CollectionArticleFragment();
		fragments.add(f2);
		MyStorageTypeAdapter adapter = new MyStorageTypeAdapter(
				getSupportFragmentManager(), fragments);
		this.myPager.setAdapter(adapter);
		this.tabs.setViewPager(myPager);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.saveback:
			finish();
			break;

		default:
			break;
		}

	}

}
