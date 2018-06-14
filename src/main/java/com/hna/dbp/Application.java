
package com.hna.dbp;

import com.hna.dbp.eureka.lite.EnableEurekaLite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * 程序的入口
 * 
 * @author jlgan
 */
@Configuration
@EnableDiscoveryClient
@EnableAutoConfiguration
@ComponentScan({"com.hna.dbp"})
@EnableEurekaLite
@EnableSwagger2
@EnableScheduling
public class Application {

  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
    LOGGER.debug("服务器启动成功");
  }

}
