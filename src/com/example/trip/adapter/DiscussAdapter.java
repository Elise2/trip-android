package com.example.trip.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.example.trip.R;
import com.example.trip.adapter.SceneryAskContentAdapter.ViewHolder;
import com.example.trip.entity.Discuss;
import com.example.trip.util.ImageLoaderUtil;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class DiscussAdapter extends BaseAdapter {
	private List<Discuss> notes;
	private LayoutInflater inflater;

	public DiscussAdapter(List<Discuss> notes, Context context) {
		super();
		this.notes = notes;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return notes == null ? 0 : notes.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return notes == null ? null : notes.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (arg1 == null) {
			arg1 = inflater.inflate(R.layout.item_discuss, null);
			holder = new ViewHolder();
			holder.faceDiscuss = (ImageView) arg1
					.findViewById(R.id.faceDiscusser);
			holder.nameDiscuss = (TextView) arg1
					.findViewById(R.id.nameDiscusser);
			holder.dateDiscuss = (TextView) arg1
					.findViewById(R.id.dateDiscusser);
			holder.levelDiscuss = (TextView) arg1
					.findViewById(R.id.levelDiscusser);
			holder.contentDiscuss = (TextView) arg1
					.findViewById(R.id.contentDiscuss);
			arg1.setTag(holder);
		} else {
			holder = (ViewHolder) arg1.getTag();
		}
		Discuss note = notes.get(arg0);
		holder.contentDiscuss.setText(note.getContent());
		holder.dateDiscuss.setText(note.getTime());
		holder.levelDiscuss.setText(note.getLevel());
		holder.nameDiscuss.setText(note.getName());

		String url = note.getImg() + "";
		if (!url.equals("")) {
			url = UrlUtil.ROOT_URL + url;
		}
		ImageLoaderUtil.display(url, holder.faceDiscuss);
		return arg1;
	}

	public class ViewHolder {
		ImageView faceDiscuss;
		TextView nameDiscuss;
		TextView levelDiscuss;
		TextView contentDiscuss;
		TextView dateDiscuss;
	}

}
