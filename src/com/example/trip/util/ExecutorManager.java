package com.example.trip.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import android.R.integer;

public class ExecutorManager {
	private ExecutorService executorService;
	private static ExecutorManager manager;

	private ExecutorManager() {
		// TODO Auto-generated constructor stub
		init();
	}

	public static ExecutorManager getInstance() {
		if (manager == null) {
			synchronized (ExecutorManager.class) {
				if (manager == null) {
					manager = new ExecutorManager();
				}
			}
		}
		return manager;
	}

	private void init() {
		int num = 2 * Runtime.getRuntime().availableProcessors();
		int max = num > 8 ? 8 : num;
		executorService = Executors.newFixedThreadPool(max);
	}

	public void excute(Runnable runnable) {
		executorService.execute(runnable);
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}
	
}
