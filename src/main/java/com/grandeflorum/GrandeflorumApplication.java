package com.grandeflorum;

import com.grandeflorum.common.config.GrandeflorumProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableConfigurationProperties({GrandeflorumProperties.class})
@MapperScan("com.grandeflorum.*.dao")
@EnableAsync
@EnableCaching
public class GrandeflorumApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GrandeflorumApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(GrandeflorumApplication.class, args);
	}

}
