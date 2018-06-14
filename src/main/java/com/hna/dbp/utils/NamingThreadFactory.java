
package com.hna.dbp.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread factory that sets thread name so we know who is responsible for so many threads being created.
 * @since 1.541
 */
public final class NamingThreadFactory implements ThreadFactory {

	private final AtomicInteger threadNum = new AtomicInteger();
	private final ThreadFactory delegate;
	private final String name;

	/**
	 * Creates a new naming factory.
	 * @param delegate a baseline factory, such as {@link Executors#defaultThreadFactory} or {@link DaemonThreadFactory} or {@link ExceptionCatchingThreadFactory}
	 * @param name an identifier to be used in thread names; might be e.g. your {@link Class#getSimpleName}
	 */
	public NamingThreadFactory(ThreadFactory delegate, String name) {
		this.delegate = delegate;
		this.name = name;
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = delegate.newThread(r);
		t.setName(name + " [#" + threadNum.incrementAndGet() + "]");
		return t;
	}

}
