/* 
 * @(#)ThreadUtils.java    Created on 2012-12-4
 * Copyright (c) 2012 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */

package com.example.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ThreadUtils
 * @Description: TODO(用来执行任务的线程工具，内包含了一个线程池)
 * @Author: soyoungboy
 * @Date: 2014-1-17 下午10:20:45
 */
public class ThreadUtils {
	private static volatile ThreadUtils instance = null;

	// private constructor suppresses
	private ThreadUtils() {

	}

	public static ThreadUtils getInstance() {
		// if already inited, no need to get lock everytime
		if (instance == null) {
			synchronized (ThreadUtils.class) {
				if (instance == null) {
					instance = new ThreadUtils();
				}
			}
		}

		return instance;
	}

	/**
	 * 初始化的线程数，有待历史的验证，暂时弄4个
	 */
	public static ExecutorService threadPool = Executors.newFixedThreadPool(4);

	/**
	 * 执行延迟任务，类似Timer的效果
	 */
	public static ScheduledExecutorService scheduleThreadPool = Executors
			.newScheduledThreadPool(2);

	/**
	 * 立即执行任务
	 * 
	 * @param task
	 *            ThreadUtils.getInstance().excute(run);
	 */
	public static void excute(Runnable task) {
		threadPool.execute(task);
	}

	/**
	 * 延后执行任务
	 * 
	 * @param task
	 * @param delay
	 *            ThreadUtils.getInstance().schedule(run,1000);
	 */
	public static void schedule(Runnable task, long delay) {
		scheduleThreadPool.schedule(task, delay, TimeUnit.MILLISECONDS);
	}

	/**
	 * @Title: shutdownThreadPool
	 * @Description: TODO()
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws 在onDestory
	 *             ()中执行[ThreadUtils.getInstance().shutdownThreadPool()]
	 */
	public static void shutdownThreadPool() {
		threadPool.shutdownNow();
	}

	/**
	 * @Title: shutdownThreadPool
	 * @Description: TODO()
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws 在onDestory
	 *             ()中执行[ThreadUtils.getInstance().shutdownScheduleThreadPool()]
	 */
	public static void shutdownScheduleThreadPool() {
		scheduleThreadPool.shutdownNow();
	}

}
