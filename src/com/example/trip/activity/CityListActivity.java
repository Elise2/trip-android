package com.example.trip.activity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import com.example.trip.R;
import com.example.trip.adapter.CityNavigationAdapter;
import com.example.trip.entity.CityModel;
import com.example.trip.entity.Schedule;
import com.example.trip.util.Constant;
import com.example.trip.util.DBManager;
import com.example.trip.util.DatabaseOpenHelper;
import com.example.trip.util.ExecutorManager;
import com.example.trip.util.MyLetterListView;
import com.example.trip.util.StringPostRequest;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;
import com.example.trip.util.MyLetterListView.OnTouchingLetterChangedListener;
import com.google.gson.Gson;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.dao.Dao;

/**
 * 城市列表
 * 
 * @author sy
 * 
 */
public class CityListActivity extends Activity implements OnClickListener {
	private String category;
	private BaseAdapter adapter;
	private ListView mCityLit;
	private TextView overlay;
	private MyLetterListView letterListView;
	private HashMap<String, Integer> alphaIndexer;// 存放存在的汉语拼音首字母和与之对应的列表位置
	private String[] sections;// 存放存在的汉语拼音首字母
	private Handler handler;
	private OverlayThread overlayThread;
	private SQLiteDatabase database;
	private ArrayList<CityModel> mCityNames;
	private ListView list;
	private boolean flags;
	private String cityName;
	private CityNavigationAdapter cityNavigationAdapter;
	private List<CityModel> cityModels;
	private RelativeLayout item;
	private ImageView load;
	private AnimationDrawable animaition;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.city_list);
		findViewById(R.id.search).setOnClickListener(this);
		findViewById(R.id.back).setOnClickListener(this);

		category = getIntent().getStringExtra("category");
		mCityLit = (ListView) findViewById(R.id.city_list);
		letterListView = (MyLetterListView) findViewById(R.id.cityLetterListView);
		DBManager dbManager = new DBManager(this);
		dbManager.openDateBase();
		dbManager.closeDatabase();
		flags = false;
		database = SQLiteDatabase.openOrCreateDatabase(DBManager.DB_PATH + "/"
				+ DBManager.DB_NAME, null);
		mCityNames = getCityNames();
		database.close();
		letterListView
				.setOnTouchingLetterChangedListener(new LetterListViewListener());
		alphaIndexer = new HashMap<String, Integer>();
		handler = new Handler();
		overlayThread = new OverlayThread();
		initOverlay();
		setAdapter(mCityNames);
		mCityLit.setOnItemClickListener(new CityListOnItemClick());
		item = (RelativeLayout) findViewById(R.id.item);
		list = (ListView) findViewById(R.id.list);
		cityModels = new ArrayList<CityModel>();
		load = (ImageView) findViewById(R.id.load);
		cityNavigationAdapter = new CityNavigationAdapter(cityModels,
				CityListActivity.this, 1);
		load.setBackgroundResource(R.anim.animation_list);
		animaition = (AnimationDrawable) load.getBackground();
		animaition.setOneShot(false);
		list.setAdapter(cityNavigationAdapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				CityModel cityModel = (CityModel) cityNavigationAdapter
						.getItem(arg2);
				if ("discovery".equals(category)) {
					Intent intent = new Intent(getApplicationContext(),
							CityDetailsActivity.class);
					intent.putExtra("city",
							(CityModel) cityNavigationAdapter.getItem(arg2));
					startActivity(intent);
				} else if ("godate".equals(category)) {
					Intent intent = new Intent();
					intent.putExtra("goCityName", cityModel.getCityName());
					intent.putExtra("goCityId", cityModel.getCityId());
					setResult(2, intent);
					finish();

					// SharedPreferences sPreferences = getSharedPreferences(
					// Constant.SP_NAME, MODE_PRIVATE);
					// SharedPreferences.Editor editor = sPreferences.edit();
					// CityModel cityModel = (CityModel) cityNavigationAdapter
					// .getItem(arg2);
					// editor.putString("goCity", cityModel.getCityName());
					// editor.commit();
					// finish();
				} else if ("date".equals(category)) {

					final Schedule date = (Schedule) getIntent()
							.getSerializableExtra("date");
					cityModel = (CityModel) cityNavigationAdapter.getItem(arg2);
					date.setCityName(cityModel.getCityName());
					date.setCityId(cityModel.getCityId());
					Intent intent = new Intent();
					intent.putExtra("date", date);
					setResult(4, intent);
					finish();

				} else if ("datess".equals(category)) {

					cityModel = (CityModel) cityNavigationAdapter.getItem(arg2);
					Intent intent = new Intent();
					intent.putExtra("goCityName", cityModel.getCityName());
					intent.putExtra("goCityId", cityModel.getCityId());
					intent.putExtra("num", getIntent().getIntExtra("num", 0));
					setResult(7, intent);
					finish();
				}

			}
		});
	}

	/**
	 * 从数据库获取城市数据
	 * 
	 * @return
	 */
	private ArrayList<CityModel> getCityNames() {
		ArrayList<CityModel> names = new ArrayList<CityModel>();
		Cursor cursor = database.rawQuery(
				"SELECT * FROM T_City ORDER BY NameSort", null);
		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToPosition(i);
			CityModel cityModel = new CityModel();
			cityModel.setCityName(cursor.getString(cursor
					.getColumnIndex("CityName")));
			cityModel.setNameSort(cursor.getString(cursor
					.getColumnIndex("NameSort")));
			cityModel.setCityId(Integer.parseInt(cursor.getString(cursor
					.getColumnIndex("CityId"))));
			names.add(cityModel);
		}
		return names;
	}

	/**
	 * 城市列表点击事件
	 * 
	 * @author sy
	 * 
	 */
	class CityListOnItemClick implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
				long arg3) {
			TranslateAnimation mShowAction = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.0f,
					Animation.RELATIVE_TO_SELF, 0.0f,
					Animation.RELATIVE_TO_SELF, 0.0f);
			mShowAction.setDuration(500);
			TranslateAnimation mHiddenAction = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0.0f,
					Animation.RELATIVE_TO_SELF, 1.0f,
					Animation.RELATIVE_TO_SELF, 0.0f,
					Animation.RELATIVE_TO_SELF, 0.0f);
			mHiddenAction.setDuration(500);
			CityModel cityModel = (CityModel) mCityLit.getAdapter()
					.getItem(pos);
			// Toast.makeText(CityListActivity.this, cityModel.getCityName(),
			// Toast.LENGTH_SHORT).show();

			if (cityName == null || cityModel.getCityName().equals(cityName)) {
				cityName = cityModel.getCityName();
				if (flags) {
					item.startAnimation(mHiddenAction);
					item.setVisibility(View.GONE);
					flags = false;
				} else {
					list.setVisibility(View.GONE);
					load.setVisibility(View.VISIBLE);
					animaition.start();
					item.startAnimation(mShowAction);
					item.setVisibility(View.VISIBLE);
					initCityItem(cityModel.getCityId());
					flags = true;
				}
			} else {
				cityName = cityModel.getCityName();
				initCityItem(cityModel.getCityId());
				if (!flags) {
					list.setVisibility(View.GONE);
					load.setVisibility(View.VISIBLE);
					// animaition.start();
					item.startAnimation(mShowAction);
					item.setVisibility(View.VISIBLE);
					flags = true;
				}
			}

		}
	}

	public void initCityItem(int cityId) {
		StringPostRequest request = new StringPostRequest(
				UrlUtil.TRIP_CITY_URL, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						List<CityModel> citys = new ArrayList<CityModel>();

						Gson gson = new Gson();
						citys = gson.fromJson(arg0,
								new TypeToken<ArrayList<CityModel>>() {
								}.getType());
						if (citys.size() > 0) {
							cityModels.clear();
							cityModels.addAll(citys);
							cityNavigationAdapter.notifyDataSetChanged();
							if (animaition.isRunning())// 是否正在运行？
							{
								animaition.stop();// 停止
								list.setVisibility(View.VISIBLE);
								load.setVisibility(View.GONE);
							}
						} else {
							list.setVisibility(View.GONE);
							load.setVisibility(View.VISIBLE);
							animaition.start();
						}

					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "网络连接失败",
								Toast.LENGTH_LONG).show();
					}
				});
		request.PutParams("action", "seek");
		request.PutParams("category", cityId + "");
		TripApplication.getInstance().getRequestQueue().add(request);
	}

	/**
	 * 为ListView设置适配器
	 * 
	 * @param list
	 */
	private void setAdapter(List<CityModel> list) {
		if (list != null) {
			adapter = new ListAdapter(this, list);
			mCityLit.setAdapter(adapter);
		}

	}

	/**
	 * ListViewAdapter
	 * 
	 * @author sy
	 * 
	 */
	private class ListAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private List<CityModel> list;

		public ListAdapter(Context context, List<CityModel> list) {

			this.inflater = LayoutInflater.from(context);
			this.list = list;
			alphaIndexer = new HashMap<String, Integer>();
			sections = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				// 当前汉语拼音首字母
				// getAlpha(list.get(i));
				String currentStr = list.get(i).getNameSort();
				// 上一个汉语拼音首字母，如果不存在为“ ”
				String previewStr = (i - 1) >= 0 ? list.get(i - 1)
						.getNameSort() : " ";
				if (!previewStr.equals(currentStr)) {
					String name = list.get(i).getNameSort();
					alphaIndexer.put(name, i);
					sections[i] = name;
				}
			}

		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.list_item, null);
				holder = new ViewHolder();
				holder.alpha = (TextView) convertView.findViewById(R.id.alpha);
				holder.name = (TextView) convertView.findViewById(R.id.name);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.name.setText(list.get(position).getCityName());
			String currentStr = list.get(position).getNameSort();
			String previewStr = (position - 1) >= 0 ? list.get(position - 1)
					.getNameSort() : " ";
			if (!previewStr.equals(currentStr)) {
				holder.alpha.setVisibility(View.VISIBLE);
				holder.alpha.setText(currentStr);
			} else {
				holder.alpha.setVisibility(View.GONE);
			}
			return convertView;
		}

		private class ViewHolder {
			TextView alpha;
			TextView name;
		}

	}

	// 初始化汉语拼音首字母弹出提示框
	private void initOverlay() {
		LayoutInflater inflater = LayoutInflater.from(this);
		overlay = (TextView) inflater.inflate(R.layout.overlay, null);
		overlay.setVisibility(View.INVISIBLE);
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_APPLICATION,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
						| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
				PixelFormat.TRANSLUCENT);
		WindowManager windowManager = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		windowManager.addView(overlay, lp);
	}

	private class LetterListViewListener implements
			OnTouchingLetterChangedListener {

		@Override
		public void onTouchingLetterChanged(final String s) {
			if (alphaIndexer.get(s) != null) {
				int position = alphaIndexer.get(s);
				mCityLit.setSelection(position);
				overlay.setText(sections[position]);
				overlay.setVisibility(View.VISIBLE);
				handler.removeCallbacks(overlayThread);
				// 延迟一秒后执行，让overlay为不可见
				handler.postDelayed(overlayThread, 1500);
			}
		}

	}

	// 设置overlay不可见
	private class OverlayThread implements Runnable {

		@Override
		public void run() {
			overlay.setVisibility(View.GONE);
		}

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.search:
			startActivity(new Intent(this, SearchLocationActivity.class));
			break;
		default:
			break;
		}

	}

}