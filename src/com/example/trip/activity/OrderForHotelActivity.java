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

public class OrderForHotelActivity extends Activity {
	private Hotel hotel;
	private TextView hotelName;
	private EditText orderTime;
	private Button orderNum;
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
	private int orderNumber = 0;
	private Button inCreateBtn;
	private Button outBtn;
	private int isTop;
	private Hunter hunter;

	/**
	 * 这是订单界面，酒店订单，猎人订单
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hotelorder);
		hotel = (Hotel) getIntent().getSerializableExtra("hotel");
		hunter = (Hunter) getIntent().getSerializableExtra("hunter");
		isTop = getIntent().getIntExtra("isTop", 0);
		orderDao = DatabaseOpenHelper.getInstance(this).getOrderDao();
		initView();
		initViewData();
	}

	public void initView() {
		hotelName = (TextView) findViewById(R.id.hotelName);
		orderNum = (Button) findViewById(R.id.changeNum);
		orderTime = (EditText) findViewById(R.id.Otiem);
		orderPrice = (TextView) findViewById(R.id.Oprice);
		orderName = (EditText) findViewById(R.id.Otiem1);
		phoneNum = (EditText) findViewById(R.id.ONum1);
		Email = (EditText) findViewById(R.id.Oprice1);
		goOrder = (Button) findViewById(R.id.goOrder);
		outBtn = (Button) findViewById(R.id.outBtn);// 点击减号
		inCreateBtn = (Button) findViewById(R.id.inCreateBtn);// 加号按钮

	}

	public void initViewData() {
		if (isTop == 1) {
			hotelName.setText(hotel.getOrder_name());
			orderPrice.setText(hotel.getOrder_price());
			outBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (orderNumber > 0) {
						orderNumber = orderNumber - 1;
						orderNum.setText(orderNumber + "");
					}
				}
			});
			inCreateBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					orderNumber = orderNumber + 1;
					orderNum.setText(orderNumber + "");
				}
			});
			try {
				order_id = (int) (orderDao.countOf() + 1);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (isTop == 2) {
			hotelName.setText(hunter.getHunter_descript());
			orderPrice.setText(hunter.getHunter_price() + "");
			outBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (orderNumber > 0) {
						orderNumber = orderNumber - 1;
						orderNum.setText(orderNumber + "");
					}
				}
			});
			inCreateBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					orderNumber = orderNumber + 1;
					orderNum.setText(orderNumber + "");
				}
			});
			try {
				order_id = (int) (orderDao.countOf() + 1);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
						OrderForHotelActivity.this);
				builder.setTitle("温馨提示").setMessage("提交订单后查看我的订单");
				builder.setPositiveButton("确定",
						new android.content.DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								try {
									orderNumber = Integer.parseInt(orderNum
											.getText().toString());
									order = new Order(hotelName.getText()
											.toString(), orderTime.getText()
											.toString(), orderNumber, Integer
											.parseInt(orderPrice.getText()
													.toString()), orderName
											.getText().toString(), phoneNum
											.getText().toString(), Email
											.getText().toString(), order_id,
											isTop);
									orderDao.createIfNotExists(order);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								Intent intent = new Intent(
										getApplicationContext(),
										MyOrderActivity.class);
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
										OrderForHotelActivity.this);
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
													orderNumber = Integer
															.parseInt(orderNum
																	.getText()
																	.toString());
													order = new Order(
															hotelName.getText()
																	.toString(),
															orderTime.getText()
																	.toString(),
															orderNumber,
															Integer.parseInt(orderPrice
																	.getText()
																	.toString()),
															orderName.getText()
																	.toString(),
															phoneNum.getText()
																	.toString(),
															Email.getText()
																	.toString(),
															order_id, isTop);
													orderDao.createIfNotExists(order);
												} catch (SQLException e) {
													// TODO Auto-generated catch
													// block
													e.printStackTrace();
												}
												Intent intent = new Intent(
														getApplicationContext(),
														MyOrderActivity.class);
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
										OrderForHotelActivity.this);
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
						Toast.makeText(OrderForHotelActivity.this, "网络连接错误",
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
		postRequest.PutParams("img", hotel.getOrder_img());
		postRequest.PutParams("top", 1 + "");
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
