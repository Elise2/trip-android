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
import com.example.trip.activity.CityDetailsActivity;
import com.example.trip.activity.ShortCityStrategyDetailActivity;
import com.example.trip.adapter.CityNavigationAdapter;
import com.example.trip.adapter.CollectionArticleAdapter;
import com.example.trip.entity.CityModel;
import com.example.trip.util.DatabaseOpenHelper;
import com.j256.ormlite.dao.Dao;

public class CollectionCityFragment extends Fragment {
	private ListView list;
	private CityNavigationAdapter adapter;
	private List<CityModel> methods;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		methods = new ArrayList<CityModel>();
		adapter = new CityNavigationAdapter(methods, getActivity(), 2);
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
				CityModel city = (CityModel) adapter.getItem(arg2);
				Intent intent = new Intent(getActivity(),
						CityDetailsActivity.class);
				intent.putExtra("city", city);
				startActivity(intent);
			}
		});
		initData();
		return v;
	}

	public void initData() {
		Dao<CityModel, Integer> collectionCityDao = DatabaseOpenHelper
				.getInstance(getActivity()).getCollectionCityDao();
		try {
			List<CityModel> datas = collectionCityDao.queryForAll();
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
