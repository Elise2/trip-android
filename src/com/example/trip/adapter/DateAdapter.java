package com.example.trip.adapter;

import java.util.List;

import com.example.trip.R;
import com.example.trip.activity.CalendarsActivity;
import com.example.trip.activity.CityListActivity;
import com.example.trip.entity.Schedule;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DateAdapter extends BaseAdapter {
	private List<Schedule> dates;
	private LayoutInflater inflater;
	private Context context;
	private onClickDate clickDate;

	public onClickDate getClickDate() {
		return clickDate;
	}

	public void setClickDate(onClickDate clickDate) {
		this.clickDate = clickDate;
	}

	public DateAdapter(List<Schedule> dates, Context context) {
		super();
		this.dates = dates;
		this.inflater = LayoutInflater.from(context);
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dates == null ? 0 : dates.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return dates == null ? null : dates.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(final int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (arg1 == null) {
			holder = new ViewHolder();
			arg1 = inflater.inflate(R.layout.item_date, null);
			holder.destination = (TextView) arg1.findViewById(R.id.destination);
			holder.goDate = (TextView) arg1.findViewById(R.id.goDate);
			holder.leaveDate = (TextView) arg1.findViewById(R.id.leaveDate);
			holder.desNum = (TextView) arg1.findViewById(R.id.desNum);
			arg1.setTag(holder);
		} else {
			holder = (ViewHolder) arg1.getTag();
		}
		final Schedule date = dates.get(arg0);
		holder.destination.setText(date.getCityName());
		holder.goDate.setText(date.getGoDate());
		holder.leaveDate.setText(date.getLeaveDate());
		holder.desNum.setText("目的地" + (arg0 + 1));
		RelativeLayout layout = (RelativeLayout) arg1
				.findViewById(R.id.destinationLayout);
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg1) {
				// TODO Auto-generated method stub
				if (clickDate != null) {
					clickDate.onCityName(arg0);
				}
				// Intent intent = new Intent(context, CityListActivity.class);
				// intent.putExtra("category", "date");
				// intent.putExtra("date", date);
				// intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				// context.startActivity(intent);
			}
		});
		holder.goDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg1) {
				// TODO Auto-generated method stub
				if (clickDate != null) {
					clickDate.onGoDate(arg0);
				}
				// Intent intent = new Intent(context, CalendarsActivity.class);
				// intent.putExtra("category", "goDate");
				// intent.putExtra("date", date);
				// intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				// context.startActivity(intent);
			}
		});
		holder.leaveDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg1) {
				// TODO Auto-generated method stub
				if (clickDate != null) {
					clickDate.oLeaveDate(arg0);
				}
				// Intent intent = new Intent(context, CalendarsActivity.class);
				// intent.putExtra("category", "leaveDate");
				// intent.putExtra("date", date);
				// intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				// context.startActivity(intent);
			}
		});
		return arg1;
	}

	public class ViewHolder {
		private TextView destination;
		private TextView goDate;
		private TextView leaveDate;
		private TextView desNum;
	}

	public interface onClickDate {
		public void onGoDate(int arg0);

		public void oLeaveDate(int arg0);

		public void onCityName(int arg0);

	}
}
