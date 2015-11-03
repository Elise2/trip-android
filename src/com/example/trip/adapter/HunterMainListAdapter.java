package com.example.trip.adapter;

import java.util.List;
import com.example.trip.R;
import com.example.trip.adapter.HotelMainAdapter.HotelHolder;
import com.example.trip.entity.Hotel;
import com.example.trip.entity.Hunter;
import com.example.trip.util.ImageLoaderUtil;
import com.example.trip.util.UrlUtil;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 猎人主页面展示列表
 * 
 * @author lewei
 * 
 */
public class HunterMainListAdapter extends BaseAdapter {
	private List<Hunter> mdata;
	private Context mContext;

	public HunterMainListAdapter(List<Hunter> mdata, Context mContext) {
		super();
		this.mdata = mdata;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.mdata.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.mdata.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		HotelHolder holder = null;
		if (convertView == null) {
			holder = new HotelHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.huntermain_listview_item, null);
			holder.hunterdescript = (TextView) convertView
					.findViewById(R.id.hunter_descript);
			holder.hunterImg = (ImageView) convertView
					.findViewById(R.id.hunterMainImg);
			holder.hunterPrice = (TextView) convertView
					.findViewById(R.id.hunterPrice);
			holder.userimg = (ImageView) convertView
					.findViewById(R.id.hunterImg);
			holder.likeNum = (TextView) convertView
					.findViewById(R.id.hunterFassNum);
			holder.buttonContainer = (LinearLayout) convertView
					.findViewById(R.id.buttonContainer);
			convertView.setTag(holder);
		} else {
			holder = (HotelHolder) convertView.getTag();
		}
		Hunter data = mdata.get(position);
		holder.hunterdescript.setText(data.getHunter_descript());
		holder.hunterPrice.setText(data.getHunter_price() + "");
		String url = data.getHunter_title_img() + "";
		if (!url.equals("")) {
			url = UrlUtil.ROOT_URL + url;
			ImageLoaderUtil.display(url, holder.hunterImg);
		}

		String userUrl = data.getHunter_img() + "";
		if (!userUrl.equals("")) {
			userUrl = UrlUtil.ROOT_URL + userUrl;
			ImageLoaderUtil.display(userUrl, holder.userimg);
		}

		holder.likeNum.setText(data.getLike_num() + "");
		String str = data.getHunter_label();
		String[] strArray = null;
		if (str == null || "".equals(str)) {

		} else {
			strArray = convertStrToArray(str);
		}
		// for (int i = 0; i < strArray.length; i++) {
		// Button button =new Button(mContext);
		// button.setBackground(mContext.getResources().getDrawable(R.drawable.btnbg));
		// button.setText(strArray[i]);
		// holder.buttonContainer.addView(button);
		// }
		return convertView;
	}

	public class HotelHolder {
		public TextView hunterdescript;
		public ImageView hunterImg;
		public ImageView userimg;
		public TextView likeNum;
		public TextView hunterPrice;
		public LinearLayout buttonContainer;
	}

	// 使用String的split 方法
	public static String[] convertStrToArray(String str) {
		String[] strArray = null;
		strArray = str.split(","); // 拆分字符为"," ,然后把结果交给数组strArray
		return strArray;
	}
}
