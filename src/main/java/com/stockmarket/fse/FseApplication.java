package com.stockmarket.fse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@SpringBootApplication(exclude = {ContextStackAutoConfiguration.class})
//@SpringBootApplication
@SpringBootApplication(exclude = {ContextStackAutoConfiguration.class})
@EnableEurekaClient
@EnableCaching
public class FseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FseApplication.class, args);
	}

}
