package com.example.trip.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.example.trip.R;
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

public class NoteDetailsAdapter extends BaseAdapter {
	private List<PlayNote> mdata;
	private Context mContext;
	private ImageLoader loader;

	public NoteDetailsAdapter(List<PlayNote> mdata, Context mContext) {
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
		noteDetailsHolder holder = null;
		if (convertView == null) {
			holder = new noteDetailsHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.notedetals_list_item, null);
			holder.img = (ImageView) convertView
					.findViewById(R.id.notedetailsImg);
			holder.content = (TextView) convertView
					.findViewById(R.id.detailContent);
			convertView.setTag(holder);
		} else {
			holder = (noteDetailsHolder) convertView.getTag();
		}
		String url = mdata.get(position).getNote_img();
		if (url != null) {
			url = UrlUtil.ROOT_URL + url;
			ImageLoaderUtil.display(url, holder.img);
		}
		holder.content.setText(mdata.get(position).getNote_content());
		return convertView;
	}

	public class noteDetailsHolder {
		public ImageView img;
		public TextView content;
	}

}
