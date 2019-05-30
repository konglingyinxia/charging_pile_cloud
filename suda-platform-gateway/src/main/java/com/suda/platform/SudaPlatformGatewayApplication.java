package com.suda.platform;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author kongling
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,
        MybatisAutoConfiguration.class})
@EnableEurekaClient
@EnableAsync
@EnableCaching
public class SudaPlatformGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SudaPlatformGatewayApplication.class, args);
    }


}
