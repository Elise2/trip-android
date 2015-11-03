package com.example.trip.activity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.trip.R;
import com.example.trip.adapter.BeenAdapter;
import com.example.trip.entity.BeenCityModel;
import com.example.trip.entity.CityModel;
import com.example.trip.util.DatabaseOpenHelper;
import com.j256.ormlite.dao.Dao;

public class BeenActivity extends Activity implements OnClickListener {
	private ListView listView;
	private BeenAdapter adapter;
	private List<BeenCityModel> methods;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_been);
		listView = (ListView) findViewById(R.id.bennlist);
		findViewById(R.id.addbeen).setOnClickListener(this);
		methods = new ArrayList<BeenCityModel>();
		adapter = new BeenAdapter(methods, this);
		listView.setAdapter(adapter);
		listView.setDivider(null);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				BeenCityModel city = (BeenCityModel) adapter.getItem(arg2);
				CityModel citys = new CityModel(city.getCityName(), city
						.getCityId(), city.isCity(), city.getCityTraverNum(),
						city.getCityCategory(), city.getNameSort(), city
								.getCityImg());
				Intent intent = new Intent(BeenActivity.this,
						CityDetailsActivity.class);
				intent.putExtra("city", citys);
				startActivity(intent);
			}
		});
		initData();

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		initData();
	}

	public void initData() {
		Dao<BeenCityModel, Integer> beenDao = DatabaseOpenHelper.getInstance(
				this).getBeenDao();
		try {
			List<BeenCityModel> datas = beenDao.queryForAll();
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

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.addbeen:
			Intent intent = new Intent(BeenActivity.this,
					CityListActivity.class);
			intent.putExtra("category", "discovery");
			startActivity(intent);
			break;

		default:
			break;
		}

	}
}
