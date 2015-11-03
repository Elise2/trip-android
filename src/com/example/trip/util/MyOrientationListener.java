package com.example.trip.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MyOrientationListener implements SensorEventListener {
	// 传感器管理者
	private SensorManager mSensorManager;
	private Context mContext;
	// 传感器
	private Sensor mSensor;
	// X轴方向变化
	private float lastX;

	public MyOrientationListener(Context context) {
		this.mContext = context;
	}

	// 开始监听
	@SuppressWarnings("deprecation")
	public void start() {
		mSensorManager = (SensorManager) mContext
				.getSystemService(Context.SENSOR_SERVICE);
		if (mSensorManager != null) {
			// 获得方向传感器
			mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		}

		if (mSensor != null) {
			// 传感器，经度
			mSensorManager.registerListener(this, mSensor,
					SensorManager.SENSOR_DELAY_UI);
		}
	}

	// 移除监听
	public void stop() {
		mSensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings({ "deprecation" })
	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
			float x = event.values[SensorManager.DATA_X];

			if (Math.abs(x - lastX) > 1.0) {
				if (mOnOrientationListener != null) {
					mOnOrientationListener.onOrientationChanged(x);
				}
			}

			lastX = x;

		}
	}

	private OnOrientationListener mOnOrientationListener;

	public void setOnOrientationListener(
			OnOrientationListener mOnOrientationListener) {
		this.mOnOrientationListener = mOnOrientationListener;
	}

	public interface OnOrientationListener {
		void onOrientationChanged(float x);
	}

}
