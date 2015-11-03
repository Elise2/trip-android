package com.example.trip.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.trip.R;
import com.example.trip.entity.Ask;
import com.example.trip.entity.PlayNote;
import com.example.trip.util.ImageLoaderUtil;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;

public class SceneryAskAdapter extends BaseAdapter {
	private List<Ask> notes;
	private LayoutInflater inflater;
	private ImageLoader loader;

	public SceneryAskAdapter(List<Ask> notes, Context context) {
		super();
		this.notes = notes;
		this.inflater = LayoutInflater.from(context);
		loader = new ImageLoader(TripApplication.getInstance()
				.getRequestQueue(), TripApplication.getInstance()
				.getImageCache());
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
			arg1 = inflater.inflate(R.layout.item_travel_ask, null);
			holder = new ViewHolder();
			holder.see = (TextView) arg1.findViewById(R.id.see);
			holder.context = (TextView) arg1.findViewById(R.id.content);
			holder.img = (ImageView) arg1.findViewById(R.id.face);
			holder.title = (TextView) arg1.findViewById(R.id.title);
			holder.commit = (TextView) arg1.findViewById(R.id.commit);

			arg1.setTag(holder);
		} else {
			holder = (ViewHolder) arg1.getTag();
		}
		Ask note = notes.get(arg0);
		holder.context.setText(note.getAskContent());
		holder.see.setText(note.getAskSee() + "");
		holder.commit.setText(note.getAnswerNum() + "");
		holder.title.setText(note.getAskTitle());
		String url = note.getAskUserImg() + "";
		if (!url.equals("")) {
			url = UrlUtil.ROOT_URL + url;
		}

		ImageLoaderUtil.display(url, holder.img);
		return arg1;
	}

	public class ViewHolder {
		ImageView img;
		TextView title;
		TextView context;
		TextView see;
		TextView commit;

	}
}
