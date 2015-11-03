package com.example.trip.activity;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.example.trip.R;
import com.example.trip.entity.Hotel;
import com.example.trip.entity.Hunter;
import com.example.trip.entity.Order;
import com.example.trip.locationselect.OrderHotel;
import com.example.trip.util.DatabaseOpenHelper;
import com.example.trip.util.StringPostRequest;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;
import com.example.viewdemo.main.OrderHotelCalendar;
import com.j256.ormlite.dao.Dao;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class OrderForHunterActivity extends Activity {
	private Hunter hunter;
	private TextView hotelName;
	private TextView orderTime;
	private EditText orderNum;
	private TextView orderPrice;
	private EditText orderName;
	private EditText phoneNum;
	private EditText Email;
	private SharedPreferences sp;
	private Button goOrder;
	private String inday;
	private Order order;
	private Dao<Order, Integer> orderDao;
	private int order_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hotelorder);
		hunter = (Hunter) getIntent().getSerializableExtra("hunter");
		orderDao = DatabaseOpenHelper.getInstance(this).getOrderDao();
		initView();
		initViewData();
	}

	public void initView() {
		hotelName = (TextView) findViewById(R.id.hotelName);
		orderNum = (EditText) findViewById(R.id.ONum);
		orderTime = (TextView) findViewById(R.id.Otiem);
		orderPrice = (TextView) findViewById(R.id.Oprice);
		orderName = (EditText) findViewById(R.id.Otiem1);
		phoneNum = (EditText) findViewById(R.id.ONum1);
		Email = (EditText) findViewById(R.id.Oprice1);
		goOrder = (Button) findViewById(R.id.goOrder);

	}

	public void initViewData() {
		hotelName.setText(hunter.getHunter_name());
		orderPrice.setText(hunter.getHunter_price());
		try {
			order_id = (int) (orderDao.countOf() + 1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		goOrder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AddData();
				// alertDialogEvent();
			}

			private void alertDialogEvent() {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						OrderForHunterActivity.this);
				builder.setTitle("温馨提示").setMessage("提交订单后查看我的订单");
				builder.setPositiveButton("确定",
						new android.content.DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								try {
									AddData();
									order = new Order(hotelName.getText()
											.toString(), orderTime.getText()
											.toString(), Integer
											.parseInt(orderNum.getText()
													.toString()), Integer
											.parseInt(orderPrice.getText()
													.toString()), orderName
											.getText().toString(), phoneNum
											.getText().toString(), Email
											.getText().toString(), order_id, 2);
									orderDao.createIfNotExists(order);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								Intent intent = new Intent(
										getApplicationContext(),
										MyOrderActivity.class);
								intent.putExtra("order", order);
								startActivity(intent);
							}
						});
				builder.setNegativeButton("取消",
						new android.content.DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								dialog.dismiss();
							}
						});
				AlertDialog dialog = builder.create();
				dialog.setCancelable(false);
				dialog.show();
			}
		});
	}

	public void AddData() {
		StringPostRequest postRequest = new StringPostRequest(
				UrlUtil.TRIP_ORDER_URL, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						JSONObject object;
						try {
							object = new JSONObject(arg0);
							String s = object.getString("info");
							if ("suc".equals(s)) {
								AlertDialog.Builder builder = new AlertDialog.Builder(
										OrderForHunterActivity.this);
								builder.setTitle("温馨提示").setMessage(
										"提交订单成功，进入我的订单");
								builder.setPositiveButton(
										"确定",
										new android.content.DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {

												try {
													order = new Order(
															hotelName.getText()
																	.toString(),
															orderTime.getText()
																	.toString(),
															Integer.parseInt(orderNum
																	.getText()
																	.toString()),
															Integer.parseInt(orderPrice
																	.getText()
																	.toString()),
															orderName.getText()
																	.toString(),
															phoneNum.getText()
																	.toString(),
															Email.getText()
																	.toString(),
															order_id, 2);
													orderDao.createIfNotExists(order);
												} catch (SQLException e) {
													// TODO Auto-generated catch
													// block
													e.printStackTrace();
												}
												Intent intent = new Intent(
														getApplicationContext(),
														MyOrderActivity.class);
												intent.putExtra("order", order);
												startActivity(intent);
											}
										});
								builder.setNegativeButton(
										"取消",
										new android.content.DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub
												dialog.dismiss();
											}
										});
								AlertDialog dialog = builder.create();
								dialog.setCancelable(false);
								dialog.show();

							} else if ("error".equals(s)) {
								AlertDialog.Builder builder = new AlertDialog.Builder(
										OrderForHunterActivity.this);
								builder.setTitle("温馨提示")
										.setMessage("订单提交失败！！！");
								builder.setPositiveButton(
										"确定",
										new android.content.DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												dialog.dismiss();

											}
										});

								AlertDialog dialog = builder.create();
								dialog.setCancelable(false);
								dialog.show();
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(OrderForHunterActivity .this, "网络连接错误",
								Toast.LENGTH_LONG).show();
					}
				});
		postRequest.PutParams("action", "add");
		postRequest.PutParams("userId", TripApplication.getInstance().getUser()
				.getUser_id()
				+ "");
		postRequest.PutParams("name", hotelName.getText().toString());
		postRequest.PutParams("time", orderTime.getText().toString());
		postRequest.PutParams("num", orderNum.getText().toString());
		postRequest.PutParams("price", orderPrice.getText().toString());
		postRequest.PutParams("cName", orderName.getText().toString());
		postRequest.PutParams("Ctel", phoneNum.getText().toString());
		postRequest.PutParams("img", hunter.getHunter_title_img());
		postRequest.PutParams("top", 2 + "");
		TripApplication.getInstance().getRequestQueue().add(postRequest);
	}

	@Override
	protected void onStart() {
		super.onStart();
		sp = getSharedPreferences("date", Context.MODE_PRIVATE);
		inday = sp.getString("dateIn", "");
		if (!"".equals(inday)) {
			orderTime.setText(inday + "");
		}
	}
}
