package com.example.trip.fragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.baidu.location.f.c;
import com.example.trip.R;
import com.example.trip.activity.PublishShortActivity;
import com.example.trip.activity.ShortCityStrategyDetailActivity;
import com.example.trip.adapter.CollectionArticleAdapter;
import com.example.trip.adapter.CommunityAdapter;
import com.example.trip.entity.Community;
import com.example.trip.entity.PlayMethod;
import com.example.trip.entity.Community;
import com.example.trip.util.DatabaseOpenHelper;
import com.example.trip.util.StringPostRequest;
import com.example.trip.util.TripApplication;
import com.example.trip.util.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.query.In;

public class CommunityFragment extends Fragment implements OnClickListener {
	private ListView list;
	private List<Community> communities;
	private CommunityAdapter adapter;
	/* 头像文件 */
	private static final String IMAGE_FILE_NAME = "head_image.jpg";
	/* 请求识别码 */
	private static final int CODE_GALLERY_REQUEST = 0xa0;
	private static final int CODE_CAMERA_REQUEST = 0xa1;
	private static final int CODE_RESULT_REQUEST = 0xa2;
	// 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
	private static int output_X = 480;
	private static int output_Y = 480;
	String photoPath;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		communities = new ArrayList<Community>();
		adapter = new CommunityAdapter(communities, getActivity());
	}

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.layout_community, null);
		v.findViewById(R.id.camera).setOnClickListener(this);
		list = (ListView) v.findViewById(R.id.list);
		list.setAdapter(adapter);
		initData();
		return v;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		initData();
	}

	public void initData() {
		StringPostRequest postRequest = new StringPostRequest(
				UrlUtil.TRIP_SHORT_URL, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						List<Community> Communitys = new ArrayList<Community>();
						Gson gson = new Gson();
						Communitys = gson.fromJson(arg0,
								new TypeToken<ArrayList<Community>>() {
								}.getType());
						if (Communitys.size() > 0) {
							communities.clear();
							communities.addAll(Communitys);
							adapter.notifyDataSetChanged();
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
		postRequest.PutParams("action", "select");
		TripApplication.getInstance().getRequestQueue().add(postRequest);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.camera:
			if (TripApplication.getInstance().getUser() == null
					|| "".equals(TripApplication.getInstance().getUser()
							.getUsername())) {
				Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_LONG).show();
			} else {
				Intent picture = new Intent(Intent.ACTION_PICK);
				picture.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(picture, CODE_GALLERY_REQUEST);
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);

		// 用户没有进行有效的设置操作，返回
		if (resultCode == getActivity().RESULT_CANCELED) {
			Toast.makeText(getActivity(), "取消", Toast.LENGTH_LONG).show();
			return;
		}
		switch (requestCode) {
		case CODE_GALLERY_REQUEST:
			Cursor cursor = getActivity().getContentResolver().query(
					intent.getData(),
					new String[] { MediaStore.Images.Media.DATA }, null, null,
					null);
			cursor.moveToFirst();
			photoPath = cursor.getString(cursor
					.getColumnIndex(MediaStore.Images.Media.DATA));
			cursor.close();
			cropRawPhoto(Uri.fromFile(new File(photoPath)));

			break;
		case CODE_RESULT_REQUEST:
			if (intent != null) {
				setImageToHeadView(intent);
			}
			break;
		}
	}

	/** * 裁剪原始的图片 */
	public void cropRawPhoto(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 设置裁剪
		intent.putExtra("crop", "true");
		// aspectX , aspectY :宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX , outputY : 裁剪图片宽高
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, CODE_RESULT_REQUEST);
	}

	/** * 提取保存裁剪之后的图片数据，并设置头像部分的View */
	private void setImageToHeadView(Intent intent) {
		Bundle extras = intent.getExtras();
		if (extras != null) {
			final Bitmap photo = extras.getParcelable("data");
			Intent intent2 = new Intent(getActivity(),
					PublishShortActivity.class);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			photo.compress(Bitmap.CompressFormat.PNG, 100, baos);
			byte[] bitmapByte = baos.toByteArray();
			intent2.putExtra("bitmap", bitmapByte);
			startActivity(intent2);
		}
	}

	public String convertBitmap(Bitmap bitmap) {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		// 将图片进行压缩
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
		try {
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] buffer = outputStream.toByteArray();
		byte[] encode = Base64.encode(buffer, Base64.DEFAULT);
		return new String(encode);
	}

	/** * 检查设备是否存在SDCard的工具方法 */
	public static boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			// 有存储的SDCard
			return true;
		} else {
			return false;
		}
	}

}
