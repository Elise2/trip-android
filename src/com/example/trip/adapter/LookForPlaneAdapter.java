package com.example.trip.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.trip.R;
import com.example.trip.entity.Plane;

public class LookForPlaneAdapter extends BaseAdapter {
	private List<Plane> datas;
	private LayoutInflater inflater;

	public LookForPlaneAdapter(List<Plane> datas, Context context) {
		super();
		this.datas = datas;
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
		ViewHolder viewHolder = null;

		if (arg1 == null) {
			viewHolder = new ViewHolder();
			arg1 = inflater.inflate(R.layout.item_look_plane, null);
			viewHolder.airPlane = (TextView) arg1.findViewById(R.id.airPlanes);
			viewHolder.gotime = (TextView) arg1.findViewById(R.id.goTime);
			viewHolder.goLocation = (TextView) arg1
					.findViewById(R.id.goLocation);
			viewHolder.arriveLocation = (TextView) arg1
					.findViewById(R.id.arriveLocations);
			viewHolder.arriveTime = (TextView) arg1
					.findViewById(R.id.arriveTimes);
			arg1.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) arg1.getTag();
		}
		Plane data = datas.get(arg0);
		viewHolder.airPlane.setText(data.getAirline() + data.getFlightNum());
		viewHolder.gotime.setText(data.getDepTime());
		viewHolder.arriveTime.setText(data.getArrTime());
		if (data.getDepTerminal() == null) {
			viewHolder.goLocation.setText(data.getDepCity());
		} else {
			viewHolder.goLocation.setText(data.getDepCity()
					+ data.getDepTerminal());
		}
		if (data.getArrTerminal() == null) {
			viewHolder.arriveLocation.setText(data.getArrCity());
		} else {
			viewHolder.arriveLocation.setText(data.getArrCity()
					+ data.getArrTerminal());
		}

		return arg1;
	}

	private class ViewHolder {
		TextView gotime;
		TextView arriveTime;
		TextView goLocation;
		TextView arriveLocation;
		TextView airPlane;

	}

}
