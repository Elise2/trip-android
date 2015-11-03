package com.example.trip.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.example.trip.R;
import com.example.trip.adapter.NoteDetailsAdapter.noteDetailsHolder;
import com.example.trip.entity.Hotel;
import com.example.trip.entity.PlayNote;
import com.example.trip.util.ImageLoaderUtil;
import com.example.trip.util.UrlUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HotelDetailAdapter extends BaseAdapter {
	private List<Hotel> mdata;
	private Context mContext;

	public HotelDetailAdapter(List<Hotel> mdata, Context mContext) {
		super();
		this.mdata = mdata;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.mdata.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.mdata.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		hotelDetailsHolder holder = null;
		if (convertView == null) {
			holder = new hotelDetailsHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.hoteldetail_listview_item, null);
			holder.img = (ImageView) convertView
					.findViewById(R.id.hotelUserItem);
			holder.content = (TextView) convertView
					.findViewById(R.id.hotelContent);
			holder.userLevel = (TextView) convertView.findViewById(R.id.hotelUserLevel);
			holder.publishTime = (TextView) convertView.findViewById(R.id.hotelPublishTime);
			holder.userName = (TextView) convertView.findViewById(R.id.hotelUserName);
			convertView.setTag(holder);
		} else {
			holder = (hotelDetailsHolder) convertView.getTag();
		}
		String url = mdata.get(position).getUserimg();
		if (url != null) {
			url = UrlUtil.ROOT_URL + url;
			ImageLoaderUtil.display(url, holder.img);
		}
		holder.content.setText(mdata.get(position).getUsercotent());
		holder.userLevel.setText(mdata.get(position).getUser_level());
		holder.publishTime.setText(mdata.get(position).getPublishtime());
		holder.userName.setText(mdata.get(position).getUsername());
		return convertView;
	}

	public class hotelDetailsHolder {
		public ImageView img;
		public TextView userName;
		public TextView userLevel;
		public TextView publishTime;
		public TextView content;
	}


}
