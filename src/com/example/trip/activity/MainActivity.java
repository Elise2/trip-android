package com.example.trip.activity;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import com.example.trip.R;
import com.example.trip.adapter.MainViewPagerAdapter;
import com.example.trip.fragment.CollectionArticleFragment;
import com.example.trip.fragment.CollectionCityFragment;
import com.example.trip.fragment.CommunityFragment;
import com.example.trip.fragment.DateFragment;
import com.example.trip.fragment.DiscoverFragment;
import com.example.trip.fragment.MyFragment;
import com.example.trip.robot.LostActivity;
import com.example.trip.util.Constant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity {
	private ViewPager pager;
	private List<Fragment> fragments;
	private MainViewPagerAdapter adapter;
	private RadioGroup select;
	private WindowManager mWindowManager;
	private WindowManager.LayoutParams mWindowParams;
	private View root;
	private SharedPreferences spPreferences;
	private boolean flag;
	private float StartX;
	private float StartY;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.layout_main);
		initView();
		initEvent();
		initFragment();
		spPreferences = getSharedPreferences(Constant.SP_NAME, MODE_PRIVATE);
		flag = spPreferences.getBoolean("lost", true);
		if (flag) {
			addView();
		}
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		flag = spPreferences.getBoolean("lost", true);
		boolean isShowing = spPreferences.getBoolean("isShowing", true);
		if (flag) {
			if (!isShowing) {
				addView();
			}
		} else {
			if (isShowing) {
				removeView();
			}
		}

	}

	private void initView() {
		pager = (ViewPager) findViewById(R.id.pager);
		fragments = new ArrayList<Fragment>();
		select = (RadioGroup) findViewById(R.id.select);

	}

	private void removeView() {
		SharedPreferences.Editor editor = spPreferences.edit();
		editor.putBoolean("isShowing", false);
		editor.commit();
		mWindowManager.removeView(root);
	}

	private void addView() {
		SharedPreferences.Editor editor = spPreferences.edit();
		editor.putBoolean("isShowing", true);
		editor.commit();

		// 获取Service
		if (mWindowManager == null) {
			mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		}
		if (mWindowParams == null) {
			mWindowParams = new WindowManager.LayoutParams();
		}
		int screenWidth = mWindowManager.getDefaultDisplay().getWidth();
		int screenHeight = mWindowManager.getDefaultDisplay().getHeight();
		// 设置窗口类型，一共有三种Application windows, Sub-windows, System windows
		// API中以TYPE_开头的常量有23个
		mWindowParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
		mWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		// 设置期望的bitmap格式
		mWindowParams.format = PixelFormat.RGBA_8888;

		// 以下属性在Layout Params中常见重力、坐标，宽高
		mWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
		mWindowParams.x = screenWidth - 100;
		mWindowParams.y = screenHeight - 200;

		mWindowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		mWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

		root = LayoutInflater.from(this).inflate(R.layout.mywindows, null);
		// root.setFocusable(true);
		ImageView tx1 = (ImageView) root.findViewById(R.id.text);
		/*
		 * tx1.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View arg0) { // TODO Auto-generated
		 * method stub // removeView(); startActivity(new
		 * Intent(MainActivity.this, LostActivity.class)); } });
		 */
		// tx1.setText("what's your name?");
		mWindowManager.addView(root, mWindowParams);
		root.setOnTouchListener(new OnTouchListener() {
			float mRawX, mRawY, mStartX, mStartY;

			public boolean onTouch(View v, MotionEvent event) {
				// 获取相对屏幕的坐标，即以屏幕左上角为原点
				mRawX = event.getRawX();
				mRawY = event.getRawY() - 25; // 25是系统状态栏的高度
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					StartX = mRawX;
					StartY = mRawY;
					// 获取相对View的坐标，即以此View左上角为原点
					mStartX = event.getX();
					mStartY = event.getY();

					break;
				case MotionEvent.ACTION_MOVE:
					updateWindowPosition();
					break;

				case MotionEvent.ACTION_UP:

					// 关键部分：移动距离较小，视为onclick点击行为
					if (Math.abs(mRawX - StartX) < 1.5
							&& Math.abs(mRawY - StartY) < 1.5) {
						startActivity(new Intent(MainActivity.this,
								LostActivity.class));
					} else {
						updateWindowPosition();
					}
					mStartX = mStartY = 0;
					break;
				}
				return true;
			}

			private void updateWindowPosition() {
				mWindowParams.x = (int) (mRawX - mStartX);
				mWindowParams.y = (int) (mRawY - mStartY);
				// 使参数生效
				mWindowManager.updateViewLayout(root, mWindowParams);
			}

		});

	}

	private void initEvent() {
		select.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				for (int i = 0; i < arg0.getChildCount(); i++) {
					if (arg0.getChildAt(i).getId() == arg1) {
						pager.setCurrentItem(i);
					}
				}

			}
		});
		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				for (int i = 0; i < select.getChildCount(); i++) {
					RadioButton button = (RadioButton) select.getChildAt(i);
					if (arg0 == i) {
						button.setChecked(true);
					} else {
						button.setChecked(false);

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
		pager.setCurrentItem(0);
		((RadioButton) select.getChildAt(0)).setChecked(true);
	}

	// 加载主页面布局
	private void initFragment() {
		Fragment fragment = new DiscoverFragment();
		fragments.add(fragment);
		Fragment fragment1 = new CommunityFragment();
		fragments.add(fragment1);
		Fragment fragment2 = new DateFragment();
		fragments.add(fragment2);
		Fragment fragment3 = new MyFragment();
		fragments.add(fragment3);
		adapter = new MainViewPagerAdapter(getSupportFragmentManager(),
				fragments);
		pager.setAdapter(adapter);
	}

}
