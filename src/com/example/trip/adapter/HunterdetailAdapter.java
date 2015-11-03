package com.example.trip.adapter;

import java.util.List;

import com.example.trip.R;
import com.example.trip.adapter.HunterMainListAdapter.HotelHolder;
import com.example.trip.entity.Hunter;
import com.example.trip.util.ImageLoaderUtil;
import com.example.trip.util.UrlUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HunterdetailAdapter extends BaseAdapter {
	private List<Hunter> mdata;
	private Context mContext;

	public HunterdetailAdapter(List<Hunter> mdata, Context mContext) {
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

	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		HunterHolder holder = null;
		if (convertView == null) {
			holder = new HunterHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.hunterdetail_listview_item, null);
			holder.hunterdescript = (TextView) convertView
					.findViewById(R.id.detailContent);
			holder.hunterImg = (ImageView) convertView
					.findViewById(R.id.detailImg);
			convertView.setTag(holder);
		} else {
			holder = (HunterHolder) convertView.getTag();
		}
		Hunter data = mdata.get(position);
		if (mdata != null) {
			holder.hunterdescript.setText(data.getHunterdetail_content());
			String url = data.getHunterdetail_img() + "";
			if (!url.equals("")) {
				url = UrlUtil.ROOT_URL + url;
				ImageLoaderUtil.display(url, holder.hunterImg);
			}
		}

		return convertView;
	}

	public class HunterHolder {
		public TextView hunterdescript;
		public ImageView hunterImg;
	}

}
