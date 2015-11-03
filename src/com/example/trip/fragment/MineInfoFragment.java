package com.example.trip.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.trip.R;
import com.example.trip.adapter.InformationAdapter;
import com.example.trip.entity.Infomation;
import com.example.trip.manager.DataManager;

public class MineInfoFragment extends Fragment {
	private DataManager dataManager;
	private List<Infomation> mdata;
	private InformationAdapter adapter;
	Handler handle = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		dataManager = new DataManager();
		mdata = new ArrayList<Infomation>();
		Bundle bundle = getArguments();
		int typeId = bundle.getInt("typeId");
		this.mdata.addAll(dataManager.getNewsById(typeId));
		adapter = new InformationAdapter(mdata, getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.info_listview, null);
		ListView list = (ListView) view.findViewById(R.id.mylist);
		// TODO Auto-generated method stub
		list.setAdapter(adapter);
		return view;
	}
}
