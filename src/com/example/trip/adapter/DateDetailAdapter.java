package com.example.trip.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract.CommonDataKinds.Relation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.i;
import com.example.trip.R;
import com.example.trip.activity.DateDetailNoteActivity;
import com.example.trip.activity.DateDetailSceneryActivity;
import com.example.trip.entity.Date;
import com.umeng.socialize.net.v;

public class DateDetailAdapter extends BaseAdapter {
	private List<Date> datas;
	private Context context;

	public DateDetailAdapter(List<Date> datas, Context context) {
		super();
		this.datas = datas;
		this.context = context;
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
			arg1 = LayoutInflater.from(context).inflate(
					R.layout.item_datedetail, null);
			viewHolder.date = (TextView) arg1.findViewById(R.id.detaildate);
			viewHolder.time = (TextView) arg1.findViewById(R.id.detailtime);
			viewHolder.hotel = (TextView) arg1.findViewById(R.id.detailhotel);
			viewHolder.location = (TextView) arg1
					.findViewById(R.id.detaillocation);
			viewHolder.scenery = (TextView) arg1
					.findViewById(R.id.detailscenery);
			viewHolder.note = (TextView) arg1.findViewById(R.id.detailnote);
			viewHolder.notes = (RelativeLayout) arg1.findViewById(R.id.note);
			viewHolder.hotels = (RelativeLayout) arg1.findViewById(R.id.hotel);
			viewHolder.plans = (RelativeLayout) arg1.findViewById(R.id.plan);
			arg1.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) arg1.getTag();
		}
		final Date date = datas.get(arg0);
		viewHolder.date.setText("第" + (arg0 + 1) + "天");
		viewHolder.time.setText(date.getGoDate());
		viewHolder.hotel.setText(date.getHotel() + "");
		viewHolder.location.setText(date.getCityName());
		viewHolder.note.setText(date.getPlayote() + "");
		viewHolder.scenery.setText(date.getScenery() + "");
		viewHolder.plans.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context,
						DateDetailSceneryActivity.class);
				intent.putExtra("datedetail", date);
				context.startActivity(intent);

			}
		});
		viewHolder.hotels.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
		viewHolder.notes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context,
						DateDetailNoteActivity.class);
				intent.putExtra("datedetail", date);
				context.startActivity(intent);
			}
		});
		return arg1;
	}

	public class ViewHolder {
		TextView date;
		TextView time;
		TextView location;
		TextView scenery;
		TextView hotel;
		TextView note;
		RelativeLayout plans;
		RelativeLayout notes;
		RelativeLayout hotels;

	}
}
