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
public class StrategySceneryAdapter extends BaseAdapter {
	private List<Scenery> mdata;
	private Context mContext;
	private ImageLoader loader;

	public StrategySceneryAdapter(List<Scenery> mdata, Context mContext) {
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
					R.layout.strategy_scenery_listview_item, null);
			holder.sceneryTitle = (TextView) convertView
					.findViewById(R.id.sceneryTitle);
			holder.sceneryImg = (ImageView) convertView
					.findViewById(R.id.sceneryImg);
			holder.talkNum = (TextView) convertView
					.findViewById(R.id.smallTalk);
			holder.recommendTitle = (TextView) convertView
					.findViewById(R.id.recommendTitle);
			holder.recommendContent = (TextView) convertView
					.findViewById(R.id.recommendContent);
			holder.sceneryType = (TextView) convertView
					.findViewById(R.id.sceneryType);
			convertView.setTag(holder);
		} else {
			holder = (SceneryHolder) convertView.getTag();
		}
		holder.sceneryTitle.setText(mdata.get(position).getTitle());
		loader = new ImageLoader(TripApplication.getInstance()
				.getRequestQueue(), TripApplication.getInstance()
				.getImageCache());
		String url = mdata.get(position).getImg();
		if (!url.equals("")) {
			url = UrlUtil.ROOT_URL + url;
		}
		ImageLoaderUtil.display(url, holder.sceneryImg);
		// holder.sceneryImg.setImageResource(mdata.get(position).getImg());
		holder.talkNum.setText(mdata.get(position).getTalkNum()+"");
		holder.recommendTitle.setText(mdata.get(position).getRecommendTitle());
		holder.recommendContent.setText(mdata.get(position)
				.getRecommendContent());
		holder.sceneryType.setText(mdata.get(position).getSceneryType());
		return convertView;
	}

	public class SceneryHolder {
		public TextView sceneryTitle;
		public ImageView sceneryImg;
		public TextView talkNum;
		public TextView recommendTitle;
		public TextView recommendContent;
		public TextView sceneryType;
	}

}
