package com.example.trip.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trip.R;
import com.example.trip.entity.Plane;
import com.example.trip.entity.Station;

public class LookForStationAdapter extends BaseAdapter {
	private List<Station> datas;
	private LayoutInflater inflater;

	public LookForStationAdapter(List<Station> datas, Context context) {
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
			arg1 = inflater.inflate(R.layout.item_look_station, null);
			viewHolder.gotime = (TextView) arg1.findViewById(R.id.goTime);
			viewHolder.goLocation = (TextView) arg1
					.findViewById(R.id.goLocation);
			viewHolder.arriveLocation = (TextView) arg1
					.findViewById(R.id.arriveLocation);
			viewHolder.arriveTime = (TextView) arg1
					.findViewById(R.id.arriveTime);
			viewHolder.run_time = (TextView) arg1.findViewById(R.id.run_time);
			viewHolder.price = (TextView) arg1.findViewById(R.id.price);
			viewHolder.train_no = (TextView) arg1.findViewById(R.id.train_no);
			viewHolder.start_station_type = (ImageView) arg1
					.findViewById(R.id.start_station_type);
			viewHolder.end_station_type = (ImageView) arg1
					.findViewById(R.id.end_station_type);
			arg1.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) arg1.getTag();

		}
		Station data = datas.get(arg0);
		if ("过".equals(data.getStart_station_type())) {
			viewHolder.start_station_type.setImageResource(R.drawable.guos);
		} else if ("始".equals(data.getStart_station_type())) {
			viewHolder.start_station_type.setImageResource(R.drawable.starts);
		} else if ("终".equals(data.getStart_station_type())) {
			viewHolder.start_station_type.setImageResource(R.drawable.overs);
		}
		if ("过".equals(data.getEnd_station_type())) {
			viewHolder.end_station_type.setImageResource(R.drawable.guos);
		} else if ("始".equals(data.getEnd_station_type())) {
			viewHolder.end_station_type.setImageResource(R.drawable.starts);
		} else if ("终".equals(data.getEnd_station_type())) {
			viewHolder.end_station_type.setImageResource(R.drawable.overs);
		}
		viewHolder.arriveLocation.setText(data.getEnd_station());
		viewHolder.goLocation.setText(data.getStart_station());
		viewHolder.arriveTime.setText(data.getEnd_time());
		viewHolder.gotime.setText(data.getStart_time());
		viewHolder.price.setText(data.getPrice1() );
		viewHolder.run_time.setText(data.getRun_time());
		viewHolder.train_no.setText(data.getTrain_no());
		return arg1;
	}

	private class ViewHolder {
		TextView gotime;
		TextView arriveTime;
		TextView goLocation;
		TextView arriveLocation;
		TextView train_no;
		TextView price;
		TextView run_time;
		ImageView start_station_type;
		ImageView end_station_type;

	}

}
