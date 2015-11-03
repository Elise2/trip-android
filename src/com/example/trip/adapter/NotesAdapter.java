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

public class NotesAdapter extends BaseAdapter {
	private List<PlayNote> mdata;
	private Context mContext;

	public NotesAdapter(List<PlayNote> mdata, Context mContext) {
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
		mainNotesHolder holder = null;
		if (convertView == null) {
			holder = new mainNotesHolder();
			convertView = LayoutInflater.from(mContext).inflate( 
					R.layout.note_main_item, null);
			holder.backImg = (ImageView) convertView
					.findViewById(R.id.mainNotesImg);
			holder.notesTitle = (TextView) convertView
					.findViewById(R.id.notes_title);
			holder.userImg = (ImageView) convertView
					.findViewById(R.id.mainNotesUserImg);
			holder.notesTime = (TextView) convertView
					.findViewById(R.id.mainNotesTime);
			holder.mainNotesUserName = (TextView) convertView.findViewById(R.id.mainNotesUserName);	
			convertView.setTag(holder);
		} else {
			holder = (mainNotesHolder) convertView.getTag();
		}
		holder.mainNotesUserName.setText(mdata.get(position).getUsername());
		holder.notesTitle.setText(mdata.get(position).getNotes_title());
		holder.notesTime.setText(mdata.get(position).getNotes_publish_time());
		String url = "" + mdata.get(position).getTop_img();
		if (url != null) {
			url = UrlUtil.ROOT_URL + url;
			ImageLoaderUtil.display(url, holder.backImg);
		}
		String userUrl = mdata.get(position).getUserimg() + "";
		if (userUrl != null) {
			userUrl = UrlUtil.ROOT_URL + userUrl;
			ImageLoaderUtil.display(userUrl, holder.userImg);
		}
		return convertView;
	}

	public class mainNotesHolder {
		public TextView mainNotesUserName;
		public TextView notesTitle;
		public ImageView userImg;
		public ImageView backImg;
		public TextView notesTime;
	}

}
