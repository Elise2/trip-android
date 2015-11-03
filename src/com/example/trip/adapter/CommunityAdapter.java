package com.example.trip.adapter;

import java.util.List;

import org.w3c.dom.Text;

import com.example.trip.R;
import com.example.trip.activity.DiscussActivity;
import com.example.trip.entity.Community;
import com.example.trip.util.ImageLoaderUtil;
import com.example.trip.util.UrlUtil;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class CommunityAdapter extends BaseAdapter {
	private List<Community> datas;
	private LayoutInflater inflater;
	private Context context;

	public CommunityAdapter(List<Community> datas, Context context) {
		super();
		this.datas = datas;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas == null ? 0 : datas.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return datas == null ? null : datas.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (arg1 == null) {
			viewHolder = new ViewHolder();
			arg1 = inflater.inflate(R.layout.item_community, null);
			viewHolder.face = (ImageView) arg1.findViewById(R.id.face);
			viewHolder.img = (ImageView) arg1.findViewById(R.id.img);
			viewHolder.comment = (TextView) arg1.findViewById(R.id.comment);
			viewHolder.name = (TextView) arg1.findViewById(R.id.userName);
			viewHolder.level = (TextView) arg1.findViewById(R.id.userLevel);
			viewHolder.time = (TextView) arg1.findViewById(R.id.times);
			viewHolder.location = (TextView) arg1.findViewById(R.id.location);
			viewHolder.content = (TextView) arg1.findViewById(R.id.content);
			viewHolder.like = (CheckBox) arg1.findViewById(R.id.like);
			arg1.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) arg1.getTag();
		}
		final Community data = datas.get(arg0);
		viewHolder.name.setText(data.getShortCommentname());
		viewHolder.time.setText(data.getShortCommentTime());
		viewHolder.comment.setText(data.getShortCommentNum() + "");
		viewHolder.level.setText(data.getShortCommentLevel());
		viewHolder.location.setText(data.getShortCommentLocation());
		if ("".equals(data.getShortCommentContent())) {
			viewHolder.content.setVisibility(View.GONE);
		} else {
			viewHolder.content.setVisibility(View.VISIBLE);
			viewHolder.content.setText(data.getShortCommentContent());
		}
		viewHolder.img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, DiscussActivity.class);
				intent.putExtra("community", data);
				context.startActivity(intent);

			}
		});
		viewHolder.comment.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, DiscussActivity.class);
				intent.putExtra("community", data);
				context.startActivity(intent);

			}
		});
		viewHolder.like.setText(data.getShortCommentLike() + "");
		String url = data.getShortCommentImg() + "";
		if (!url.equals("")) {
			url = UrlUtil.ROOT_URL + url;
			ImageLoaderUtil.display(url, viewHolder.img);
		}
		String faceUrl = data.getShortCommentFace() + "";
		if (!faceUrl.equals("")) {
			faceUrl = UrlUtil.ROOT_URL + faceUrl;
			ImageLoaderUtil.display(faceUrl, viewHolder.face);
		}

		final CheckBox checkBox = viewHolder.like;
		viewHolder.like
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
		return arg1;
	}

	public class ViewHolder {
		TextView name;
		ImageView img;
		ImageView face;
		TextView level;
		TextView location;
		TextView time;
		CheckBox like;
		TextView comment;
		TextView content;

	}
}
