
package com.hna.dbp.eureka.lite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.CloudEurekaClient;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.hna.dbp.controller.EurekaLiteController;

/**
 * @author jlgan
 */
@Configuration
@EnableConfigurationProperties
public class EurekaLiteConfiguration {
  private static final Logger LOG = LoggerFactory.getLogger(EurekaLiteConfiguration.class);

  @Bean
  public EurekaLiteProperties eurekaLiteProperties() {
    return new EurekaLiteProperties();
  }

  @Bean
  @ConditionalOnMissingBean
  public Eureka eureka(InetUtils inetUtils, @Lazy CloudEurekaClient eurekaClient,
      EurekaClientConfigBean eurekaClientConfigBean) {
    return new Eureka(inetUtils, eurekaClient, eurekaClientConfigBean);
  }

  @Bean
  public EurekaLiteController eurekaLiteController(Eureka eureka, EurekaLiteProperties properties) {
    return new EurekaLiteController(eureka);
  }

}
