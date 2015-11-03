package com.example.trip.activity;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.example.trip.R;
import com.example.trip.R.id;
import com.example.trip.entity.PlayMethod;
import com.example.trip.manager.ShareManager;
import com.example.trip.util.Constant;
import com.example.trip.util.DatabaseOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.umeng.socialize.bean.SocializeConfig;
import com.umeng.socialize.net.m;
import com.umeng.socialize.sso.UMSsoHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ShortCityStrategyDetailActivity extends Activity implements
		OnClickListener {
	private WebView webview;
	private TextView travelName;
	private CheckBox storageImg;
	private DatabaseOpenHelper helper;
	private Dao<PlayMethod, Integer> collectionArticleDao;
	private ShareManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shortcity_strategydetail);
		findViewById(R.id.share).setOnClickListener(this);
		findViewById(R.id.back).setOnClickListener(this);
		manager = new ShareManager(this);

		helper = DatabaseOpenHelper.getInstance(this);
		collectionArticleDao = helper.getCollectionArticleDao();
		travelName = (TextView) findViewById(R.id.travelName);
		storageImg = (CheckBox) findViewById(R.id.storageImg);

		final PlayMethod method = (PlayMethod) getIntent()
				.getSerializableExtra("situation");
		storageImg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1 == false) {
					try {
						if (collectionArticleDao.idExists(method.getId()) == true) {

							collectionArticleDao.delete(method);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					SimpleDateFormat sDateFormat = new SimpleDateFormat(
							"yyyy/MM/dd");
					String nowDate = sDateFormat.format(new java.util.Date());
					method.setTime(nowDate);
					try {
						collectionArticleDao.createIfNotExists(method);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		if (method != null) {
			travelName.setText(method.getTitle());
			webview = (WebView) findViewById(R.id.webview);
			webview.loadUrl(method.getUrl());
			try {
				if (collectionArticleDao.idExists(method.getId()) == true) {
					storageImg.setChecked(true);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			storageImg.setVisibility(View.GONE);
			int num = getIntent().getIntExtra("situationss", 0);
			switch (num) {
			case Constant.INFO:
				webview = (WebView) findViewById(R.id.webview);
				webview.loadUrl("http://m.mafengwo.cn/baike/info-10444.html");
				break;
			case Constant.COST:
				storageImg.setVisibility(View.GONE);
				webview = (WebView) findViewById(R.id.webview);
				webview.loadUrl("http://m.mafengwo.cn/baike/cost-10444.html");
				break;
			case Constant.TIPS:
				storageImg.setVisibility(View.GONE);
				webview = (WebView) findViewById(R.id.webview);
				webview.loadUrl("http://m.mafengwo.cn/baike/tips-10444.html");
				break;
			case Constant.IN:
				storageImg.setVisibility(View.GONE);
				webview = (WebView) findViewById(R.id.webview);
				webview.loadUrl("http://m.mafengwo.cn/baike/in-10444.html");
				break;
			case Constant.OUT:
				storageImg.setVisibility(View.GONE);
				webview = (WebView) findViewById(R.id.webview);
				webview.loadUrl("http://m.mafengwo.cn/baike/out-10444.html");
				break;
			case Constant.FESTIVAL:
				storageImg.setVisibility(View.GONE);
				webview = (WebView) findViewById(R.id.webview);
				webview.loadUrl("http://m.mafengwo.cn/baike/festival-10444.html");
				break;
			case Constant.HIGHTLIGHT:
				storageImg.setVisibility(View.GONE);
				webview = (WebView) findViewById(R.id.webview);
				webview.loadUrl("http://m.mafengwo.cn/baike/highlight-10444.html");
				break;

			default:
				break;
			}
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.share:
			manager.create();
			manager.postShare();
			break;
		default:
			break;
		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		manager.onDestroy();
	}

	
	
}
