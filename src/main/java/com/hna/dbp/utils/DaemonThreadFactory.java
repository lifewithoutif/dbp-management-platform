
package com.hna.dbp.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DaemonThreadFactory implements ThreadFactory {
	private final ThreadFactory core;

	public DaemonThreadFactory() {
		this(Executors.defaultThreadFactory());
	}

	public DaemonThreadFactory(ThreadFactory core) {
		this.core = core;
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = core.newThread(r);
		t.setDaemon(true);
		return t;
	}
}
