package com.example.trip.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.example.trip.R;
import com.example.trip.entity.CityModel;
import com.example.trip.util.ImageLoaderUtil;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 导航栏的adapter
 * 
 * @author Administrator
 * 
 */
public class CityNavigationAdapter extends BaseAdapter {
	private List<CityModel> citys;
	private ImageLoader loader;
	private Context context;
	private int flag;

	public CityNavigationAdapter(List<CityModel> citys, Context context,
			int flag) {
		super();
		this.citys = citys;
		this.context = context;
		loader = new ImageLoader(TripApplication.getInstance()
				.getRequestQueue(), TripApplication.getInstance()
				.getImageCache());
		this.flag = flag;
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
			if (flag == 1) {
				arg1 = (View) inflater.inflate(
						R.layout.trip_city_navigation_item, null);
				one.cityName = (TextView) arg1.findViewById(R.id.cityName);
				one.cityNum = (TextView) arg1.findViewById(R.id.cityNum);
				one.cityImg = (ImageView) arg1.findViewById(R.id.cityImg);
				arg1.setTag(one);
			} else if (flag == 2) {
				arg1 = (View) inflater.inflate(R.layout.item_collection_city,
						null);
				one.cityName = (TextView) arg1.findViewById(R.id.title);
				one.cityNum = (TextView) arg1.findViewById(R.id.num);
				one.cityImg = (ImageView) arg1.findViewById(R.id.img);
				arg1.setTag(one);
			}
		} else {
			one = (oneViewHolder) arg1.getTag();
		}

		String url = citys.get(arg0).getCityImg() + "";
		if (!url.equals("")) {
			url = UrlUtil.ROOT_URL + url;
		}
		one.cityName.setText(citys.get(arg0).getCityName());
		one.cityNum.setText("有" + citys.get(arg0).getCityTraverNum() + "人去过~");
		ImageLoaderUtil.display(url, one.cityImg);
		return arg1;
	}

	public class oneViewHolder {
		TextView cityName;
		TextView cityNum;
		ImageView cityImg;
	}

}
