package com.example.trip.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.trip.R;

public class OldDateAdapter extends BaseAdapter {
	private List<String> datas;
	private LayoutInflater inflater;

	public OldDateAdapter(List<String> locationList, Context context) {
		super();
		this.datas = locationList;
		this.inflater = LayoutInflater.from(context);
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
		TextView location = null;
		if (arg1 == null) {
			arg1 = inflater.inflate(R.layout.item_olddate, null);
			location = (TextView) arg1.findViewById(R.id.locations);
			arg1.setTag(location);
		} else {
			location = (TextView) arg1.getTag();
		}
		location.setText(datas.get(arg0));
		return arg1;
	}

}
