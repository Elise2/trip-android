package com.example.trip.adapter;

import java.util.List;

import com.example.trip.entity.Type;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class InfoTypeAdapter extends FragmentPagerAdapter {

	private List<Fragment> fragments;
	private List<Type> types;
	public InfoTypeAdapter(FragmentManager fm,List<Fragment> fragments,List<Type> types) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.fragments = fragments;
		this.types = types;
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
		return types.get(position).getTypeName();
	}
	

}
