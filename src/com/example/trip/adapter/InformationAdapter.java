package com.example.trip.adapter;

import java.util.List;

import com.example.trip.R;
import com.example.trip.entity.Infomation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InformationAdapter extends BaseAdapter {
	private List<Infomation> mdata;
	private Context mContext;

	public InformationAdapter(List<Infomation> mdata, Context mContext) {
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
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		SHolder holder = null;
		if (arg1 == null) {
			holder = new SHolder();
			arg1 = LayoutInflater.from(mContext).inflate(
					R.layout.infomation_item, null);
			holder.title = (TextView) arg1.findViewById(R.id.infoTitle);
			holder.content = (TextView) arg1.findViewById(R.id.infoContent);
			arg1.setTag(holder);
		} else {
			holder = (SHolder) arg1.getTag();
		}
		holder.title.setText(this.mdata.get(arg0).getTitle());
		holder.content.setText(this.mdata.get(arg0).getContent());
		return arg1;
	}

	public class SHolder {
		public TextView title;
		public TextView content;
	}

}
