package com.example.trip.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.example.trip.R;
import com.example.trip.activity.CityListActivity;
import com.example.trip.activity.LookForTrafficActivity;
import com.example.trip.activity.NoteDeatailActivity;
import com.example.trip.activity.NoteMainActivity;
import com.example.trip.activity.SearchLocationActivity;
import com.example.trip.activity.ShortCityStrategyDetailActivity;
import com.example.trip.adapter.HomePagerViewAdapter;
import com.example.trip.adapter.HomepagerListview_ItemAdapter;
import com.example.trip.entity.PlayMethod;
import com.example.trip.entity.PlayNote;
import com.example.trip.locationselect.OrderHotel;
import com.example.trip.manager.DataManager;
import com.example.trip.util.StringPostRequest;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * ����������
 * 
 * @author Administrator
 * 
 */
public class DiscoverFragment extends Fragment implements OnClickListener {
	private LinearLayout headerIndicatorContainer;
	private ListView mylist;
	private List<PlayNote> mdata;
	private List<PlayNote> newData;
	private ViewPager myPager;
	private boolean isInit = false;
	private HomepagerListview_ItemAdapter adapter;
	private Button playMethodChange;
	private List<PlayMethod> methods;
	private int count;
	private ImageView playMethodImg;
	private TextView playMethodTitle, playMethodContent;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mdata = new ArrayList<PlayNote>();
		newData = new ArrayList<PlayNote>();
		methods = DataManager.initMethods();
		initNotes();
		adapter = new HomepagerListview_ItemAdapter(newData, getActivity());
		count = 0;
	}

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.layout_discover, null);
		mylist = (ListView) view.findViewById(R.id.listView);
		// 新增方法
		mylist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				PlayNote playNote = (PlayNote) adapter.getItem(position - 2);
				Intent intent = new Intent(getActivity(),
						NoteDeatailActivity.class);
				intent.putExtra("playNote", playNote);
				startActivity(intent);
			}
		});
		mylist.setAdapter(adapter);
		isInit = true;
		InitHeaderView();
		adapter.notifyDataSetChanged();
		/*
		 * if (isVisible) { new Handler().post(new Runnable() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub
		 * InitHeaderView(); adapter.notifyDataSetChanged();
		 * 
		 * } });
		 * 
		 * }
		 */
		return view;
	}

	public void initNotes() {
		StringPostRequest postRequest = new StringPostRequest(
				UrlUtil.TRIP_NOTES_URL, new Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						List<PlayNote> playNotes = new ArrayList<PlayNote>();
						Gson gson = new Gson();
						playNotes = gson.fromJson(arg0,
								new TypeToken<ArrayList<PlayNote>>() {
								}.getType());
						if (playNotes.size() > 0) {
							mdata.clear();
							mdata.addAll(playNotes);
							adapter.notifyDataSetChanged();
							newData.add(mdata.get(0));
							newData.add(mdata.get(1));
						}

					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(getActivity(), "网络连接错误",
								Toast.LENGTH_LONG).show();
					}
				});
		postRequest.PutParams("action", "top");
		TripApplication.getInstance().getRequestQueue().add(postRequest);

	}

	public void initPlayMethod() {
		PlayMethod method = methods.get(count);
		playMethodImg.setImageResource(method.getImg());
		playMethodContent.setText(method.getContnt());
		playMethodTitle.setText(method.getTitle());
	}

	public void InitHeaderView() {
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.trip_home_page_headview, null);
		// 新增方法
		view.findViewById(R.id.moreSub).setOnClickListener(this);
		view.findViewById(R.id.search).setOnClickListener(this);
		playMethodChange = (Button) view.findViewById(R.id.playMethodChange);
		playMethodTitle = (TextView) view.findViewById(R.id.playMethodTitle);
		playMethodContent = (TextView) view
				.findViewById(R.id.playMethodContent);
		view.findViewById(R.id.playnotes).setOnClickListener(this);
		playMethodImg = (ImageView) view.findViewById(R.id.playMethodImg);
		initPlayMethod();
		playMethodImg.setOnClickListener(this);
		playMethodChange.setOnClickListener(this);
		RelativeLayout findStrategy = (RelativeLayout) view
				.findViewById(R.id.findStrategy);
		RelativeLayout diaosi = (RelativeLayout) view.findViewById(R.id.diaosi);
		findStrategy.setOnClickListener(this);
		view.findViewById(R.id.qinglv).setOnClickListener(this);
		view.findViewById(R.id.zijiayou).setOnClickListener(this);
		view.findViewById(R.id.chihuo).setOnClickListener(this);
		view.findViewById(R.id.xingshe).setOnClickListener(this);
		view.findViewById(R.id.duzi).setOnClickListener(this);
		RelativeLayout lookforTraffic = (RelativeLayout) view
				.findViewById(R.id.lookforTraffic);
		lookforTraffic.setOnClickListener(this);

		view.findViewById(R.id.orderhotel).setOnClickListener(this);
		diaosi.setOnClickListener(this);

		myPager = (ViewPager) view.findViewById(R.id.homePager);
		headerIndicatorContainer = (LinearLayout) view
				.findViewById(R.id.headerIndicatorContainer);
		intiHeaderViewPager();
		mylist.addHeaderView(view);

	}

	public void intiHeaderViewPager() {
		final List<ImageView> images = new ArrayList<ImageView>();
		ImageView image = new ImageView(getActivity());
		image.setImageResource(R.drawable.img_guide_cover_2);
		image.setScaleType(ScaleType.FIT_XY);
		images.add(image);

		image = new ImageView(getActivity());
		image.setImageResource(R.drawable.img_guide_cover_1);
		image.setScaleType(ScaleType.FIT_XY);
		images.add(image);

		image = new ImageView(getActivity());
		image.setImageResource(R.drawable.img_guide_cover_3);
		image.setScaleType(ScaleType.FIT_XY);
		images.add(image);

		HomePagerViewAdapter adapter = new HomePagerViewAdapter(images);
		myPager.setAdapter(adapter);

		for (int i = 0; i < images.size(); i++) {
			ImageView header_indicator = (ImageView) LayoutInflater.from(
					getActivity()).inflate(R.layout.homepager_header_indicator,
					null);
			if (i == 0) {
				header_indicator.setImageResource(R.drawable.b);
			} else {
				header_indicator.setImageResource(R.drawable.a);
			}
			LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			// lp.gravity = Gravity.CENTER;
			lp.setMargins(30, 60, 0, 0);
			headerIndicatorContainer.addView(header_indicator, lp);
		}

		myPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				for (int i = 0; i < images.size(); i++) {
					ImageView indicator = (ImageView) headerIndicatorContainer
							.getChildAt(i);
					if (i == arg0) {
						indicator.setImageResource(R.drawable.b);
					} else {
						indicator.setImageResource(R.drawable.a);
					}
				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId()) {
		case R.id.findStrategy:
			Intent intent = new Intent(getActivity(), CityListActivity.class);
			intent.putExtra("category", "discovery");
			startActivity(intent);
			break;
		case R.id.playnotes:
			startActivity(new Intent(getActivity(), NoteMainActivity.class));
			break;
		case R.id.diaosi:
			intent = new Intent(getActivity(),
					ShortCityStrategyDetailActivity.class);

			intent.putExtra("situation", new PlayMethod(
					"http://m.mafengwo.cn/nb/h5/topic.php?id=802", "山水游",
					R.drawable.diao,
					"桂林 photo by 游梦 推荐理由：桂林风景秀丽，有以漓江风光和喀斯特地貌为代表", 5));
			startActivity(intent);
			break;

		case R.id.zijiayou:
			intent = new Intent(getActivity(),
					ShortCityStrategyDetailActivity.class);
			intent.putExtra("situation", new PlayMethod(
					"http://m.mafengwo.cn/nb/h5/topic.php?id=807", "自驾游",
					R.drawable.zijia,
					"新西兰 photo by ZY_FC 推荐理由：新西兰最美的地方不在于景点，而是在那广袤苍茫的大地", 6));
			startActivity(intent);
			break;

		case R.id.qinglv:
			intent = new Intent(getActivity(),
					ShortCityStrategyDetailActivity.class);
			intent.putExtra("situation", new PlayMethod(
					"http://m.mafengwo.cn/nb/h5/topic.php?id=797", "情侣游",
					R.drawable.love, "鼓浪屿 photo by 然然 推荐理由："
							+ "鼓浪屿小资情调浓郁，不仅有风格迥异的建筑，还有迷人的碧海蓝天，但", 7));
			startActivity(intent);
			break;
		case R.id.chihuo:
			intent = new Intent(getActivity(),
					ShortCityStrategyDetailActivity.class);
			intent.putExtra(
					"situation",
					new PlayMethod(
							"http://m.mafengwo.cn/nb/h5/topic.php?id=806",
							"吃货游",
							R.drawable.food_beaut,
							"槟城 photo by 同类.angle 推荐理由：槟城的美食在马来西亚享有盛名，一些普通的食材，在槟城厨师的一双巧手",
							8));
			startActivity(intent);

			break;
		case R.id.xingshe:

			intent = new Intent(getActivity(),
					ShortCityStrategyDetailActivity.class);
			intent.putExtra(
					"situation",
					new PlayMethod(
							"http://m.mafengwo.cn/nb/h5/topic.php?id=804",
							"拍照游", R.drawable.guimi,
							"婺源 photo by 胡打岔 推荐理由：你对婺源的油菜花早就垂涎三尺了吧，美美的油菜花，在油菜花海中能拍出美美的人像"));
			startActivity(intent);
			break;
		case R.id.duzi:
			intent = new Intent(getActivity(),
					ShortCityStrategyDetailActivity.class);
			intent.putExtra(
					"situation",
					new PlayMethod(
							"http://m.mafengwo.cn/nb/h5/topic.php?id=839",
							"独自游", R.drawable.self,
							"清迈 photo by 第九棵樟树 推荐理由：泰北玫瑰--小城清迈绝对是一个人散心的好去处。不同于热闹的海滩，这里多了一份恬淡的宁静"));
			startActivity(intent);
			break;
		case R.id.orderhotel:
			startActivity(new Intent(getActivity(), OrderHotel.class));
			break;
		case R.id.moreSub:
			startActivity(new Intent(getActivity(), NoteMainActivity.class));
			break;
		case R.id.search:
			startActivity(new Intent(getActivity(),
					SearchLocationActivity.class));
			break;
		case R.id.lookforTraffic:
			startActivity(new Intent(getActivity(),
					LookForTrafficActivity.class));
			break;
		case R.id.playMethodImg:
			intent = new Intent(getActivity(),
					ShortCityStrategyDetailActivity.class);
			intent.putExtra("situation", methods.get(count));
			startActivity(intent);
			break;
		case R.id.playMethodChange:
			count++;
			if (methods.size() == count) {
				count = 0;
			}
			initPlayMethod();
			break;

		}

	}
	/*
	 * @Override public void lazyLoadData() { // TODO Auto-generated method stub
	 * if (isVisible && isInit) { InitHeaderView();
	 * adapter.notifyDataSetChanged(); } }
	 */
}
