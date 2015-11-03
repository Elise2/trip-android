package com.example.trip.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.BDLocation.a;
import com.example.trip.R;
import com.example.trip.adapter.LookForStationAdapter;
import com.example.trip.entity.Station;

public class TrafficStatioActivity extends Activity implements OnClickListener {
	private ListView list;
	private LookForStationAdapter adapter;
	private List<Station> datas;
	private TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		list = (ListView) findViewById(R.id.list);
		title = (TextView) findViewById(R.id.title);
		findViewById(R.id.settingImg).setOnClickListener(this);
		datas = new ArrayList<Station>();
		adapter = new LookForStationAdapter(datas, getApplicationContext());
		list.setAdapter(adapter);
		initData();
	}

	public void initData() {
		List<Station> Stations = new ArrayList<Station>();
		Stations = (List<Station>) getIntent().getSerializableExtra("traffic");
		if (Stations != null && Stations.size() > 0) {
			title.setText(Stations.get(0).getStart_station() + "——"
					+ Stations.get(0).getEnd_station());
			datas.clear();
			datas.addAll(Stations);
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.settingImg:
			finish();
			break;

		default:
			break;
		}
	}
}
