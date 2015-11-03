package com.example.trip.activity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import com.android.volley.toolbox.ImageLoader;
import com.example.trip.R;
import com.example.trip.entity.Scenery;
import com.example.trip.manager.ShareManager;
import com.example.trip.util.ImageLoaderUtil;
import com.example.trip.util.SceneryConfigration;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SceneryContentActivity extends Activity {
	private Scenery scenery;
	private List<Scenery> mdata;
	private List<Scenery> newData;
	private TextView sceneryName;
	private TextView scenerySupport;
	private TextView recommendContent;
	private TextView mTextView;
	private LinearLayout strategyContainer;
	private int maxLine = 3;
	private String scename;
	private Scenery scenery1;
	private int position;
	private ShareManager manager;

	// 关于评论
	private TextView recommentorName1;
	private ImageView recommentorImg1;
	private TextView comment1;
	private TextView recommentorName2;
	private ImageView recommentorImg2;
	private TextView comment2;
	private TextView recommentorName3;
	private ImageView recommentorImg3;
	private TextView comment3;
	private ImageLoader imgLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_scenery_content);
		manager = new ShareManager(this);
		findViewById(R.id.travelImg).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		findViewById(R.id.shareImg).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				manager.create();
				manager.postShare();
			}
		});
		scenery1 = (Scenery) getIntent().getSerializableExtra("scenery");
		mdata = (List<Scenery>) getIntent().getSerializableExtra("scenerys");
		initView();
		scenery = new Scenery();
		newData = new ArrayList<Scenery>();
		new sceneryThread().start();

	}

	private void initView() {
		sceneryName = (TextView) findViewById(R.id.sceneryName);
		scenerySupport = (TextView) findViewById(R.id.scenerySupport);
		recommendContent = (TextView) findViewById(R.id.recommendContent);
		mTextView = (TextView) findViewById(R.id.adjust_text);
		strategyContainer = (LinearLayout) findViewById(R.id.strategyContainer);
		// 关于评论者
		recommentorName1 = (TextView) findViewById(R.id.orderName1);
		recommentorImg1 = (ImageView) findViewById(R.id.hotelItem1);
		comment1 = (TextView) findViewById(R.id.comment1);

		recommentorName2 = (TextView) findViewById(R.id.orderName2);
		recommentorImg2 = (ImageView) findViewById(R.id.hotelItem2);
		comment2 = (TextView) findViewById(R.id.comment2);

		recommentorName3 = (TextView) findViewById(R.id.orderName3);
		recommentorImg3 = (ImageView) findViewById(R.id.hotelItem3);
		comment3 = (TextView) findViewById(R.id.comment3);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		manager.onDestroy();
	}

	/**
	 * 获取景点tips
	 * 
	 * @return 景点对象
	 */
	public Scenery getScenery() {
		Scenery scenery = null;
		String url = null;
		if (scenery1 != null) {
			scename = scenery1.getName();
			url = "http://api.map.baidu.com/telematics/v3/travel_attractions?id="
					+ scename
					+ "&"
					+ "ak=8ehFGlEW9ab410o3fIsBQS52&mcode=BE:95:FB:38:22:E0:82:D2:4A:8A:FD:D6:88:9B:F2:A7:A7:9F:66:C2;com.example.trip";
			HttpResponse httpResponse = null;
			StringBuffer result = null;
			DefaultHttpClient client = null;
			try {
				client = new DefaultHttpClient();
				client.getParams().setParameter(
						CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);
				client.getParams().setParameter(
						CoreConnectionPNames.SO_TIMEOUT, 15000);
				HttpGet httpGet = new HttpGet(url);
				httpResponse = client.execute(httpGet);
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					result = new StringBuffer("");
					String s = EntityUtils.toString(httpResponse.getEntity(),
							"UTF-8");

					InputStream in_withcode = new ByteArrayInputStream(
							s.getBytes("UTF-8"));
					scenery = new SceneryConfigration().readInfo(in_withcode);
					if (scenery != null) {
						return scenery;
					}

				} else {
					// Toast.makeText(this, "数据请求异常！",
					// Toast.LENGTH_LONG).show();
					scenery = null;
				}
			} catch (Exception e) {
				// // TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return scenery;

	}

	Handler myHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (scenery != null) {
				setDescipte();
			}
			if (mdata.size() > 0 & mdata != null) {
				setData();
			}
			if (newData != null) {
				setRecommentor();
			}
		}

		public void setRecommentor() {
			if (newData.size() > 0) {
				Scenery scenery = newData.get(0);
				recommentorName1.setText(newData.get(0).getUsername());
				comment1.setText(newData.get(0).getRecommendContent());
				String url = newData.get(0).getUserImg();
				if (!url.equals("")) {
					url = UrlUtil.TRIP_LOGIN_URL + url;
					imgLoader = new ImageLoader(TripApplication.getInstance()
							.getRequestQueue(), TripApplication.getInstance()
							.getImageCache());
					ImageLoaderUtil.display(url, recommentorImg1);

				}

				recommentorName2.setText(newData.get(1).getUsername());
				comment2.setText(newData.get(1).getRecommendContent());
				url = newData.get(1).getUserImg();
				if (!url.equals("")) {
					url = UrlUtil.TRIP_LOGIN_URL + url;
					imgLoader = new ImageLoader(TripApplication.getInstance()
							.getRequestQueue(), TripApplication.getInstance()
							.getImageCache());
					ImageLoaderUtil.display(url, recommentorImg2);

				}

				recommentorName3.setText(newData.get(2).getUsername());
				comment3.setText(newData.get(2).getRecommendContent());
				url = newData.get(2).getUserImg();
				if (!url.equals("")) {
					url = UrlUtil.TRIP_LOGIN_URL + url;
					imgLoader = new ImageLoader(TripApplication.getInstance()
							.getRequestQueue(), TripApplication.getInstance()
							.getImageCache());
					ImageLoaderUtil.display(url, recommentorImg3);

				}
			}
		}

		public void setData() {
			for (int i = 0; i < mdata.size(); i++) {
				if (mdata.get(i).getName().equals(scename)) {
					newData.add(mdata.get(i));
				}
			}
		}

		private void setDescipte() {
			sceneryName.setText(scenery.getName());
			recommendContent.setText(scenery.getObstract());
			mTextView.setMaxLines(maxLine);
			mTextView.setEllipsize(TruncateAt.END);
			mTextView.setText(scenery.getDescription());
			mTextView.setHeight(mTextView.getLineHeight() * maxLine);
			mTextView.post(new Runnable() {
				@Override
				public void run() {
					strategyContainer
							.setVisibility(mTextView.getLineCount() > maxLine ? View.VISIBLE
									: View.GONE);
				}
			});
			strategyContainer.setOnClickListener(new MyTurnListener());
		}

	};

	class sceneryThread extends Thread {

		public void run() {

			try {
				scenery = getScenery();
				myHandler.sendEmptyMessage(0);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private class MyTurnListener implements View.OnClickListener {

		boolean isExpand;

		@Override
		public void onClick(View v) {
			isExpand = !isExpand;
			mTextView.clearAnimation();
			final int tempHight;
			final int startHight = mTextView.getHeight();
			int durationMillis = 200;

			if (isExpand) {
				tempHight = mTextView.getLineHeight()
						* mTextView.getLineCount() - startHight;
			} else {
				tempHight = mTextView.getLineHeight() * maxLine - startHight;
			}

			Animation animation = new Animation() {
				protected void applyTransformation(float interpolatedTime,
						Transformation t) {
					mTextView.setHeight((int) (startHight + tempHight
							* interpolatedTime));
				}
			};
			animation.setDuration(durationMillis);
			mTextView.startAnimation(animation);

		}
	}

}
