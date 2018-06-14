package com.hna.dbp.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 增加拦截器   打印请求后返回的数据
 * @author jlgan
 *
 */
@Configuration
public class WebAppConfigurer  extends WebMvcConfigurerAdapter {
	
  @Override
	public void addInterceptors(InterceptorRegistry registry) {
		  registry.addInterceptor(new ApplicationInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
}
