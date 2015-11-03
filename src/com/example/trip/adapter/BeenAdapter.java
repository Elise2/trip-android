package com.example.trip.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.example.trip.R;
import com.example.trip.adapter.CityNavigationAdapter.oneViewHolder;
import com.example.trip.entity.BeenCityModel;
import com.example.trip.util.ImageLoaderUtil;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BeenAdapter extends BaseAdapter {
	private List<BeenCityModel> citys;
	private Context context;

	public BeenAdapter(List<BeenCityModel> citys, Context context) {
		super();
		this.citys = citys;
		this.context = context;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return citys == null ? 0 : citys.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return citys == null ? null : citys.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		oneViewHolder one = null;
		LayoutInflater inflater = LayoutInflater.from(context);
		if (arg1 == null) {
			one = new oneViewHolder();
			arg1 = (View) inflater.inflate(R.layout.item_been, null);
			one.cityName = (TextView) arg1.findViewById(R.id.beencityName);
			one.cityImg = (ImageView) arg1.findViewById(R.id.beencityImg);
			one.cityTime = (TextView) arg1.findViewById(R.id.beencityTime);
			arg1.setTag(one);

		} else {
			one = (oneViewHolder) arg1.getTag();
		}

		String url = citys.get(arg0).getCityImg() + "";
		if (!url.equals("")) {
			url = UrlUtil.ROOT_URL + url;
		}
		one.cityName.setText(citys.get(arg0).getCityName());
		one.cityTime.setText(citys.get(arg0).getTime());
		ImageLoaderUtil.display(url, one.cityImg);
		return arg1;
	}

	public class oneViewHolder {
		TextView cityName;
		TextView cityTime;
		ImageView cityImg;
	}

}
