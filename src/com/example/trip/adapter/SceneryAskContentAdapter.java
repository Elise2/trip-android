package com.example.trip.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.example.trip.R;
import com.example.trip.adapter.SceneryAskAdapter.ViewHolder;
import com.example.trip.entity.Answer;
import com.example.trip.entity.Ask;
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
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class SceneryAskContentAdapter extends BaseAdapter {
	private List<Answer> notes;
	private LayoutInflater inflater;
	private ImageLoader loader;

	public SceneryAskContentAdapter(List<Answer> notes, Context context) {
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
			arg1 = inflater.inflate(R.layout.item_travel_ask_content, null);
			holder = new ViewHolder();
			holder.bestAnswer = (ImageView) arg1.findViewById(R.id.bestAnswer);
			holder.contentAnswer = (TextView) arg1
					.findViewById(R.id.contentAnswer);
			holder.dateAnswer = (TextView) arg1.findViewById(R.id.dateAnswer);
			holder.faceAnswer = (ImageView) arg1.findViewById(R.id.faceAnswer);
			holder.goodAnswer = (CheckBox) arg1.findViewById(R.id.goodAnswer);
			holder.levelAnswer = (TextView) arg1.findViewById(R.id.levelAnswer);
			holder.nameAnswer = (TextView) arg1.findViewById(R.id.nameAnswer);
			arg1.setTag(holder);
		} else {
			holder = (ViewHolder) arg1.getTag();
		}
		Answer note = notes.get(arg0);
		holder.contentAnswer.setText(note.getContent());
		holder.dateAnswer.setText(note.getTime());
		holder.goodAnswer.setText(note.getGood() + "");
		final CheckBox checkBox = holder.goodAnswer;
		holder.goodAnswer
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton arg0,
							boolean arg1) {
						// TODO Auto-generated method stub
						if (arg1) {
							checkBox.setText((Integer.parseInt((checkBox
									.getText().toString())) + 1) + "");
						} else {
							checkBox.setText((Integer.parseInt((checkBox
									.getText().toString())) - 1) + "");

						}

					}
				});
		holder.levelAnswer.setText(note.getLevel());
		holder.nameAnswer.setText(note.getName());
		if (note.getIsBest() == 1) {
			holder.bestAnswer.setVisibility(View.VISIBLE);
		} else {
			holder.bestAnswer.setVisibility(View.GONE);
		}

		String url = note.getImg() + "";
		if (!url.equals("")) {
			url = UrlUtil.ROOT_URL + url;
		}
		ImageLoaderUtil.display(url, holder.faceAnswer);
		return arg1;
	}

	public class ViewHolder {
		ImageView faceAnswer;
		TextView nameAnswer;
		TextView levelAnswer;
		ImageView bestAnswer;
		TextView contentAnswer;
		TextView dateAnswer;
		CheckBox goodAnswer;
	}

}
