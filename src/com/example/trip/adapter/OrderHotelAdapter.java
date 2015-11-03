package com.example.trip.adapter;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.example.trip.R;
import com.example.trip.entity.Scenery;
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
public class OrderHotelAdapter extends BaseAdapter {
	private List<Scenery> mdata;
	private Context mContext;
	private ImageLoader loader;

	public OrderHotelAdapter(List<Scenery> mdata, Context mContext) {
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
		SceneryHolder holder = null;
		if (convertView == null) {
			holder = new SceneryHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.strategy_hotel_listview_item, null);
			holder.hotelTitle = (TextView) convertView
					.findViewById(R.id.hotelTitle);
			holder.hotelImg = (ImageView) convertView
					.findViewById(R.id.hotelImg);
			holder.hoteltalkNum = (TextView) convertView
					.findViewById(R.id.hotelTalk);
			holder.hotelRecommendTitle = (TextView) convertView
					.findViewById(R.id.hotelRecommendTitle);
			holder.hotelRecommendContent = (TextView) convertView
					.findViewById(R.id.hotelRecommendContent);
			holder.hotelAddress = (TextView) convertView
					.findViewById(R.id.hotelAddress);
			convertView.setTag(holder);
			holder.orderPrice = (TextView) convertView
					.findViewById(R.id.hotelPrice);
		} else {
			holder = (SceneryHolder) convertView.getTag();
		}
		holder.hotelTitle.setText(mdata.get(position).getTitle());
		loader = new ImageLoader(TripApplication.getInstance()
				.getRequestQueue(), TripApplication.getInstance()
				.getImageCache());
		String url = mdata.get(position).getImg();
		if (!url.equals("")) {
			url = UrlUtil.ROOT_URL + url;
		}
		ImageLoaderUtil.display(url, holder.hotelImg);
		// holder.sceneryImg.setImageResource(mdata.get(position).getImg());
		holder.hoteltalkNum.setText(mdata.get(position).getTalkNum() + "");
		holder.hotelRecommendTitle.setText(mdata.get(position)
				.getRecommendTitle());
		holder.hotelRecommendContent.setText(mdata.get(position)
				.getRecommendContent());
		holder.hotelAddress.setText(mdata.get(position).getSceneryType());
		holder.orderPrice.setText(mdata.get(position).getHotelPrice());
		return convertView;
	}

	public class SceneryHolder {
		public TextView hotelTitle;
		public ImageView hotelImg;
		public TextView hoteltalkNum;
		public TextView hotelRecommendTitle;
		public TextView hotelRecommendContent;
		public TextView orderPrice;
		public TextView hotelAddress;
	}

}
