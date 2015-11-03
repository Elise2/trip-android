package com.example.trip.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.example.trip.R;
import com.example.trip.activity.CityDetailsActivity;
import com.example.trip.activity.HomePagerShareSpotActivity;
import com.example.trip.entity.CityModel;
import com.example.trip.entity.ShareSpotModel;
import com.example.trip.entity.User;
import com.example.trip.util.ImageLoaderUtil;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShareSpotAdapter extends BaseAdapter {
	private List<ShareSpotModel> mData;
	private List<CityModel> cities;
	private List<User> users;
	private ImageLoader loader;
	private Context mContext;

	public ShareSpotAdapter(List<ShareSpotModel> mData, List<CityModel> cities,
			List<User> users, Context mContext) {
		super();
		this.mData = mData;
		this.mContext = mContext;
		this.cities = cities;
		this.users = users;
		loader = new ImageLoader(TripApplication.getInstance()
				.getRequestQueue(), TripApplication.getInstance()
				.getImageCache());
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		ShareSpotHolder holder = null;
		if (convertView == null) {
			holder = new ShareSpotHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.homepager_traveltype_list_item, null);
			holder.spotLocation = (TextView) convertView
					.findViewById(R.id.travelLocal);
			holder.spotImg = (ImageView) convertView
					.findViewById(R.id.topPicture);
			holder.photoByName = (TextView) convertView
					.findViewById(R.id.photoByName);
			holder.shareReson = (TextView) convertView
					.findViewById(R.id.detailReason);
			convertView.setTag(holder);
		} else {
			holder = (ShareSpotHolder) convertView.getTag();
		}
		holder.spotLocation.setText(mData.get(position).getSpotLocation());
		// holder.spotImg.setImageURI(uri);
		holder.photoByName.setText(mData.get(position).getPhotoByName());
		holder.shareReson.setText(mData.get(position).getShareReason());
		String url = mData.get(position).getTopImg() + "";
		if (!url.equals("")) {
			url = UrlUtil.ROOT_URL + url;
		}
		ImageLoaderUtil.display(url, holder.spotImg);
		ImageView cityWebLink = (ImageView) convertView
				.findViewById(R.id.cityWebLink);
		cityWebLink.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContext, CityDetailsActivity.class);
				intent.putExtra("city", cities.get(position));
				((HomePagerShareSpotActivity) mContext).startActivity(intent);
			}
		});
		// TODO Auto-generated method stub
		return convertView;
	}

	public class ShareSpotHolder {
		public TextView spotLocation;
		public ImageView spotImg;
		public TextView photoByName;
		public TextView shareReson;
	}

}
