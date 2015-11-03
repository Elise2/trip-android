package com.example.trip.fragment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.baidu.location.BDLocation.a;
import com.example.trip.R;
import com.example.trip.R.layout;
import com.example.trip.activity.ShortCityStrategyDetailActivity;
import com.example.trip.adapter.CollectionArticleAdapter;
import com.example.trip.entity.PlayMethod;
import com.example.trip.util.DatabaseOpenHelper;
import com.j256.ormlite.dao.Dao;

public class CollectionArticleFragment extends Fragment {
	private ListView list;
	private CollectionArticleAdapter adapter;
	private List<PlayMethod> methods;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		methods = new ArrayList<PlayMethod>();
		adapter = new CollectionArticleAdapter(methods, getActivity());

	}

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.list, null);
		v.findViewById(R.id.bar).setVisibility(View.GONE);
		v.findViewById(R.id.divide).setVisibility(View.GONE);
		list = (ListView) v.findViewById(R.id.list);
		list.setAdapter(adapter);
		list.setDivider(null);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				PlayMethod method = (PlayMethod) adapter.getItem(arg2);
				Intent intent = new Intent(getActivity(),
						ShortCityStrategyDetailActivity.class);
				intent.putExtra("situation", method);
				startActivity(intent);
			}
		});
		initData();
		return v;
	}

	public void initData() {
		Dao<PlayMethod, Integer> collectionArticleDao = DatabaseOpenHelper
				.getInstance(getActivity()).getCollectionArticleDao();
		try {
			List<PlayMethod> datas = collectionArticleDao.queryForAll();
			if (datas != null & datas.size() > 0) {
				methods.clear();
				methods.addAll(datas);
				adapter.notifyDataSetChanged();

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
