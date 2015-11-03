package com.example.trip.activity;

import com.example.trip.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class CalculatorActivity extends Activity {
	private GridView view;
	private int first = 0, second = 0;
	private String action = "1";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_calculator);
		view = (GridView) findViewById(R.id.view);
		MyAdapter adapter = new MyAdapter(this);
		view.setAdapter(adapter);
		view.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				

			}
		});

	}

	public class MyAdapter extends BaseAdapter {
		private Context context;
		private Integer[] imgs = { R.drawable.account_page_calc_one_up,
				R.drawable.account_page_calc_two_up,
				R.drawable.account_page_calc_three_up,
				R.drawable.account_page_calc_clear_up,

				R.drawable.account_page_calc_four_up,
				R.drawable.account_page_calc_five_up,
				R.drawable.account_page_calc_six_up,
				R.drawable.account_page_calc_minus_up,

				R.drawable.account_page_calc_seven_up,
				R.drawable.account_page_calc_eight_up,
				R.drawable.account_page_calc_nine_up,
				R.drawable.account_page_calc_plus_up,

				R.drawable.account_page_calc_empty_up,
				R.drawable.account_page_calc_zero_up,
				R.drawable.account_page_calc_point_up,
				R.drawable.account_page_calc_cancel_up,

		};

		public MyAdapter(Context context) {
			super();
			this.context = context;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imgs.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			ImageView imageView;
			if (arg1 == null) {
				imageView = new ImageView(context);
				imageView.setLayoutParams(new GridView.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				imageView.setAdjustViewBounds(false);
				// imageView.setScaleType(ScaleType.FIT_XY);

			} else {
				imageView = (ImageView) arg1;
			}
			imageView.setImageResource(imgs[arg0]);

			return imageView;
		}

	}
}
