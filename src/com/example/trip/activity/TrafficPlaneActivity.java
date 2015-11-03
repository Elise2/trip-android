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
import com.example.trip.adapter.LookForPlaneAdapter;
import com.example.trip.entity.Plane;

public class TrafficPlaneActivity extends Activity implements OnClickListener {
	private ListView list;
	private LookForPlaneAdapter adapter;
	private List<Plane> datas;
	private TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		title = (TextView) findViewById(R.id.title);
		list = (ListView) findViewById(R.id.list);
		findViewById(R.id.settingImg).setOnClickListener(this);
		datas = new ArrayList<Plane>();
		adapter = new LookForPlaneAdapter(datas, getApplicationContext());
		list.setAdapter(adapter);
		initData();
	}

	public void initData() {
		List<Plane> planes = new ArrayList<Plane>();
		planes = (List<Plane>) getIntent().getSerializableExtra("traffic");
		if (planes != null && planes.size() > 0) {
			title.setText(planes.get(0).getDepCity() + "——"
					+ planes.get(0).getArrCity());
			datas.clear();
			datas.addAll(planes);
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
