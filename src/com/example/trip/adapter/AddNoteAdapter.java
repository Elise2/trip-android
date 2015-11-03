package com.example.trip.adapter;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trip.R;
import com.example.trip.entity.AddNote;

public class AddNoteAdapter extends BaseAdapter {
	private List<AddNote> traces;
	private LayoutInflater inflater;
	private Context context;

	public AddNoteAdapter(List<AddNote> traces, Context context) {
		super();
		this.traces = traces;
		this.inflater = LayoutInflater.from(context);
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return traces == null ? 0 : traces.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return traces == null ? null : traces.get(arg0);
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
			arg1 = inflater.inflate(R.layout.item_add_note, null);
			holder = new ViewHolder();
			holder.content = (TextView) arg1.findViewById(R.id.tracecontent);
			holder.img = (ImageView) arg1.findViewById(R.id.traceimg);
			arg1.setTag(holder);
		} else {
			holder = (ViewHolder) arg1.getTag();
		}
		final AddNote trace = traces.get(arg0);
		holder.img.setImageBitmap(trace.getBitmap());
		holder.content.setText(trace.getContent());
		final TextView coTextView = holder.content;
		holder.content.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = builder = new AlertDialog.Builder(
						context);
				builder.setTitle("描述");
				View view = inflater.inflate(R.layout.myintroduce, null);
				final EditText text = (EditText) view
						.findViewById(R.id.introducecontains);
				if (!"添加描述...".equals(trace.getContent())) {
					text.setText(trace.getContent());
				}
				builder.setView(view);
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								coTextView.setText(text.getText().toString());
								trace.setContent(text.getText().toString());
							}
						});
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								arg0.dismiss();
							}
						});
				builder.create().show();
			}
		});
		return arg1;
	}

	private class ViewHolder {
		TextView content;
		ImageView img;
	}
}
