package com.hna.dbp.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 过滤所有的http请求，主要实现的功能<br>
 * 1、获取请求参数中的参数<br>
 * 
 * @author jlgan
 *
 */
public class RestFilter implements Filter {

	private static final Logger LOG = LoggerFactory.getLogger(RestFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	/**
	 * 对请求进行拦截
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String reqUrl = req.getRequestURL().toString();
		String param = req.getQueryString();
		LOG.debug("URL ={}  Param=[{}]  Method={} ", reqUrl, param, req.getMethod());
		ServletInputStream inputStream = null;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			inputStream = request.getInputStream();
			int bufferLen = 1024 * 8;
			byte[] buffer = new byte[bufferLen];
			int len = 0;
			while ((len = inputStream.read(buffer, 0, bufferLen)) != -1) {
				outputStream.write(buffer, 0, len);
			}
			byte[] allData = outputStream.toByteArray();
			RestHttpServletRequestWrapper restHttpServletRequestWrapper = new RestHttpServletRequestWrapper(req);
			String content = new String(allData, "UTF-8");
			LOG.debug("content={}", content);
			restHttpServletRequestWrapper.setContent(allData);
			chain.doFilter(restHttpServletRequestWrapper, response);
		} catch (IOException e) {
			LOG.error("filter  IOException", e);
		} finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outputStream);
		}

	}

	@Override
	public void destroy() {
	}

}
