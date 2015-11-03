package com.example.trip.fragment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.baidu.location.BDLocation.a;
import com.example.trip.R;
import com.example.trip.activity.NoteDeatailActivity;
import com.example.trip.adapter.NotesAdapter;
import com.example.trip.adapter.SceneryNoteAdapter;
import com.example.trip.entity.PlayNote;
import com.example.trip.util.DatabaseOpenHelper;

public class PlayNoteFragment extends Fragment {
	// 个人收藏中的游记模块
	private List<PlayNote> mdata;
	private SceneryNoteAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mdata = new ArrayList<PlayNote>();
		adapter = new SceneryNoteAdapter(mdata, getActivity(), 2);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.info_listview, null);
		ListView list = (ListView) view.findViewById(R.id.mylist);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				PlayNote note = (PlayNote) adapter.getItem(arg2);
				Intent intent = new Intent(getActivity(),
						NoteDeatailActivity.class);
				intent.putExtra("playNote", note);
				startActivity(intent);
			}
		});
		initData();
		return view;
	}

	public void initData() {
		List<PlayNote> notes = new ArrayList<PlayNote>();
		try {
			notes = DatabaseOpenHelper.getInstance(getActivity())
					.getPlayNoteDao().queryForAll();
			if (notes != null && notes.size() > 0) {
				mdata.clear();
				mdata.addAll(notes);
				adapter.notifyDataSetChanged();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
