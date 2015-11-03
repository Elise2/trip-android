package com.example.trip.adapter;
import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.example.trip.R;
import com.example.trip.entity.Hotel;
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

/**
 * 找攻略中景点点击进去的节目
 * 
 * @author lewei
 *
 */
public class HotelMainAdapter extends BaseAdapter {
	private List<Hotel> mdata;
	private Context mContext;

	public HotelMainAdapter(List<Hotel> mdata, Context mContext) {
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
		HotelHolder holder = null;
		if (convertView == null) {
			holder = new HotelHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.hotelmain_listview_item, null);
			holder.hotelTitle = (TextView) convertView
					.findViewById(R.id.hotelTitle);
			holder.hotelImg = (ImageView) convertView
					.findViewById(R.id.hotelImg);
			holder.talkNum = (TextView) convertView
					.findViewById(R.id.hotelTalk);
			holder.recommendTitle = (TextView) convertView
					.findViewById(R.id.useName);
			holder.recommendContent = (TextView) convertView
					.findViewById(R.id.hotelRecommendContent);
			holder.hotelType = (TextView) convertView
					.findViewById(R.id.hotelType);
			holder.hotelPrice = (TextView) convertView.findViewById(R.id.hotelPrice);
			convertView.setTag(holder);
		} else {
			holder = (HotelHolder) convertView.getTag();
		}
		if(mdata.get(position)!=null){
		holder.hotelTitle.setText(mdata.get(position).getOrder_name());
		String url = mdata.get(position).getOrder_img()+"";
		if (!url.equals("")) {
			url = UrlUtil.ROOT_URL + url;
			ImageLoaderUtil.display(url, holder.hotelImg);
		}
		
		// holder.sceneryImg.setImageResource(mdata.get(position).getImg());
		holder.talkNum.setText(mdata.get(position).getOrder_recommentNum()+"");
		holder.recommendTitle.setText(mdata.get(position).getUsername());
		holder.recommendContent.setText(mdata.get(position)
				.getOrder_descript());
		holder.hotelType.setText(mdata.get(position).getOrder_type());
		holder.hotelPrice.setText(mdata.get(position).getOrder_price());
		}
		return convertView;
	}

	public class HotelHolder {
		public TextView hotelTitle;
		public ImageView hotelImg;
		public TextView talkNum;
		public TextView recommendTitle;
		public TextView recommendContent;
		public TextView hotelType;
		public TextView hotelPrice;
	}

}
