package com.example.trip.activity;

import java.util.ArrayList;
import java.util.List;

import com.astuetz.PagerSlidingTabStrip;
import com.example.trip.R;
import com.example.trip.adapter.MyOrderTypeAdapter;
import com.example.trip.adapter.MyStorageTypeAdapter;
import com.example.trip.entity.Order;
import com.example.trip.entity.PlayNote;
import com.example.trip.fragment.CollectionArticleFragment;
import com.example.trip.fragment.CollectionCityFragment;
import com.example.trip.fragment.OrderFragment;
import com.example.trip.fragment.PlayNoteFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 我的订单
 * 
 * @author z
 * 
 */
public class MyOrderActivity extends FragmentActivity implements
		OnClickListener {
	private PagerSlidingTabStrip tabs;
	private ViewPager myPager;
	private Order order;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.my_order);
		tabs = (PagerSlidingTabStrip) findViewById(R.id.myOrderTabs);
		myPager = (ViewPager) findViewById(R.id.myOrderPager);
		findViewById(R.id.backOrderImg).setOnClickListener(this);
		initViewPager();
	}

	private void initViewPager() {
		List<Fragment> fragments = new ArrayList<Fragment>();
		Fragment f = new OrderFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("isTop", 1);
		f.setArguments(bundle);
		fragments.add(f);
		f = new OrderFragment();
		bundle = new Bundle();
		bundle.putInt("isTop", 2);
		f.setArguments(bundle);
		fragments.add(f);
//		Fragment f2 = new CollectionArticleFragment();
//		fragments.add(f2);
		MyOrderTypeAdapter adapter = new MyOrderTypeAdapter(
				getSupportFragmentManager(), fragments);
		this.myPager.setAdapter(adapter);
		this.tabs.setViewPager(myPager);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.backOrderImg:
			finish();
			break;

		default:
			break;
		}

	}

}
