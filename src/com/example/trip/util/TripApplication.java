package com.example.trip.util;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.trip.entity.User;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

public class TripApplication extends Application {
	private static TripApplication application;
	private RequestQueue requestQueue;
	private SetImageCache cache;
	//新增属性
	private User user;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		application = this;
		requestQueue = Volley.newRequestQueue(this);
		cache = new SetImageCache();
		// 初始化UIL
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this)
		// 不要缓存不同尺寸
				.denyCacheImageMultipleSizesInMemory()
				// 下载图片线程的优先级
				.threadPriority(Thread.NORM_PRIORITY - 2)
				// 设置下载线程的执行器（线程池）
				.taskExecutor(
						ExecutorManager.getInstance().getExecutorService())
				// 设置内存缓存的大小
				.memoryCacheSize((int) Runtime.getRuntime().maxMemory() / 8)
				// 设置磁盘缓存大小
				.diskCacheSize(50 * 1024 * 1024)
				// 设置磁盘缓存文件的命名生成器
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				// 设置磁盘缓存的路径
				.discCache(
						new UnlimitedDiskCache(FileUtil.getInstance(this)
								.makeDir("imagCache")))
				//
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				// 设置具体的图片加载器：
				.imageDownloader(
						new BaseImageDownloader(this, 60 * 1000, 60 * 1000))
				// 生成配置信息
				.build();
		ImageLoader.getInstance().init(config);

	}

	public static TripApplication getInstance() {
		return application;
	}

	public RequestQueue getRequestQueue() {
		return this.requestQueue;
	}

	public SetImageCache getImageCache() {
		return cache;
	}
	
	//新增方法
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
