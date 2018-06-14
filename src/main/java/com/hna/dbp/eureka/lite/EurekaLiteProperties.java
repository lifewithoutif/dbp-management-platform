package com.hna.dbp.eureka.lite;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author jlgan
 */
@Data
@ConfigurationProperties("eureka.lite")
public class EurekaLiteProperties {
  private boolean unregisterOnShutdown = false;
}
