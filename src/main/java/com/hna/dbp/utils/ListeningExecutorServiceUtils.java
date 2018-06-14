package com.hna.dbp.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 *
 * 功能描述： 监听异步线程执行结果
 * 
 * @author ganjl
 *
 */
public class ListeningExecutorServiceUtils {

	private transient static ListeningExecutorService executorService = MoreExecutors
			.listeningDecorator(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2));

	public static <T> ListenableFuture<T> execAsyncListenableFuture(Callable<T> task) {
		return executorService.submit(task);
	}

	public static <T> void callbackListenableFuture(ListenableFuture<T> listener, FutureCallback<T> callback) {
		Futures.addCallback(listener, callback);
	}

	public static <T> T addListener(ListenableFuture<T> listener, Runnable runnable)
			throws Exception, ExecutionException {
		listener.addListener(runnable, executorService);
		return listener.get();
	}

}
