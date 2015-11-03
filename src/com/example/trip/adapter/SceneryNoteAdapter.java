package com.example.trip.adapter;

import java.util.List;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.trip.R;
import com.example.trip.adapter.SceneryAskAdapter.ViewHolder;
import com.example.trip.entity.PlayNote;
import com.example.trip.util.ImageLoaderUtil;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;

public class SceneryNoteAdapter extends BaseAdapter {
	private List<PlayNote> notes;
	private LayoutInflater inflater;
	private int flag;

	public SceneryNoteAdapter(List<PlayNote> notes, Context context, int flag) {
		super();
		this.notes = notes;
		this.inflater = LayoutInflater.from(context);
		this.flag = flag;
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
			holder = new ViewHolder();
			arg1 = inflater.inflate(R.layout.item_paly_note, null);
			holder.see = (TextView) arg1.findViewById(R.id.see);
			holder.context = (TextView) arg1.findViewById(R.id.content);
			holder.img = (ImageView) arg1.findViewById(R.id.img);
			holder.face = (ImageView) arg1.findViewById(R.id.face);
			holder.noteLocation = (TextView) arg1
					.findViewById(R.id.noteLocation);
			arg1.setTag(holder);
		} else {
			holder = (ViewHolder) arg1.getTag();
		}
		PlayNote note = notes.get(arg0);
		holder.context.setText(note.getNotes_title());
		holder.see.setText(note.getSee() + "" + "浏览");
		String scenenryUrl = note.getTop_img() + "";
		String userUrl = note.getUserimg() + "";
		if (!scenenryUrl.equals("")) {
			scenenryUrl = UrlUtil.ROOT_URL + scenenryUrl;
			ImageLoaderUtil.display(scenenryUrl, holder.img);
		}
		if (!userUrl.equals("")) {
			userUrl = UrlUtil.ROOT_URL + userUrl;
			ImageLoaderUtil.display(userUrl, holder.face);
		}
		if (flag == 1) {
			holder.noteLocation.setVisibility(View.GONE);
		} else if (flag == 2) {
			holder.noteLocation.setText(note.getCity_name());
		}

		return arg1;
	}

	public class ViewHolder {
		ImageView img;
		ImageView face;
		TextView context;
		TextView see;
		TextView noteLocation;

	}
}
