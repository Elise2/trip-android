package com.example.trip.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HomePagerViewAdapter extends PagerAdapter{
	private List<ImageView> mData;
	
	public HomePagerViewAdapter(List<ImageView> mdata) {
		super();
		// TODO Auto-generated constructor stub
		mData = mdata;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.mData.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(mData.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		container.addView(mData.get(position));
		return this.mData.get(position);
	}
	
	

}
