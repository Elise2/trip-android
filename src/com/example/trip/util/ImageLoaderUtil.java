package com.example.trip.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.trip.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class ImageLoaderUtil {
	private static DisplayImageOptions options = new DisplayImageOptions.Builder()
			// ���ع������ʾ��ͼƬ
			.showImageOnLoading(
					R.drawable.emergency_rescue_group_item_default_icon)
			// ����ʧ��ʱ��ʾ��ͼƬ
			.showImageOnFail(
					R.drawable.emergency_rescue_group_item_default_icon)
			// uriΪ�յ�ʱ����ʾ��ͼƬ
			.showImageForEmptyUri(
					R.drawable.emergency_rescue_group_item_default_icon)
			// �Ƿ�����ڴ滺��
			.cacheInMemory(true)
			//
			.cacheOnDisk(true)
			//
			.bitmapConfig(Bitmap.Config.RGB_565).resetViewBeforeLoading(true)
			.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
			.displayer(new FadeInBitmapDisplayer(200))
			// RoundedBitmapDisplayer
			.build();

	// file://

	public static void display(String url, ImageView imageView) {
		ImageLoader.getInstance().displayImage(url, imageView, options);
	}
}
