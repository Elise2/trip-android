package com.example.trip.adapter;

import java.util.List;

import com.example.trip.R;
import com.example.trip.adapter.ShareSpotAdapter.ShareSpotHolder;
import com.example.trip.entity.Partner;
import com.example.trip.entity.ShareSpotModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Ω·∞ÈAdapter
 * 
 * @author Administrator
 *
 */
public class PartnerAdapter extends BaseAdapter {
	private List<Partner> mData;
	private Context mContext;

	public PartnerAdapter(List<Partner> mData, Context mContext) {
		super();
		this.mData = mData;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ShareSpotHolder holder = null;
		if (convertView == null) {
			holder = new ShareSpotHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.takepartner_item, null);
			holder.name = (TextView) convertView.findViewById(R.id.partnerName);
			holder.headImg = (ImageView) convertView
					.findViewById(R.id.partnerItem);
			holder.start = (TextView) convertView.findViewById(R.id.detialLine);
			holder.end = (TextView) convertView.findViewById(R.id.destination);
			holder.goTime = (TextView) convertView
					.findViewById(R.id.detialTime);
			holder.totalTime = (TextView) convertView
					.findViewById(R.id.totalTime2);
			holder.request = (TextView) convertView.findViewById(R.id.request);
			convertView.setTag(holder);
		} else {
			holder = (ShareSpotHolder) convertView.getTag();
		}
		holder.name.setText(mData.get(position).getName());
		// holder.headImg
		holder.start.setText(mData.get(position).getStart());
		holder.end.setText(mData.get(position).getEnd());
		holder.goTime.setText(mData.get(position).getStartTime());
		holder.totalTime.setText(mData.get(position).getTotalTime() + "");
		// TODO Auto-generated method stub
		return convertView;
	}

	public class ShareSpotHolder {
		public TextView name;
		public ImageView headImg;
		public TextView start;
		public TextView end;
		public TextView goTime;
		public TextView totalTime;
		public TextView request;
	}

}
