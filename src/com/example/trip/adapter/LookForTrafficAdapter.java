package com.example.trip.adapter;

import java.util.List;

import com.example.trip.entity.Type;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class LookForTrafficAdapter extends FragmentPagerAdapter {

	private List<Fragment> fragments;

	public LookForTrafficAdapter(FragmentManager fm, List<Fragment> fragments) {
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
		// TODO Auto-generated method stub
		if (position == 0) {
			return "飞机";
		} else if (position == 1) {
			return "火车";
		}
		return null;
	}

}
