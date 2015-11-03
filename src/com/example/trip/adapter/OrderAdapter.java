package com.example.trip.adapter;

import java.util.List;

import com.example.trip.R;
import com.example.trip.adapter.CityNavigationAdapter.oneViewHolder;
import com.example.trip.entity.Order;
import com.example.trip.entity.PlayMethod;
import com.example.trip.util.ImageLoaderUtil;
import com.example.trip.util.UrlUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class OrderAdapter extends BaseAdapter {
	private List<Order> datas;
	private LayoutInflater inflater;
	private int flag;
	public OrderAdapter(List<Order> datas, Context context,int flag) {
		super();
		this.datas = datas;
		this.inflater = LayoutInflater.from(context);
		this.flag = flag;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas == null ? 0 : datas.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return datas == null ? null : datas.get(arg0);
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
		if (arg1 == null) {
			one = new oneViewHolder();
			arg1 = (View) inflater.inflate(R.layout.myorderlist_item, null);
			one.roomNum = (TextView) arg1.findViewById(R.id.roomNum);
			one.roomPrice = (TextView) arg1.findViewById(R.id.totalPrice);
			one.img = (ImageView) arg1.findViewById(R.id.orderHotelImg);
			one.title = (TextView) arg1.findViewById(R.id.orderHotelName);

			arg1.setTag(one);
		} else {
			one = (oneViewHolder) arg1.getTag();
		}

		Order data = datas.get(arg0);
		one.title.setText(data.getHotelName());
		one.roomPrice.setText((data.getOrderPrice() * data.getOrderNum()) + "");
		one.roomNum.setText(data.getOrderNum() + "");
		String url = data.getHotelImg() + "";
		if (url != null) {
			url = UrlUtil.ROOT_URL + url;
			ImageLoaderUtil.display(url, one.img);
		}
		return arg1;
	}

	public class oneViewHolder {
		TextView title;
		TextView roomNum;
		TextView roomPrice;
		ImageView img;
	}

}
