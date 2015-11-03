package com.example.trip.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.trip.R;
import com.example.trip.adapter.PartnerAdapter;
import com.example.trip.entity.Partner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class PartnerActivity extends Activity implements OnClickListener {
	private ListView myList;
	private List<Partner> mdata;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.takepartner);
		myList = (ListView) findViewById(R.id.partList);
		findViewById(R.id.icBack).setOnClickListener(this);
		mdata = new ArrayList<Partner>();
		initList();
		PartnerAdapter adapter = new PartnerAdapter(mdata,
				getApplicationContext());
		myList.setAdapter(adapter);

	}

	public void initList() {
		mdata.add(new Partner("悠悠君", "", "烟台", "北京", "2015-10-30", 7,
				"一周内有时间的求陪同！"));
		mdata.add(new Partner("hahh", "", "青岛", "上海", "2015-10-1", 7,
				"不错的旅程，你要么！"));
		mdata.add(new Partner("elise", "", "烟台", "北京", "2015-10-30", 7,
				"一周内有时间的求陪同！"));
		mdata.add(new Partner("qq", "", "深圳", "大连", "2015-11-23", 3,
				"一周内有时间的求陪同！"));
		mdata.add(new Partner("知了", "", "丹东", "香港", "2015-10-20", 7,
				"一周内有时间的求陪同！"));
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.icBack:
			finish();
			break;

		default:
			break;
		}

	}
}
