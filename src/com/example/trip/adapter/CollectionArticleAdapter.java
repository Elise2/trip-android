package com.example.trip.adapter;

import java.util.List;

import com.example.trip.R;
import com.example.trip.adapter.CityNavigationAdapter.oneViewHolder;
import com.example.trip.entity.PlayMethod;
import com.example.trip.util.ImageLoaderUtil;
import com.example.trip.util.UrlUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CollectionArticleAdapter extends BaseAdapter {
	private List<PlayMethod> datas;
	private LayoutInflater inflater;

	public CollectionArticleAdapter(List<PlayMethod> datas, Context context) {
		super();
		this.datas = datas;
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
		oneViewHolder one = null;
		if (arg1 == null) {
			one = new oneViewHolder();
			arg1 = (View) inflater.inflate(R.layout.item_collection_article,
					null);
			one.time = (TextView) arg1.findViewById(R.id.time);
			one.content = (TextView) arg1.findViewById(R.id.content);
			one.img = (ImageView) arg1.findViewById(R.id.img);
			one.title = (TextView) arg1.findViewById(R.id.title);

			arg1.setTag(one);
		} else {
			one = (oneViewHolder) arg1.getTag();
		}

		PlayMethod data = datas.get(arg0);
		one.title.setText(data.getTitle());
		one.content.setText(data.getContnt());
		one.time.setText(data.getTime());
		one.img.setImageResource(data.getImg());
		return arg1;
	}

	public class oneViewHolder {
		TextView title;
		TextView time;
		TextView content;
		ImageView img;
	}

}
