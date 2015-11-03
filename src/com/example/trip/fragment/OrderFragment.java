package com.example.trip.fragment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.baidu.location.BDLocation.a;
import com.example.trip.R;
import com.example.trip.activity.NoteDeatailActivity;
import com.example.trip.activity.OrderHotelLastActivity;
import com.example.trip.adapter.NotesAdapter;
import com.example.trip.adapter.OrderAdapter;
import com.example.trip.adapter.SceneryNoteAdapter;
import com.example.trip.entity.Discuss;
import com.example.trip.entity.Order;
import com.example.trip.entity.PlayNote;
import com.example.trip.util.DatabaseOpenHelper;
import com.example.trip.util.StringPostRequest;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class OrderFragment extends Fragment {
	// 我的订单中的酒店订单
	private List<Order> mdata;
	private OrderAdapter adapter;
	private int num;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mdata = new ArrayList<Order>();
		num = getArguments().getInt("isTop");
		adapter = new OrderAdapter(mdata, getActivity(), num);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.myorder_listview, null);
		ListView list = (ListView) view.findViewById(R.id.orderlist);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Order order = (Order) adapter.getItem(arg2);
				Intent intent = new Intent(getActivity(),
						OrderHotelLastActivity.class);
				intent.putExtra("order", order);
				startActivity(intent);
			}
		});
		initOrder();
		return view;
	}

	public void initOrder() {
		StringPostRequest stringPost = new StringPostRequest(
				UrlUtil.TRIP_ORDER_URL, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub
						Gson gson = new Gson();
						List<Order> orders = new ArrayList<Order>();
						orders = gson.fromJson(arg0,
								new TypeToken<ArrayList<Order>>() {
								}.getType());
						if (orders.size() != 0) {
							mdata.clear();
							mdata.addAll(orders);
						}
						adapter.notifyDataSetChanged();
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_LONG)
								.show();
					}
				});
		stringPost.PutParams("action", "select");
		stringPost.PutParams("top", num + "");
		stringPost.PutParams("user_id", TripApplication.getInstance().getUser()
				.getUser_id()
				+ "");
		TripApplication.getInstance().getRequestQueue().add(stringPost);

	}

	// public void initData() {
	// List<Order> orders = new ArrayList<Order>();
	// try {
	// orders = DatabaseOpenHelper.getInstance(getActivity())
	// .getOrderDao().queryForAll();
	// if (orders != null && orders.size() > 0) {
	// mdata.clear();
	// for (int i = 0; i < orders.size(); i++) {
	// if (orders.get(i).getIsTop() == num)
	// mdata.add(orders.get(i));
	// }
	// // mdata.addAll(orders);
	// adapter.notifyDataSetChanged();
	// }
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
}
