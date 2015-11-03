package com.example.trip.activity;

import com.example.trip.R;
import com.example.trip.entity.Order;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderHotelLastActivity extends Activity {
	private Order order;
	private TextView hotelName;
	private TextView orderN;
	private TextView orderPhoneNum;
	private TextView orderRoomNum;
	private TextView orderTime;
	private TextView totalMoney;
	private ImageView orderDetailImg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orderdetail);
		order = (Order) getIntent().getSerializableExtra("order");
		initView();
		initData();

	}

	private void initView() {
		hotelName = (TextView) findViewById(R.id.hotelName);
		orderN = (TextView) findViewById(R.id.orderN);//预订人
		orderPhoneNum = (TextView) findViewById(R.id.orderPhoneNum);
		orderRoomNum = (TextView) findViewById(R.id.orderroomnum);
		orderTime = (TextView) findViewById(R.id.orderTime);
		totalMoney = (TextView) findViewById(R.id.totalMoney);
		orderDetailImg = (ImageView) findViewById(R.id.orderDetailImg);
	}

	private void initData() {
		hotelName.setText(order.getHotelName());
		orderN.setText(order.getName());
		orderPhoneNum.setText(order.getPhonenum());
		orderRoomNum.setText(order.getOrderNum() + "");
		orderTime.setText(order.getInTime());
		totalMoney.setText((order.getOrderPrice() * order.getOrderNum()) + "");
		orderDetailImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
