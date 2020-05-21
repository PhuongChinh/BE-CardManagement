package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;


@Configuration
public class RepoRestConfig implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config);	
		config.setReturnBodyForPutAndPost(true);
		config.setReturnBodyOnCreate(true);
		config.setReturnBodyOnUpdate(true);
		config.setBasePath("/api/v1");
	}
	
	
}
