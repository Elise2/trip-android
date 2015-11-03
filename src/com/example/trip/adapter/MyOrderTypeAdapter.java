package com.example.trip.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
/**
 * 个人收藏
 * @author 
 *
 */
public class MyOrderTypeAdapter extends FragmentPagerAdapter {
	private List<Fragment> fragments;
	public MyOrderTypeAdapter(FragmentManager fm,List<Fragment> fragments) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.fragments = fragments;
	}
	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.fragments.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.fragments.size();
	}
	@Override
	public CharSequence getPageTitle(int position) {
		if (position==0) {
			return "酒店订单";
		}else if (position==1) {
			return "猎人订单";
		}
		return null;
	}
	

}
