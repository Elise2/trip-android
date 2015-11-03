package com.example.trip.adapter;

import java.util.List;

import com.example.trip.R;
import com.example.trip.entity.PlayNote;
import com.example.trip.entity.SubjectRecommend;
import com.example.trip.util.ImageLoaderUtil;
import com.example.trip.util.UrlUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomepagerListview_ItemAdapter extends BaseAdapter {
	private List<PlayNote> mdata;
	private Context mContext;

	public HomepagerListview_ItemAdapter(List<PlayNote> mdata, Context mContext) {
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
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.mdata.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		SHolder holder = null;
		if (convertView == null) {
			holder = new SHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.homepager_list_item, null);
			holder.title = (TextView) convertView.findViewById(R.id.subTitle);
			holder.time = (TextView) convertView.findViewById(R.id.subTime);
			holder.imgs = (RoundedImageView) convertView
					.findViewById(R.id.subimg);
			holder.local = (TextView) convertView
					.findViewById(R.id.subLocation);
			holder.write = (TextView) convertView.findViewById(R.id.write);
			holder.face = (RoundedImageView) convertView.findViewById(R.id.face);
			convertView.setTag(holder);
		} else {
			holder = (SHolder) convertView.getTag();
		}
		holder.title.setText(this.mdata.get(position).getNotes_title());
		holder.time.setText(this.mdata.get(position).getNotes_publish_time());
		holder.local.setText(this.mdata.get(position).getCity_name());
		holder.write.setText(this.mdata.get(position).getUsername());
		String url = mdata.get(position).getTop_img() + "";
		if (url != null) {
			url = UrlUtil.ROOT_URL + url;
			ImageLoaderUtil.display(url, holder.imgs);
		}
		
		String userUrl = mdata.get(position).getUserimg() + "";
		if (userUrl != null) {
			userUrl = UrlUtil.ROOT_URL + userUrl;
			ImageLoaderUtil.display(userUrl, holder.face);
		}
		return convertView;
	}

	public class SHolder {
		public TextView title;
		public TextView time;
		public RoundedImageView imgs;
		public TextView local;
		public TextView write;
		public RoundedImageView face;
	}

}
