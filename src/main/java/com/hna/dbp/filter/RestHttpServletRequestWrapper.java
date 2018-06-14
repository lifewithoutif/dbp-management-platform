package com.hna.dbp.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 将前端的请求对象进一步封装,为数据签名预留扩展
 * 
 * @author jlgan
 *
 */
public class RestHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private InnerServletInputStream innerServletInputStream = null;

	private int contentLength = 0;

	public RestHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public int getContentLength() {
		return contentLength;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return innerServletInputStream;
	}

	public void setContent(byte[] content) {
		this.innerServletInputStream = new InnerServletInputStream(content);
		this.contentLength = content.length;
	}

	/**
	 * 自己实现一个servlet的inputstream类，用来封装请求后的数据
	 * 
	 * @author jlgan
	 *
	 */
	private static final class InnerServletInputStream extends ServletInputStream {

		private byte[] data = null;

		private int currentPoint = 0;

		private List<ReadListener> list = new ArrayList<ReadListener>();

		public InnerServletInputStream(byte[] data) {
			this.data = data;
		}

		@Override
		public boolean isFinished() {
			return currentPoint == data.length;
		}

		@Override
		public boolean isReady() {
			if (isFinished())
				return false;
			return true;
		}

		@Override
		public void setReadListener(ReadListener listener) {
			list.add(listener);
		}

		@Override
		public int read() throws IOException {
			if (isFinished()) {
				return -1;
			}
			int result = data[currentPoint++];
			return result;
		}

	}

}
